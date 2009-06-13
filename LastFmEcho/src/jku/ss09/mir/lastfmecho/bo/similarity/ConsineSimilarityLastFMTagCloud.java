package jku.ss09.mir.lastfmecho.bo.similarity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.feature.Feature;

public class ConsineSimilarityLastFMTagCloud extends AbstractSimilartityMeasure {


	public ConsineSimilarityLastFMTagCloud(int id, String name, List<MirArtist> artistList) {
		super(id, name, artistList);
	}

	@Override
	public boolean runCalc() {

		boolean returnValue = true;
		
		for (int i = 0; i < artistList.size(); i++) {		
			for (int j = 0; j < artistList.size(); j++) {
				//TODO if computational costly -break if i <=j

				MirArtist artistA = artistList.get(i);
				MirArtist artistB = artistList.get(j);
				double similarity = calcSimilarity(artistA, artistB);
				
				resultMatrix[i][j] = similarity;
				if (similarity == Double.MIN_VALUE)
				{
					System.out.println("Error in processing TagCloud Cosine Similarity");
					
				} 		
			}
		}
		return returnValue;

	}

	@Override
	protected double calcSimilarity(MirArtist artistA, MirArtist artistB) {
		double similarity = 0;
		int numerator = 0;
		double thisDenominator = 0;
		double otherDenominator = 0;
		
		
		if (artistA.getTagCloudFeature() != null && artistB.getTagCloudFeature() != null) {
			
			if (artistA.getTagCloudFeature().getTopTags() != null && artistA.getTagCloudFeature().getTopTags() != null) {
				HashMap<String,Integer> artistATags = artistA.getTagCloudFeature().getTopTags();
				HashMap<String,Integer> artistBTags = artistB.getTagCloudFeature().getTopTags();
				
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
//				System.out.println("Cosine similarity between '" + artistA.getName() + "' and '"+artistB.getName()+"': "+similarity );
				
				return similarity;
			} else {
				System.out.println("Error: CosineSimilarityTagCloud: no tags found");
				return Double.MIN_VALUE;
			}
		}  else {
			System.out.println("Error: CosineSimilarityTagCloud: no tags cloud feature found");
			return Double.MIN_VALUE;
		}		
	}
}
