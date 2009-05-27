package jku.ss09.mir.lastfmecho.bo;

import java.util.Collection;

import net.roarsoftware.lastfm.Artist;
import net.roarsoftware.lastfm.Authenticator;
import net.roarsoftware.lastfm.Session;

public class LastFMParser {

	
	private static final String apiKey = "1b34f50bf78ad8099fcf14bdd11e90be"; //this is the key used in the last.fm API examples online.
	private static final String apiSecret = "3d183fee8932affdf033bd035e1e5f83"; //this is the Secret for methods requiring user authentication
	private static final String user = "_yak";
	private static final String password = "lastechocounts";
	
	public LastFMParser()  {
		
	}
	
	
	
	public void init()
	{
		
	
	}	
	
	public static String getApiKey() {
		return apiKey;
	}



	public static String getApiSecret() {
		return apiSecret;
	}



	public static String getUser() {
		return user;
	}



	public static String getPassword() {
		return password;
	}
	
	
	public static Session getMobileSession()
	{
		
		Session session2 = Authenticator.getMobileSession(user, password, apiKey, apiSecret);
		return session2;
	}
	
	
	
	
}
