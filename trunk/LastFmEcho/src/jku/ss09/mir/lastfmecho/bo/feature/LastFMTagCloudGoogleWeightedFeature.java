package jku.ss09.mir.lastfmecho.bo.feature;

import java.util.Collection;
import java.util.HashMap;

import net.roarsoftware.lastfm.Artist;

import jku.ss09.mir.lastfmecho.bo.LastFMParser;
import jku.ss09.mir.lastfmecho.bo.MirArtist;

public class LastFMTagCloudGoogleWeightedFeature extends Feature{
	
	
	private HashMap<String,Integer> topTags;
	private HashMap<String,Integer> topTagsNorm;
	
	
	public LastFMTagCloudGoogleWeightedFeature(MirArtist artist) {
		super(artist,FeatureFactory.FEATURE_TAGCLOUD,"TagCloud");
	}
	
	
	@Override
	public boolean calc() {		
		setTags((HashMap<String, Integer>)Artist.getTopTags(artist.getName(), LastFMParser.getApiKey()));
		
		
		

		
		//normalizeTags();
		
		return false;
	}	
	
	private void setTags(HashMap<String,Integer> tags){
		topTags = new HashMap<String,Integer>();
		Collection<String> tagNames = tags.keySet();
		
		for(String name : tagNames){
			if(tags.get(name) > 0)
				topTags.put(name, tags.get(name));
		}
	}
	
	public HashMap<String, Integer> getTopTags() {
		return topTags;
	}

	public HashMap<String, Integer> getTopTagsNorm() {
		return topTagsNorm;
	}
	
	
//	private void normalizeTags(){
//		double sum = 0;
//		topTagsNorm = new HashMap<String,Integer>();
//		int count = topTags.size();
//		
//		for(String tagName : topTags.keySet()){
//			sum += topTags.get(tagName) * topTags.get(tagName);
//		}
//		sum = Math.sqrt(sum);
//		
//		double factor = (double)100 / sum; // normalization to value 100
//		
//		for(String tagName : topTags.keySet()){
//			topTagsNorm.put(tagName, (int)(topTags.get(tagName) * factor));
//		}
//	}




	
	
}
