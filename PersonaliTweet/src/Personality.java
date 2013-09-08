
//import static PersonaliTweet.twitter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.TwitterException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Decamun
 */
public class Personality {

        public double[] proportions;
        public double[] counts;
        String name;

        public Personality(int catLength) {
            proportions = new double[catLength];
            counts = new double[catLength];
        }
        
        double[] tweetToUserMatch(String text, String[] users, analyzer localEngine){
            double[] probabilities = new double[users.length];
            ChiSquareTest testEngine=new ChiSquareTest();
            
            //Creates object with personality of the user who wrote the text
            Personality textUser = new Personality(counts.length);
            ArrayList<String> singleText= new ArrayList<String>();
            singleText.add(text);
            localEngine.analyze(singleText);
            int totalCountsUser = 0;
            for(int i=0;i<counts.length;i++){
                textUser.counts[i] = (double) localEngine.theCategories[i].counter; 
                totalCountsUser += textUser.counts[i];
            }
                
            
            Personality[] Persons = new Personality[users.length];
            for(int i=0;i<users.length;i++){
                Persons[i]=new Personality(counts.length);
                Persons[i].name = users[i];
            }            
            try{
                for(int i=0;i<users.length;i++){
                    ArrayList<String> sList = new ArrayList<>();
                    for (int x = 1; x < 10; x++) {
                        List<Status> statuses = PersonaliTweet.twitter.getUserTimeline(users[i], new Paging(x));// twitter.getHomeTimeline();
                        for (Status status : statuses) {
                            sList.add(status.getText());
                            //System.out.println(status.getUser().getName() + ":" +
                            //	status.getText());
                
                        }
                    }
                    localEngine.analyze(sList);
                    int total = 0;
                    for(int j=0;j<counts.length;j++){
                        Persons[i].counts[j] = localEngine.theCategories[j].counter;
                        total += Persons[i].counts[j];
                    }
                    
                    double[] expectedCounts = new double[counts.length];
                    for(int j=0;j<counts.length;j++){
                        Persons[i].proportions[j] = (double) Persons[i].counts[j] / total;
                        expectedCounts[j] = Persons[i].proportions[j] * totalCountsUser;
                    }
                    
                    long[] userCounts = new long[counts.length];
                    for(int j=0;j<userCounts.length;j++)
                        userCounts[j] = (long) textUser.counts[j];
                    for (int j=0; j<expectedCounts.length; j++){
                        if (expectedCounts[j]<5){
                            expectedCounts[j]=6;
                        }
                        if (userCounts[j]<5){
                            userCounts[j]=6;
                        }
                    }
                    probabilities[i] = testEngine.chiSquareTest(expectedCounts, userCounts);
                    
                }
                
                
            }
            catch(TwitterException e){
                System.out.println("Error!");
                System.out.println(e);
            }
            
            catch (NotPositiveException e){
                System.out.println("Error!");
            }
            catch (IllegalArgumentException e){
                System.out.println("It's too small!");
            }
            return probabilities;
        }
        
        double[] userToPersonalityMatch(String[] users, analyzer localEngine){
            Personality[] personas=new Personality[users.length];
            double[] probabilities=new double[users.length];
            ChiSquareTest testEngine=new ChiSquareTest();
            try{
                for (int i=0; i<users.length; i++){
                    ArrayList<String> sList = new ArrayList<>();
                    for (int x = 1; x < 10; x++) {
                        List<Status> statuses = PersonaliTweet.twitter.getUserTimeline(users[i], new Paging(x));// twitter.getHomeTimeline();
                        for (Status status : statuses) {
                            sList.add(status.getText());
                            //System.out.println(status.getUser().getName() + ":" +
                            //	status.getText());
                
                        }
                    }
                    localEngine.analyze(sList);
                    long observedCounts[]=new long[counts.length];
                    double total=0;
                    personas[i]=new Personality(counts.length);
                    for (int m=0; m<personas[i].counts.length; m++){
                        total+=localEngine.theCategories[m].counter;
                        personas[i].counts[m]=localEngine.theCategories[m].counter;
                        observedCounts[m]=(long) localEngine.theCategories[m].counter;
                    }
                    for (int m=0; m<counts.length; m++){
                        counts[m]=proportions[m]*total;
                    }
//                    probabilities[i]=0;
//                    for (int m=0; m<counts.length; m++){
//                        probabilities[i]+=Math.pow((counts[m]-observedCounts[m]), 2);
//                    }
//                    probabilities[i]=Math.sqrt(probabilities[i]);
                    for (int j=0; j<counts.length; j++){
                        if (counts[j]<5){
                            counts[j]=6;
                        }
                        if (observedCounts[j]<5){
                            observedCounts[j]=6;
                        }
                    }
                    probabilities[i]=testEngine.chiSquareTest(counts, observedCounts);
                }
            }
            catch(TwitterException e){
                System.out.println("Error!");
                System.out.println(e);
            }
            catch (NotPositiveException e){
                System.out.println("Error!");
                System.out.println(e);
            }
            catch (IllegalArgumentException e){
                System.out.println(e);
                
            }
            return probabilities;
        }
    }
