package jku.ss09.mir.lastfmecho.bo.similarity;

import java.util.List;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.feature.Feature;

/**
 * AbstractSimilartityMeasure provides a basic stub for calculating similarities between lists
 * 
 * @author jakob
 *
 */
public abstract class AbstractSimilartityMeasure {
	
	
	private int id;
	private String name;
	
	private List<MirArtist> artistList;

	/**
	 * @param id an arbitrary unique id for this similarity measure 
	 * @param name of this SimilarityMeasure e.g. "Euclidean Distance" 
	 * @param artistList2
	 */
	public AbstractSimilartityMeasure(int id, String name, List<MirArtist> artistList2)
	{
		this.id = id;
		this.name = name;
		this.artistList = artistList2;
	}
	
	/**
	 * This is the run method to be implemented from subclasses for calculating similarity relations
	 * between all artist provided in the constructor
	 */
	public abstract void runCalc();
	
	/**
	 * 
	 * !USE THIS METHOD INSIDE runCalc()
	 * This method is to be implemented in the subclasses to calculate the relation between 
	 * 2 artists 
	 * @param artistA
	 * @param artistB
	 * @return relation expressed as a number
	 */
	protected abstract double calcSimilarity(MirArtist artistA, MirArtist artistB); 
	
	
}
