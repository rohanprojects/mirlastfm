package jku.ss09.mir.lastfmecho.bo;

public class MirArtist {

	
	private MirGenre genre; 
	private String name;
	
	
	public MirArtist(String name) {
		super();
		this.name = name;
	}


	public MirGenre getGenre() {
		return genre;
	}


	public void setGenre(MirGenre genre) {
		this.genre = genre;
		genre.add(this);
	}


	public String getName() {
		return name;
	}
	
	
	
	
	
	
	
	

	
	
}
