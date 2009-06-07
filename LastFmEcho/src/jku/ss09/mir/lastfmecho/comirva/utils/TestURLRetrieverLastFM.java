/*
 * Created on 19.04.2005
 */
package jku.ss09.mir.lastfmecho.comirva.utils;

import comirva.config.AnySearchConfig;
import comirva.util.external.TextFormatTool;

import java.io.*;
import java.net.*;
import java.util.*;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
	
/**
 * This class implements a URL retrieval for queries to a search engine.
 * 
 * @author Markus Schedl
 */
public class TestURLRetrieverLastFM {
	// number of requested web pages
	private static final int REQUESTED_PAGES = 100;
	private static final int MAX_RETURNED_PAGES = 100;														// maximum number of returned results by the search engine
	private static String STORAGE_PATH = System.getProperty("user.dir") + "/data/download/";							// local path to store the crawled web pages
//	private static String ENTITIES_TO_CRAWL = "C:/Research/Data/amg-artists/artists_reggae.txt";			// local file containing entities to crawls (e.g. artists)
	private static boolean RETRIEVE_PAGES = false;															// indicates whether the actual web pages should be retrieved (if false, only the URLs returned by the search engine are stored)
	
	private int lastIndex[];						// to store the index of the last accessible web page for each artist
	private Vector artistsUrls[];					// to store a list of already retrieved urls for every artist				
	
	
	
	
	/**
	 * Creates a new URLRetriever-instance.
	 * 
	 * @param startIndex	the index of the artist list where the crawl should start 
	 */
	public void run(int startIndex, List<String> artists) {
		// create reader to access file
			// make sure that storage path ends with a slash
			if (STORAGE_PATH.charAt(STORAGE_PATH.length()-1) != '/' && STORAGE_PATH.charAt(STORAGE_PATH.length()-1) != '\\')
				STORAGE_PATH+="/";
			// create output directory if it does not already exist
			File fileOutputPath = new File(STORAGE_PATH);
			if (!fileOutputPath.isDirectory())
				(new File(STORAGE_PATH)).mkdir();
			// init last index
			this.lastIndex = new int[artists.size()];
			// init vector of artists' urls
			this.artistsUrls = new Vector[artists.size()];
			for (int i=0; i<artists.size(); i++)
				this.artistsUrls[i] = new Vector();

		URLRetrieverConfig asCfg;			// web crawl configuration
		// multiple crawls if number of requested pages exceeds the
		// maximum number of returned pages from the search engine
		for (int i=0; i<(int)Math.floor((double)REQUESTED_PAGES/MAX_RETURNED_PAGES); i++) {
			// create configuration for web crawl
			asCfg = new URLRetrieverConfig(REQUESTED_PAGES, i*MAX_RETURNED_PAGES); 
			// start web crawl with index 0
			startCrawl(artists, asCfg, startIndex);
		}
	}
	

	
	private void startCrawl(List<String> artists, URLRetrieverConfig asCfg, int startIdx) {
		int curIdx = 0;		// to remember actual position in case of error
		try {
			for (int i=startIdx; i<artists.size(); i++) {
				curIdx = i;	// remember current position
				//************************** ******the query for the search engine
				String query = "\"" + (String)artists.get(i) + "\"+music";
				//**************************************************************//
				AnySearch as = new AnySearch(asCfg, "http://www.google.com", query);
				System.out.println(i + ": issuing query " + query + "\tretrieving pages "+(asCfg.getFirstRequestedPageNumber()+1)+" to "+(asCfg.getFirstRequestedPageNumber()+MAX_RETURNED_PAGES));
				URL[] resultURLs = as.getResultURLs(MAX_RETURNED_PAGES);
				System.out.println("accessible web pages: "+resultURLs.length);
				if (resultURLs != null && resultURLs.length != 0)
					// create directory for current artist
					(new File(STORAGE_PATH + TextFormatTool.removeUnwantedChars((String)artists.get(i)))).mkdir();
				// every url returned
				for (int j=0; j<resultURLs.length; j++) {
					// store current url in artists' url vector if it is not already contained
					if (this.artistsUrls[i].indexOf(resultURLs[j]) == -1) {
						// store new url in artists' url vector
						this.artistsUrls[i].addElement(resultURLs[j]);
						// retrieve
						if (RETRIEVE_PAGES) {
							Runtime.getRuntime().exec("wget -t 2 -T 2 -O " + STORAGE_PATH
									+ TextFormatTool.removeUnwantedChars((String)artists.get(i)) + "/" 
									+ TextFormatTool.leadingDoubleZero(Integer.toString(this.lastIndex[i])) + ".html" + " " 
									+ resultURLs[j].toString());
//							System.out.println(j+"\t"+resultURLs[j].toString());
						}
						// update index of last accessible web page
						this.lastIndex[i]++;
					} else {		// current url is already contained in artists' url vector
//						System.out.println(resultURLs[i].toString() + " already in vector");
					}
				}
				// create file of URLs in directory where retrieved pages of current search term has been stored
				File fileURLList = new File(	STORAGE_PATH + 
						TextFormatTool.removeUnwantedChars((String)artists.get(i)) + 
						"/urls.dat");
				// check if urls.dat already exists
				Vector urls = new Vector();
				if (fileURLList.exists()) {		// exists, read input (to be able to only add new urls)
					BufferedReader br = new BufferedReader(new FileReader(fileURLList));
					String url = br.readLine();
					while (url != null) {
						urls.addElement(url);
						url = br.readLine();
					}
					br.close();	
				}
				// open urls.dat for append
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileURLList, true));
				try {
					for (int j=0; j<this.artistsUrls[i].size(); j++) {
						// add url if not already in urls.dat
						String artistUrl = artistsUrls[i].elementAt(j).toString();
						if (urls != null && !urls.contains(artistUrl))
							bw.write(artistUrl+"\n");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					bw.flush();
					bw.close();
				}
				System.out.println(this.lastIndex[i]+" pages retrieved");
				System.gc();			// to avoid memory leaks
			}
		} catch (Exception e) {
			// restart crawl after position where error occurred
			startCrawl(artists, asCfg, curIdx+1);
		}
	
	}

   	
}
