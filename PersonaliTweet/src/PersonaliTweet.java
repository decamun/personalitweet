
import java.util.ArrayList;
import twitter4j.*;
import java.io.*;
import java.util.List;
import java.util.regex.*;
import javax.swing.JOptionPane;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import org.apache.commons.math3.stat.inference.*;
import org.apache.commons.math3.exception.*;



//PennApps Fall 2013 alksdjf;lsaf;lsaf;lsadjf
//this works muthafucka!

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Decamun
 */
public class PersonaliTweet {

    /**
     * @param args the command line arguments
     */
    static class category {

        String[] words;
        int counter;
        String name;

        public category(String cat) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(cat)));
                words = new String[Integer.parseInt(in.readLine())];
                for (int i = 0; i < words.length; i++) {
                    words[i] = in.readLine();
                }
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("IO Exception motherfucker...");
            }
            name = cat;
        }
    }

    static class analyzer {

        Category[] theCategories;

        public analyzer(String initialFile) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(initialFile)));
                theCategories = new Category[Integer.parseInt(in.readLine())];
                for (int i = 0; i < theCategories.length; i++) {
                    theCategories[i] = new Category(in.readLine());
                }
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found dumbass");
            } catch (IOException e) {
                System.out.println("IO Exception motherfucker...");
            }
        }

        void analyze(ArrayList<String> tweets) {
            Pattern p;
            Matcher engine;
            for (int x = 0; x < tweets.size(); x++) {
                for (int i = 0; i < theCategories.length; i++) {
                    for (int m = 0; m < theCategories[i].words.length; m++) {
                        p = Pattern.compile(theCategories[i].words[m], Pattern.CASE_INSENSITIVE);
                        engine = p.matcher(tweets.get(x));
                        while (engine.find()) {
                            theCategories[i].counter++;
                            System.out.println(theCategories[i].words[m]);
                        }
                    }
                }
            }
        }
    }

    static class Personality {

        double[] proportions;
        double[] counts;
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
                    for (int x = 1; x < 50; x++) {
                        List<Status> statuses = twitter.getUserTimeline(users[i], new Paging(x));// twitter.getHomeTimeline();
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
                    for (int x = 1; x < 50; x++) {
                        List<Status> statuses = twitter.getUserTimeline("Fefi428", new Paging(x));// twitter.getHomeTimeline();
                        for (Status status : statuses) {
                            sList.add(status.getText());
                            //System.out.println(status.getUser().getName() + ":" +
                            //	status.getText());
                
                        }
                    }
                    localEngine.analyze(sList);
                    long observedCounts[]=new long[counts.length];
                    int total=0;
                    for (int m=0; m<personas[i].counts.length; m++){
                        total+=localEngine.theCategories[m].counter;
                        personas[i].counts[m]=localEngine.theCategories[m].counter;
                        observedCounts[m]=(long) localEngine.theCategories[m].counter;
                    }
                    for (int m=0; m<counts.length; m++){
                        counts[m]=proportions[m]*total;
                    }
                    probabilities[i]=testEngine.chiSquareTest(counts, observedCounts);
                }
            }
            catch(TwitterException e){
                System.out.println("Error!");
            }
            catch (NotPositiveException e){
                System.out.println("Error!");
            }
            catch (IllegalArgumentException e){
                
            }
            return probabilities;
        }
    }
    
    
    //Main function
    public static Twitter twitter;

    public static void login(String user, boolean newuser) throws TwitterException, IOException {
        //if logged in
        //log out
        
        String key = "MzWmQeFJF56Rq82CCdpA";
        String secret = "z3WiDz31MIXgNAWasNt1M0vcY0VQOLJPoZqETAROc";

        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(key, secret);
        RequestToken requestToken = twitter.getOAuthRequestToken();
        AccessToken accessToken = null;

        if (!newuser) {
            accessToken = loadAccessToken(user);
            twitter.setOAuthAccessToken(accessToken);
        } else {

            String pin = JOptionPane.showInputDialog("Go to following URL", requestToken.getAuthorizationURL());
            try {
                if (pin.length() > 0) {
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                } else {
                    accessToken = twitter.getOAuthAccessToken();
                }
            } catch (TwitterException te) {
                if (401 == te.getStatusCode()) {
                    System.out.println("Unable to get the access token.");
                } else {
                    te.printStackTrace();
                }
            }
            storeAccessToken(user, accessToken);
        }
    }

    public static Category[] getTweets(String handle) throws TwitterException {
        ArrayList<String> sList = new ArrayList<>();
        for (int x = 1; x < 50; x++) {
            List<Status> statuses = twitter.getUserTimeline("Fefi428", new Paging(x));// twitter.getHomeTimeline();
            for (Status status : statuses) {
                sList.add(status.getText());
                //System.out.println(status.getUser().getName() + ":" +
                //	status.getText());
            }
        }
        analyzer test = new analyzer("categories.txt");
        test.analyze(sList);
        return test.theCategories;
    }

    public static void main(String[] args) throws TwitterException, IOException {
        
        login("test",false);
        String[] users = new String[1];
        users[0] = "deadmau5";
        
        String theText = "I just fucking hate life. I am so sad all fucking day crap. I release music, drums bass albums i rock";
        double[] prob = new double[1];
        analyzer sth = new analyzer("categories.txt");
        Personality depressedDude = new Personality(sth.theCategories.length);
        prob = depressedDude.tweetToUserMatch(theText, users, sth);
        System.out.println(prob[0]);
          
//        String key = "MzWmQeFJF56Rq82CCdpA";
//        String secret = "z3WiDz31MIXgNAWasNt1M0vcY0VQOLJPoZqETAROc";
//
//        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("new account?");
//        String n = r.readLine();
//        String accountName = null;
//        Twitter twitter = TwitterFactory.getSingleton();
//        twitter.setOAuthConsumer(key, secret);
//
//        RequestToken requestToken = twitter.getOAuthRequestToken();
//
//        AccessToken accessToken = null;
//        if (n.equals("n")) {
//
//            System.out.print("Enter account name: ");
//            accountName = r.readLine();
//            accessToken = loadAccessToken(accountName);
//            twitter.setOAuthAccessToken(accessToken);
//        }
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        while (null == accessToken) {
//            System.out.print("Enter account name: ");
//            accountName = br.readLine();
//            System.out.println("Open the following URL and grant access to your account:");
//            System.out.println(requestToken.getAuthorizationURL());
//            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
//            String pin = br.readLine();
//            try {
//                if (pin.length() > 0) {
//                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
//                } else {
//                    accessToken = twitter.getOAuthAccessToken();
//                }
//            } catch (TwitterException te) {
//                if (401 == te.getStatusCode()) {
//                    System.out.println("Unable to get the access token.");
//                } else {
//                    te.printStackTrace();
//                }
//            }
//        }
//
//        //persist to the accessToken for future reference.
//        ArrayList<String> sList = new ArrayList<String>();
//
//        //System.out.println(twitter.verifyCredentials().getId());
//        storeAccessToken(accountName, accessToken);
//        for (int x = 1; x < 50; x++) {
//            List<Status> statuses = twitter.getUserTimeline("Fefi428", new Paging(x));// twitter.getHomeTimeline();
//            for (Status status : statuses) {
//                sList.add(status.getText());
//                //System.out.println(status.getUser().getName() + ":" +
//                //	status.getText());
//            }
//        }
//        System.out.println(twitter.getRateLimitStatus());
//
//        //System.out.println("Showing home timeline.");
////		
//        //for (int x = 0; x < sList.size(); x++) {
//        //  System.out.println(sList.get(x));
//        //}
//        //System.exit(0);
//        analyzer test = new analyzer("categories.txt");
//        test.analyze(sList);
//        for (int i = 0; i < test.theCategories.length; i++) {
//            System.out.println(test.theCategories[i].name + " " + test.theCategories[i].counter);
//        }
//
////	for (int i=0; i<test.theCategories.length; i++){
////            System.out.println(test.theCategories[i].name);
////            for (int m=0; m<test.theCategories[i].words.length; m++){
////                System.out.println(test.theCategories[i].words[m]);
////                //this is a change
////                //this is another change
////            }
////	}
    }

    private static void storeAccessToken(String useId, AccessToken accessToken) throws IOException {
        FileOutputStream out = new FileOutputStream(new File("auth.dat"));
        ObjectOutputStream outStream = new ObjectOutputStream(out);

        //store accessToken.getToken()
        //store accessToken.getTokenSecret()
        outStream.writeObject(useId);
        outStream.writeObject(accessToken.getToken());
        outStream.writeObject(accessToken.getTokenSecret());
        outStream.close();
    }

    private static AccessToken loadAccessToken(String useId) throws IOException {
        FileInputStream in = new FileInputStream("auth.dat");

        try {
            ObjectInputStream inStream = new ObjectInputStream(in);

            if (useId.equals((String) inStream.readObject())) {
                return new AccessToken((String) inStream.readObject(), (String) inStream.readObject());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }
}



//End of the line. Code above this line should work...
