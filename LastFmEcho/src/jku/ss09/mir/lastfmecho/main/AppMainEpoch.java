package jku.ss09.mir.lastfmecho.main;

import java.io.IOException;
import java.util.List;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.MusicFileParser;
import jku.ss09.mir.lastfmecho.bo.feature.Feature;
import jku.ss09.mir.lastfmecho.bo.feature.FeatureFactory;
import jku.ss09.mir.lastfmecho.bo.similarity.DistanceSimilarityLastFMEpoch;
import jku.ss09.mir.lastfmecho.utils.MatrixUtils;
import jku.ss09.mir.lastfmecho.utils.TextFileWriter;
import jku.ss09.mir.lastfmecho.bo.visualization.MirArtistNetworkGraphVisualizer;
import static org.math.array.DoubleArray.*;
import static org.math.array.LinearAlgebra.*;

/**
 * 
 * @author doris
 *
 */

public class AppMainEpoch {
	public static TextFileWriter writer;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String dirPath = System.getProperty("user.dir");
		System.out.println(dirPath);
		String targetDir = dirPath + "/data/results" + "epoch.txt";
		
		try {
			writer = new TextFileWriter(targetDir);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/**
		 * MIR MusicFileParser 
		 * fetches content from genre, artist files provided for the project
		 */
		MusicFileParser fileParser = new MusicFileParser();
		fileParser.run();

////		System.out.println("Genres: " + fileParser.getGenreSet().size());
////		for (MirGenre mirGenre : fileParser.getGenreSet()) {
////			System.out.println(mirGenre.getName());
////		for (MirArtist artist: mirGenre.getArtistList())
////				System.out.println("\t" + artist.getName());
//		}

	
		/**
		 * 1.) retrieve LastFM Albums for all artists and extract release dates (year) 
		 * 2.) calc similarities for all and 
		 * 3.) visualize them 
		 * 
		 */
		
		System.out.println("---------- LastFM Tag Epoch Retrieval based on TopAlbums ----------");
		List<MirArtist> artistList = fileParser.getArtistList();
		int idx = 1;
		for (MirArtist mirArtist : artistList) {
			writer.write(mirArtist.getName() + ": " );
			System.out.print(mirArtist.getName() + ": " );
			// 1. this creates and calculates the feature and 
			// 2. adds it to the mirArtist
			Feature feature = FeatureFactory.createFeatureForArtist(FeatureFactory.FEATURE_EPOCH, mirArtist);
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			mirArtist.addFeature(feature);
			writer.writeLine("calculate feature " + feature.getName());
			System.out.println(idx + " Artist: " + mirArtist.getName() + " calcFeature " + feature.getName());
			idx++;
		}
				
		
		//sort list by mean release date
		sortListByMeanReleaseDate(artistList);
		writer.writeLine("--------- Sorted by mean release date -----------");
		System.out.println("--------- Sorted by mean release date -----------");
		for (MirArtist mirArtist : artistList) {
			writer.writeLine(mirArtist.getName() + ": " + mirArtist.getEpochFeature().getMeanYear());
			System.out.println(mirArtist.getName() + ": " + mirArtist.getEpochFeature().getMeanYear());
		}
		
		
		//calculate similarity between artists based on their mean year of album releases
		writer.writeLine("----------- Calculate similarities ----------------");
		System.out.println("----------- Calculate similarities ----------------");
		DistanceSimilarityLastFMEpoch distSimilarity = new DistanceSimilarityLastFMEpoch(1, "DistanceSimilarity", artistList);
		if(distSimilarity.runCalc()){
			double[][] resultMatrix = distSimilarity.getResults();
			for(int i = 0; i < resultMatrix.length; i++) {
				String line = "";
				for (int j = 0; j < resultMatrix[i].length; j++) {
					line+= i + " :"+ resultMatrix[i][j] +"\t";
				}
				writer.writeLine(line);
				System.out.println(line);
			}
			
			MirArtistNetworkGraphVisualizer vis = new MirArtistNetworkGraphVisualizer(artistList,distSimilarity.getResults());
			vis.init();
		}
		
		writer.close();

	}
	
	/**
	 * An optimized InsertionSort Algorithm
	 * @param a
	 */
	private static void sortListByMeanReleaseDate(List<MirArtist> a){
		for(int i=0; i < a.size()-1; i++){
			if(a.get(i).getEpochFeature() != null && a.get(i+1).getEpochFeature() != null 
					&& (a.get(i).getEpochFeature().getMeanYear() > a.get(i+1).getEpochFeature().getMeanYear())){
				MirArtist h = a.get(i+1);	
				a.set(i+1, a.get(i));
				int j = i-1;
				
				while(j>=0 && a.get(j).getEpochFeature().getMeanYear() > h.getEpochFeature().getMeanYear()){
					a.set(j+1, a.get(j));
					j--;
				}
				
				a.set(j+1, h); 
			}				
		}
	}
}
