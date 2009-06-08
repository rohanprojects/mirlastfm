package jku.ss09.mir.lastfmecho.bo;

import java.util.Collection;
import java.util.HashMap;


import net.roarsoftware.lastfm.Artist;

public class MirArtist {

	
	private MirGenre genre; 
	private String name;
	private HashMap<String,Integer> topTags;
	private HashMap<String,Integer> topTagsNorm;
	
	
	public MirArtist(String name) {
		super();
		this.name = name;
		
//		setTags((HashMap<String, Integer>)Artist.getTopTags(name, LastFMParser.getApiKey()));
//		normalizeTags();
	}


	public MirGenre getGenre() {
		return genre;
	}


	public void setGenre(MirGenre genre) {
		this.genre = genre;
		genre.add(this);
	}


	public String getName() {
		return name;
	}
	
	private void setTags(HashMap<String,Integer> tags){
		topTags = new HashMap<String,Integer>();
		Collection<String> tagNames = tags.keySet();
		
		for(String name : tagNames){
			if(tags.get(name) > 0)
				topTags.put(name, tags.get(name));
		}
	}

	public HashMap<String,Integer> getTags(){
		return topTags;
	}

	public HashMap<String,Integer> getTagsNorm(){
		return topTagsNorm;
	}
	
	public float compareArtistCS(MirArtist otherArtist){
		double similarity = 0;
		int numerator = 0;
		double thisDenominator = 0;
		double otherDenominator = 0;
		
		HashMap<String,Integer> otherTags = otherArtist.getTags();
		
		// calculate cosine similarity
		for(String tagName : topTags.keySet()){
			if(otherTags.containsKey(tagName)){
				numerator += topTags.get(tagName) * otherTags.get(tagName);
				
			}
			thisDenominator += topTags.get(tagName)* topTags.get(tagName);
		}
		
		thisDenominator = Math.sqrt(thisDenominator);
		for(String otherTagName : otherTags.keySet()){
			otherDenominator += otherTags.get(otherTagName) * otherTags.get(otherTagName); 
		}
		otherDenominator = Math.sqrt(otherDenominator);
		
		similarity = numerator / (thisDenominator * otherDenominator);
		System.out.println("Cosine similarity between '"+name+ "' and '"+otherArtist.getName()+"': "+similarity );
		
		return (float)similarity;
	}
	
	public float compareArtistED(MirArtist otherArtist){
		double similarity = 0;
		int count = 0;
		
		
		HashMap<String,Integer> otherTags = otherArtist.getTagsNorm();
		
		
		for(String tagName : topTagsNorm.keySet()){
			if(otherTags.containsKey(tagName))
				similarity += (topTagsNorm.get(tagName) - otherTags.get(tagName)) * (topTagsNorm.get(tagName) - otherTags.get(tagName));
			else
				similarity += topTagsNorm.get(tagName) * topTagsNorm.get(tagName);
			count++;
		}
		
		for(String otherName : otherTags.keySet()){
			if(!topTagsNorm.containsKey(otherName)){
				similarity += otherTags.get(otherName) * otherTags.get(otherName);
				count++;
			}
		}
		similarity = similarity / count;
		similarity = Math.sqrt(similarity);
		
		
		System.out.println("Euclidean distance between '"+name+ "' and '"+otherArtist.getName()+"': "+similarity );
		
		return (float) similarity;
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
