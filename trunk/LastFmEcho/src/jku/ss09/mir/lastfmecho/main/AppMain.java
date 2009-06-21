package jku.ss09.mir.lastfmecho.main;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import comirva.util.external.URLRetriever;

import jku.ss09.mir.lastfmecho.bo.LastFMParser;
import jku.ss09.mir.lastfmecho.bo.MusicFileParser;
import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.MirGenre;
import jku.ss09.mir.lastfmecho.comirva.utils.GoogleUrlRetriever;
import jku.ss09.mir.lastfmecho.utils.UrlDownloader;

import net.roarsoftware.lastfm.Album;
import net.roarsoftware.lastfm.Artist;
import net.roarsoftware.lastfm.Authenticator;
import net.roarsoftware.lastfm.Chart;
import net.roarsoftware.lastfm.Event;
import net.roarsoftware.lastfm.Session;
import net.roarsoftware.lastfm.User;

public class AppMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		
		String dirPath = System.getProperty("user.dir");
		System.out.println(dirPath);
		
		
		/**
		 * MIR MusicFileParser 
		 * fetches content from genre, artist files provided for the project
		 */
		//MusicFileParser fileParser = new MusicFileParser();
		//fileParser.run();

//		System.out.println("Genres: " + fileParser.getGenreSet().size());
//		for (MirGenre mirGenre : fileParser.getGenreSet()) {
//			System.out.println(mirGenre.getName());
////			for (MirArtist artist: mirGenre.getArtistList())
////				System.out.println("\t" + artist.getName());
//		}

		/**
		 * LastFMParser
		 * An auxiliary class for fetching content from LastFM via 
		 * User: Jakob Doppler Profile and API Key   
		 */   
		LastFMParser lastFmParser = new LastFMParser();
		String artistString= "Incubus";
		TestLastFMFetching.testExtractArtistInfo(artistString);
		
		/**
		 * Google Parser
		 * maybe get Information from Google Seach Results - With wget/CoMIRVA
		 * ATTENTION: Google Request for URL RETRIEVING NEEDS ONLY BE DONE ONCE !!! 
		 */
		

//		List<String> artistStringList = new ArrayList<String>();
//		List<MirArtist> artistList = fileParser.getArtistList();
//		for (MirArtist mirArtist : artistList) {
//			artistStringList.add(mirArtist.getName());
//		}
//		//retrieve URLS in 
//		TestURLRetrieverLastFM retriever = new TestURLRetrieverLastFM();
//		retriever.run(0,artistStringList);
//		
		
		/**
		 *  Url Downloader
		 *  Fetches n files
		 */
		//UrlDownloader urldownloader = new UrlDownloader();
		//urldownloader.runFile(downloadPath + "urls.dat" ,downloadPath);
		//urldownloader.setMaxPages(50);
		//urldownloader.runDirectory(new File(dirPath + "/data/download/"));
		
		//Playlist playlist = Playlist.create("example playlist", "description", session2);


	}

}
