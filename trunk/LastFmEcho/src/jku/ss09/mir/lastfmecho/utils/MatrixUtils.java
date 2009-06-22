package jku.ss09.mir.lastfmecho.utils;

public class MatrixUtils {

	/**
	 * 
	 * @param matrix
	 * @param range true if range from current min to max value should be distributed from 0.0 to 1.0, 
	 * otherwise only max value (and 0 as min value) is considered
	 */
	public static void normalizeToRange(double[][] matrix, boolean range){
		double minValue = 0;
		double maxValue = 1;
		for(int i=0; i < matrix.length; i++){
			for(int j=0; j < matrix[i].length; j++){
				if(matrix[i][j] > maxValue)
					maxValue = matrix[i][j];
				if(matrix[i][j] < minValue);
					minValue = matrix[i][j];
			}
		}
		
		System.out.println("RANGE: " + minValue + " - " + maxValue);
		for(int i=0; i < matrix.length; i++){
			for(int j=0; j < matrix[i].length; j++){
				if(range)
					matrix[i][j] = matrix[i][j] / (maxValue-minValue);
				else
					matrix[i][j] = matrix[i][j] / maxValue;
			}
		}	
	}
	
	public static void invertNormalizedValues(double[][] matrix){
		for(int i=0; i < matrix.length; i++){
			for(int j=0; j < matrix[i].length; j++){
				if(matrix[i][j] >= 0.0)
					matrix[i][j] = 1.0 - matrix[i][j];
			}
		}
	}
	
	
}
