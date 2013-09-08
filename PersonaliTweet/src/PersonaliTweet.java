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
            List<Status> statuses = twitter.getUserTimeline(handle, new Paging(x));// twitter.getHomeTimeline();
            for (Status status : statuses) {
                sList.add(status.getText());
                //System.out.println(status.getUser().getName() + ":" +
                //	status.getText());
            }
        }
        analyzer test = new analyzer("categories.txt");
        test.analyze(sList);
        for (int i=0; i<test.theCategories.length; i++){
            System.out.println(test.theCategories[i].name+" "+test.theCategories[i].counter);
        }
        return test.theCategories;
    }

    public static void main(String[] args) throws TwitterException, IOException {
        
        login("tester",false);
        String[] users = new String[1];
        users[0]="deadmau5";
        analyzer engine=new analyzer("categories.txt");
        String theTest="I am very happy, am awesome, fun, great. I like to write";
        Personality ours=new Personality(8);
        ours.proportions[0]=0.09;
        ours.proportions[1]=0.08;
        ours.proportions[2]=0.013;
        ours.proportions[3]=0.52;
        ours.proportions[4]=0.018;
        ours.proportions[5]=0.12;
        ours.proportions[6]=0.066;
        ours.proportions[7]=0.088;
        double[] output=new double[1];
        output=ours.userToPersonalityMatch(users, engine);
        System.out.println(output[0]);
        //getTweets("midwesttraveler");
//        users[0] = "deadmau5";
//        
//        String theText = "I just fucking hate life. I am so sad all fucking day crap. I release music, drums bass albums i rock";
//        double[] prob = new double[1];
//        analyzer sth = new analyzer("categories.txt");
//        Personality depressedDude = new Personality(sth.theCategories.length);
//        prob = depressedDude.tweetToUserMatch(theText, users, sth);
//        System.out.println(prob[0]);
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



//End of the line. Code above this line should work...


//End of the line. Code above this line should work...
