package jku.ss09.mir.lastfmecho.bo;

import java.util.ArrayList;
import java.util.List;

public class MirGenre {
	
	
	private String name;
	private List<MirArtist> artistList;

	public MirGenre(String name) {
		super();
		this.name = name;
		artistList = new ArrayList<MirArtist>();
	}

	public boolean add(MirArtist e) {
		return artistList.add(e);
	}

	public String getName() {
		return name;
	}

	public List<MirArtist> getArtistList() {
		return artistList;
	}
	
	
	
	
	
}
