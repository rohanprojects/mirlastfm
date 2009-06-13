package jku.ss09.mir.lastfmecho.bo.similarity;

import java.util.List;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.feature.Feature;

public class ConsineSimilarity extends AbstractSimilartityMeasure {

	
	public ConsineSimilarity(int id, String name, List<MirArtist> artistList) {
		super(id, name, artistList);
	}

	@Override
	protected double calcSimilarity(MirArtist artistA, MirArtist artistB) {
		
		return 0;
	}

	@Override
	public void runCalc() {
		// TODO Auto-generated method stub
		
	}
	

}
