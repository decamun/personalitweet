
import java.util.ArrayList;
import twitter4j.*;
import java.io.*;
import java.util.List;
import java.util.regex.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

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

        category[] theCategories;

        public analyzer(String initialFile) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(new File(initialFile)));
                theCategories = new category[Integer.parseInt(in.readLine())];
                for (int i = 0; i < theCategories.length; i++) {
                    theCategories[i] = new category(in.readLine());
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
                        }
                    }
                }
            }
        }
    }

    static Window window = new Window(1000, 1000);
    //Main function
    public static void main(String[] args) throws TwitterException, IOException {


        String key = "MzWmQeFJF56Rq82CCdpA";
        String secret = "z3WiDz31MIXgNAWasNt1M0vcY0VQOLJPoZqETAROc";

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("new account?");
        String n = r.readLine();
        String accountName = null;
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(key, secret);

        RequestToken requestToken = twitter.getOAuthRequestToken();

        AccessToken accessToken = null;
        if (n.equals("n")) {

            System.out.print("Enter account name: ");
            accountName = r.readLine();
            accessToken = loadAccessToken(accountName);
            twitter.setOAuthAccessToken(accessToken);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (null == accessToken) {
            System.out.print("Enter account name: ");
            accountName = br.readLine();
            System.out.println("Open the following URL and grant access to your account:");
            System.out.println(requestToken.getAuthorizationURL());
            System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
            String pin = br.readLine();
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
        }

        //persist to the accessToken for future reference.
        ArrayList<String> sList = new ArrayList<String>();

        //System.out.println(twitter.verifyCredentials().getId());
        storeAccessToken(accountName, accessToken);
        for (int x = 1; x < 10; x++) {
            List<Status> statuses = twitter.getUserTimeline("DeadMau5", new Paging(x));// twitter.getHomeTimeline();
            for (Status status : statuses) {
                sList.add(status.getText());
                //System.out.println(status.getUser().getName() + ":" +
                //	status.getText());
            }
        }

        //System.out.println("Showing home timeline.");
//		
        for (int x = 0; x < sList.size(); x++) {
            System.out.println(sList.get(x));
        }
        //System.exit(0);
        
        System.out.println("blah");
        Sprite testObj = new Sprite();
        window.repaint();
        analyzer test = new analyzer("categories.txt");
        test.analyze(sList);
        System.out.println(test.theCategories[0].counter);

//	for (int i=0; i<test.theCategories.length; i++){
//            System.out.println(test.theCategories[i].name);
//            for (int m=0; m<test.theCategories[i].words.length; m++){
//                System.out.println(test.theCategories[i].words[m]);
//                //this is a change
//                //this is another change
//            }
//	}
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
