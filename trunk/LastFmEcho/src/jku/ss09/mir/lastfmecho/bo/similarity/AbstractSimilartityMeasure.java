package jku.ss09.mir.lastfmecho.bo.similarity;

import java.util.List;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.bo.feature.Feature;

import static org.math.array.DoubleArray.*;
import static org.math.array.LinearAlgebra.*;

import org.math.array.DoubleArray;

/**
 * AbstractSimilartityMeasure provides a basic stub for calculating similarities between lists
 * 
 * @author jakob
 *
 */
public abstract class AbstractSimilartityMeasure {
	
	
	protected int id;
	protected String name;
	
	protected List<MirArtist> artistList;
	protected double[][] resultMatrix;
	

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
		resultMatrix = fill(artistList.size(), artistList.size(), 0.0);
	}
	
	/**
	 * This is the run method to be implemented from subclasses for calculating similarity relations
	 * between all artist provided in the constructor
	 * 
	 * @return if calculation was successful, errors may be acceptable 
	 */
	public abstract boolean runCalc();
	
		
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

	
	/**
	 * This returns the N x N matrix of the similarities of N artists
	 * results are computed primarily unidirectional for 1-Dimension Artist (row) to 2-Dimension Artist (column)
	 * 
	 * @return resultMatrix
	 */
	public double[][] getResults() {
		return resultMatrix;
	}
	
	public List<MirArtist> getArtistList() {
		return artistList;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
