package jku.ss09.mir.lastfmecho.bo.feature;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import net.roarsoftware.lastfm.Artist;

import jku.ss09.mir.lastfmecho.bo.LastFMParser;
import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.utils.CollectionUtils;

public class LastFMTagCloudFeature extends Feature{
	
	
	private Map<String,Integer> topTags;
	private Map<String,Integer> topTagsNorm;
	
	
	public LastFMTagCloudFeature(MirArtist artist) {
		super(artist,FeatureFactory.FEATURE_TAGCLOUD,"TagCloud");
	}
	
	
	@Override
	public boolean calc() {
		
		
		setTags(Artist.getTopTags(artist.getName(), LastFMParser.getApiKey()));
		normalizeTags();
		
		return false;
	}
	
	
	public Map<String, Integer> getTopTags() {
		return topTags;
	}


	public Map<String, Integer> getTopTagsNorm() {
		return topTagsNorm;
	}
	
	
	private void setTags(Map<String,Integer> tags){

		
//		topTags = new HashMap<String,Integer>();
//		Collection<String> tagNames = tags.keySet();
//		
//		for(String name : tagNames){
//			if(tags.get(name) > 0)
//				topTags.put(name, tags.get(name));
//		}
		
		
		topTags = CollectionUtils.sortByValue(tags);
		filterAndRemoveTags(topTags);
	}
	
	
	/**
	 * 
	 * Removes tags related to artist name or name substring
	 * 
	 * @param tags
	 */
	private void filterAndRemoveTags(Map<String, Integer> tags) {
		
		//remove tags that contain name or part of names from artist 
		
		
		// see http://java.sun.com/developer/technicalArticles/releases/1.4regex/
		/*
		 * Uses split to break up a string of input separated by
		 * commas and/or whitespace.
		 * 
		 * java has substring
		 *http://www.exampledepot.com/egs/java.lang/HasSubstr.html 
		 *
		 */
		
		String artistName = this.getArtist().getName();
		Pattern p = Pattern.compile("[,\\s]+");
		String[] result = p.split(artistName);

		for (Iterator<String> iterator = tags.keySet().iterator(); iterator.hasNext();) {
			String tagString = iterator.next();
			for (int i = 0; i < result.length; i++) {
				if (tagString.matches("(?i).*"+ result[i].toLowerCase()  +".*")) {
					//remove
					System.out.println("\t \t removed " + tagString);
					iterator.remove();
					break;
				}
			}
		}
		
	}


	private void normalizeTags(){
		double sum = 0;
		topTagsNorm = new HashMap<String,Integer>();
		int count = topTags.size();
		
		for(String tagName : topTags.keySet()){
			sum += topTags.get(tagName) * topTags.get(tagName);
		}
		sum = Math.sqrt(sum);
		
		double factor = (double)100 / sum; // normalization to value 100
		
		for(String tagName : topTags.keySet()){
			topTagsNorm.put(tagName, (int)(topTags.get(tagName) * factor));
		}
	}




	
	
}
