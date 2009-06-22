package jku.ss09.mir.lastfmecho.bo.similarity;

import java.util.ArrayList;
import java.util.List;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.utils.MatrixUtils;

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
				
				if (similarity == -1)
					System.err.println("Error in processing Epoch Similarity between " + artistA.getName() + " and " + artistB.getName()); 		
			}
		}
		
		MatrixUtils.normalizeToRange(resultMatrix, false);
		MatrixUtils.invertNormalizedValues(resultMatrix);
		
		return returnValue;

	}

	@Override
	protected double calcSimilarity(MirArtist artistA, MirArtist artistB) {
		int similarity = -1;

		if (artistA.getEpochFeature() != null && artistB.getEpochFeature() != null) {
			
			if (artistA.getEpochFeature().getReleaseDates() != null && artistA.getEpochFeature().getReleaseDates() != null) {
				ArrayList<Integer> artistADates = artistA.getEpochFeature().getReleaseDates();
				ArrayList<Integer> artistBDates = artistB.getEpochFeature().getReleaseDates();
				
				//calculate mean release year
				if(artistADates != null && artistADates.size() > 0 && artistBDates != null && artistBDates.size() > 0){
					int artistAMean = calcMean(artistADates);
					int artistBMean = calcMean(artistBDates);
				
					//calculate difference of mean release years
					similarity = Math.abs(artistAMean-artistBMean);
					System.out.println("Epoch distance between " + artistA.getName() + " and " + artistB.getName()+  ": " + similarity);
				} 							
				return similarity;
			} else {
				System.err.println("Error: DistSimilarityEpoch: no releaseDates found");
				return similarity;
			}
		}  else {
			System.err.println("Error: DistSimilarityEpoch: no releaseDates feature found");
			return similarity;
		}		
	}
	
	
	private int calcMean(ArrayList<Integer> releaseDates){
		if(releaseDates != null && releaseDates.size() > 0){
			int sumYears = 0;
			
			for(Integer year : releaseDates)
				sumYears += year;
			
			return (int) sumYears / releaseDates.size();
		}
		return -1;
	}
	
//	private void normalizeResultMatrix(){
//		double maxValue = 1;
//		for(int i=0; i < resultMatrix.length; i++){
//			for(int j=0; j < resultMatrix[i].length; j++){
//				if(resultMatrix[i][j] > maxValue)
//					maxValue = resultMatrix[i][j];
//			}
//		}
//		
//		for(int i=0; i < resultMatrix.length; i++){
//			for(int j=0; j < resultMatrix[i].length; j++){
//				resultMatrix[i][j] /= maxValue;
//			}
//		}
//		
//		
//	}
}
