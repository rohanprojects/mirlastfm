package jku.ss09.mir.lastfmecho.main;

import java.util.Collection;

import jku.ss09.mir.lastfmecho.bo.LastFMParser;
import net.roarsoftware.lastfm.Album;
import net.roarsoftware.lastfm.Artist;
import net.roarsoftware.lastfm.Event;
import net.roarsoftware.lastfm.Session;

public class TestLastFMFetching {

	
	
	public static void testAll()
	{
		
		String artistString= "Incubus";
		
		//Artist
		//- get top albums 
		//- get similar albums
		//- get Top Genre!!! Tags
		testExtractArtistInfo(artistString);
		
		//fetches a session of a individual User - Needed for individual user queries that we do not need
		testFetchSession();
	}
	
	
	
	public static void testExtractArtistInfo(String artistString) {
		

		int idx = 0;
		System.out.println("----TOP ALBUMS------------");
		// get top albums of artist
		Collection<Album> topAlbums = Artist.getTopAlbums(artistString,LastFMParser.getApiKey());
		System.out.println(artistString +" hast albums:" + topAlbums.size());
		for (Album album : topAlbums) {
			System.out.println(idx + ": "+ album.getName());
			idx++;
		}
		
		System.out.println("----SIMILAR ARTISTS------------");
		Collection<Artist> similar = Artist.getSimilar(artistString, LastFMParser.getApiKey());
		
		idx = 0;
		for (Artist artist2 : similar) {
			System.out.println(idx + ": " + artist2.getName());
			idx++;
		}
		
		System.out.println("----TOP TAGS-----------");
		idx = 0;
		Collection<String> topTags = Artist.getTopTags(artistString, LastFMParser.getApiKey());
		for (String tagString : topTags) {
			System.out.println(idx + ": " +tagString);
			idx++;
		}
		
		System.out.println("----EVENTS -----------");
		Collection <Event> eventColl = Artist.getEvents(artistString, LastFMParser.getApiKey());

		for (Event event : eventColl) {
			
			System.out.println(event.getStartDate().toString() + " | " +event.getTitle() + " |  " +event.getVenue().getCountry() );
			//Event info = event.getInfo(Integer.toString(event.getId()),LastFMParser.getApiKey());	
		}
		
		//		Chart<Artist> chart = User.getWeeklyArtistChart(user, 10, apiKey);
		//		DateFormat format = DateFormat.getDateInstance();
		//		String from = format.format(chart.getFrom());
		//		String to = format.format(chart.getTo());
		//		System.out.printf("Charts for %s for the week from %s to %s:%n", user, from, to);
		//		Collection<Artist> artists = chart.getEntries();
		//		for (Artist artist : artists) {
		//			System.out.println(artist.getName());
		//		}
		//		
		//
	}

	public static void testFetchSession()
	{
		
//		/**
//		 * FETCH SESSIOn METHOD 1 - WORKS
//		 */
		String artistString= "Incubus";
		Session session = LastFMParser.getMobileSession();
		if (session != null) {
			Collection<String> tags = Artist.getTags(artistString, session);
			for (String tagName : tags) {
				System.out.println(tagName);
			}
		}
		
		
		
//		/**
//		 * FETCH SESSIOn METHOD 2 -DOES NOT WORK
//		 */
	//	
	//	
//		String token = Authenticator.getToken(apiKey);
	//	
//		//generate secret with http://www.lastfm.de/api/desktopauth#6
//		// http://www.lastfm.es/group/Last.fm+Web+Services/forum/21604/_/455058/1#f9100608
//		String secret = "api_key" + apiKey +"methodauth.getSessiontoken"+ token + apiSecret;
//		System.out.println(secret);
//		String md5Secret = MD5.calc(secret);
	//	
//			System.out.println(md5Secret);
//		Session session = Authenticator.getSession(token, apiKey, md5Secret);
//		if (session != null) {
//			Collection<String> tags = Artist.getTags(artistString, session);
//			for (String string : tags) {
//				System.out.println(tags);
//			}
//		} else 
//		{
//			System.out.println("No session object found");
//		}
	//	
		
	}
	
	
}
