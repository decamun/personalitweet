import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.api.TimelinesResources;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


public class twitterTest {



	public static void main (String[] args) throws TwitterException, IOException
	{
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
		if(n.equals("n"))
		{

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
			try{
				if(pin.length() > 0){
					accessToken = twitter.getOAuthAccessToken(requestToken, pin);
				}else{
					accessToken = twitter.getOAuthAccessToken();
				}
			} catch (TwitterException te) {
				if(401 == te.getStatusCode()){
					System.out.println("Unable to get the access token.");
				}else{
					te.printStackTrace();
				}
			}
		}

		//persist to the accessToken for future reference.
		ArrayList<String> sList = new ArrayList<String>();

		//System.out.println(twitter.verifyCredentials().getId());
		storeAccessToken(accountName , accessToken);
                for(int x=1;x<10;x++)
                {
		List<Status> statuses = twitter.getUserTimeline("DeadMau5", new Paging(x));// twitter.getHomeTimeline();
                for (Status status : statuses) {
			sList.add(status.getText());
			//System.out.println(status.getUser().getName() + ":" +
				//	status.getText());
		}
                }
		
		//System.out.println("Showing home timeline.");
		
		for(int x=0;x<sList.size();x++)
		{
			System.out.println(sList.get(x));
		}
		System.exit(0);
	}
	private static void storeAccessToken(String useId, AccessToken accessToken) throws IOException{
		FileOutputStream out = new FileOutputStream(new File("auth.dat"));
		ObjectOutputStream outStream = new ObjectOutputStream(out);

		//store accessToken.getToken()
		//store accessToken.getTokenSecret()
		outStream.writeObject(useId);
		outStream.writeObject(accessToken.getToken());
		outStream.writeObject(accessToken.getTokenSecret());
		outStream.close();
	}
	private static AccessToken loadAccessToken(String useId) throws IOException
	{
		FileInputStream in = new FileInputStream("auth.dat");
		
		try {
			ObjectInputStream inStream = new ObjectInputStream(in);
			
			if(useId.equals((String)inStream.readObject()))	
					return new AccessToken((String)inStream.readObject(),(String)inStream.readObject());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}

