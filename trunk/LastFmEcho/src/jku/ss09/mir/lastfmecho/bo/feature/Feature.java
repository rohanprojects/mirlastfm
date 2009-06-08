package jku.ss09.mir.lastfmecho.bo.feature;

import jku.ss09.mir.lastfmecho.bo.MirArtist;

public abstract class Feature {


	protected int id;
	protected String name;
	protected MirArtist artist;
	
	
	public Feature(MirArtist artist, int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public MirArtist getArtist() {
		return artist;
	}

	/**
	 * 
	 * here the calculation/retieval of the feature is done
	 * 
	 * @return if calculation was successful
	 */
	public abstract boolean calc(); 
	
	
	
}
