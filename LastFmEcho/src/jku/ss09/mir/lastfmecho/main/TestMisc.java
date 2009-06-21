package jku.ss09.mir.lastfmecho.main;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import comirva.util.external.TextFormatTool;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.MusicFileParser;
import jku.ss09.mir.lastfmecho.bo.feature.Feature;
import jku.ss09.mir.lastfmecho.bo.feature.FeatureFactory;
import jku.ss09.mir.lastfmecho.bo.google.FileListTermExtractor;
import jku.ss09.mir.lastfmecho.utils.CollectionUtils;



public class TestMisc {


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
		MusicFileParser fileParser = new MusicFileParser();
		fileParser.run();

	
	
		/**
		 * Test - 
		 * 1.) retrieve LastFM Tags for all artists , 
		 * 2.) calc similarities for all and 
		 * 3.) visualize them 
		 * 
		 */
		
		System.out.println("---------- LastFM Tag Cloud Feature Retrieval ----------");
		List<MirArtist> artistList = fileParser.getArtistList().subList(0, 1);
		int idx = 1;
		for (MirArtist mirArtist : artistList) {
			// 1. this creates and calculates the feature and 
			// 2. adds it to the mirArtist
			Feature feature = FeatureFactory.createFeatureForArtist(FeatureFactory.FEATURE_TAGCLOUD, mirArtist);
			//Todo exception handlingm if a feature cant be created
			
			mirArtist.addFeature(feature);
			System.out.println(idx + " Artist: " + mirArtist.getName() + "  calcFeature  " + feature.getName());
			idx++;
		}
		
		
		MirArtist artist = artistList.get(0);
		String artistName = TextFormatTool.removeUnwantedChars(artist.getName());
		String targetDir = dirPath + "/data/download/" + artistName + "/";
		
		
		FileListTermExtractor extractor = new FileListTermExtractor();
		
		HashMap<String, Integer> googleTermMap = (HashMap<String, Integer>)artist.getTagCloudFeature().getTopTags().clone();
		extractor.run(targetDir,googleTermMap);
		
		System.out.println("Term | Google Term | LastFM Term");
		//sort and display google Terms
		Map<String, Integer> sortGoogleTermMap = CollectionUtils.sortByValue(googleTermMap);
		for (Iterator<Entry<String,Integer>> it = sortGoogleTermMap.entrySet().iterator(); it.hasNext();) {
			Entry<String,Integer> entry = it.next();
			Integer lastFMCount = artist.getTagCloudFeature().getTopTags().get(entry.getKey());
			if (lastFMCount == null) {
				lastFMCount = 0;
			};
				
			
			System.out.println(entry.getKey() + " " + entry.getValue() + " " + lastFMCount);
			
		}
		
		System.out.println("--------------------------");
		
//		//sort and display last FM Terms
//		Map<String, Integer> sortlastFMTermMap = CollectionUtils.sortByValue(artist.getTagCloudFeature().getTopTags());
//		for (Iterator<Entry<String,Integer>> it = sortlastFMTermMap.entrySet().iterator(); it.hasNext();) {
//			Entry<String,Integer> entry = it.next();
//			System.out.println(entry.getKey() + " " + entry.getValue());
//			
//		}
		
		
		
		
	}
	
	

}
