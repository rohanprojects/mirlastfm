package jku.ss09.mir.lastfmecho.bo.similarity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.feature.Feature;

public class ConsineSimilarityTagCloud extends AbstractSimilartityMeasure {


	public ConsineSimilarityTagCloud(int id, String name, List<MirArtist> artistList) {
		super(id, name, artistList);
	}

	@Override
	public boolean runCalc() {

		for (int i = 0; i < artistList.size(); i++) {		
			for (int j = 0; j < artistList.size(); j++) {
				//TODO if computational costly -break if i <=j

				MirArtist artistA = artistList.get(i);
				MirArtist artistB = artistList.get(j);
				double similarity = calcSimilarity(artistA, artistB);
				resultMatrix[i][j] = similarity;
			}
		}
		return false;

	}

	@Override
	protected double calcSimilarity(MirArtist artistA, MirArtist artistB) {
		double similarity = 0;
		int numerator = 0;
		double thisDenominator = 0;
		double otherDenominator = 0;
		
		HashMap<String,Integer> artistATags = artistA.getTags();
		HashMap<String,Integer> artistBTags = artistB.getTags();
		
		// calculate cosine similarity
		for(String tagName : artistATags.keySet()){
			if(artistBTags.containsKey(tagName)){
				numerator += artistATags.get(tagName) * artistBTags.get(tagName);
				
			}
			thisDenominator += artistATags.get(tagName)* artistATags.get(tagName);
		}
		
		thisDenominator = Math.sqrt(thisDenominator);
		for(String otherTagName : artistBTags.keySet()){
			otherDenominator += artistBTags.get(otherTagName) * artistBTags.get(otherTagName); 
		}
		otherDenominator = Math.sqrt(otherDenominator);
		
		similarity = numerator / (thisDenominator * otherDenominator);
		System.out.println("Cosine similarity between '" + artistA.getName() + "' and '"+artistB.getName()+"': "+similarity );
		
		return similarity;
		
	}
}
