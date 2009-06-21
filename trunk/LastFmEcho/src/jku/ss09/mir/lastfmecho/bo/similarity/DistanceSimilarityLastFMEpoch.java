package jku.ss09.mir.lastfmecho.bo.similarity;

import java.util.ArrayList;
import java.util.List;

import jku.ss09.mir.lastfmecho.bo.MirArtist;

public class DistanceSimilarityLastFMEpoch extends AbstractSimilartityMeasure {


	public DistanceSimilarityLastFMEpoch(int id, String name, List<MirArtist> artistList) {
		super(id, name, artistList);
	}

	@Override
	public boolean runCalc() {

		boolean returnValue = true;
		
		for (int i = 0; i < artistList.size(); i++) {		
			for (int j = 0; j < artistList.size(); j++) {
				MirArtist artistA = artistList.get(i);
				MirArtist artistB = artistList.get(j);
				double similarity = calcSimilarity(artistA, artistB);
				
				resultMatrix[i][j] = similarity;
				if (similarity == Double.MIN_VALUE)
				{
					System.out.println("Error in processing Epoch Similarity");
					
				} 		
			}
		}
		return returnValue;

	}

	@Override
	protected double calcSimilarity(MirArtist artistA, MirArtist artistB) {
		int similarity = 0;

		if (artistA.getEpochFeature() != null && artistB.getEpochFeature() != null) {
			
			if (artistA.getEpochFeature().getReleaseDates() != null && artistA.getEpochFeature().getReleaseDates() != null) {
				ArrayList<Integer> artistADates = artistA.getEpochFeature().getReleaseDates();
				ArrayList<Integer> artistBDates = artistB.getEpochFeature().getReleaseDates();
				
				//calculate mean release year
				int artistAMean = calcMean(artistADates);
				int artistBMean = calcMean(artistBDates);
				
				//calculate difference of mean release years
				similarity = Math.abs(artistAMean-artistBMean);
				System.out.println("Epoch similarity between " + artistA.getName() + " and " + artistB.getName()+  ": " + similarity);
								
				return similarity;
			} else {
				System.out.println("Error: DistSimilarityEpoch: no releaseDates found");
				return Double.MIN_VALUE;
			}
		}  else {
			System.out.println("Error: DistSimilarityEpoch: no releaseDates feature found");
			return Double.MIN_VALUE;
		}		
	}
	
	
	private int calcMean(ArrayList<Integer> releaseDates){
		if(releaseDates != null){
			int sumYears = 0;
			
			for(Integer year : releaseDates)
				sumYears += year;
			
			return (int) sumYears / releaseDates.size();
		}
		return 0;
	}
}
