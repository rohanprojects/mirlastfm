package jku.ss09.mir.lastfmecho.bo.feature;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import net.roarsoftware.lastfm.Artist;
import net.roarsoftware.lastfm.Album;

import jku.ss09.mir.lastfmecho.bo.LastFMParser;
import jku.ss09.mir.lastfmecho.bo.MirArtist;

public class EpochFeature extends Feature{
	
	
	private Collection<Album> topAlbums;
	private ArrayList<Integer> releaseDates;
	private int meanYear;
//	private int rmsDeviation;
	
	
	public EpochFeature(MirArtist artist) {
		super(artist,FeatureFactory.FEATURE_EPOCH,"Epoch");
	}
	
	
	@Override
	public boolean calc() {
		Collection<Album> tmpTopAlbums = null;
		try {
			tmpTopAlbums = Artist.getTopAlbums(artist.getName(), LastFMParser.getApiKey());
		} catch (Exception e) {
			System.err.println("No albums retrieved for artist " + artist.getName());
		} 
		
		if(tmpTopAlbums != null)
			setAlbums((Collection<Album>) tmpTopAlbums);
		else{
			System.err.println(" NO topAlbums retrieved?: ");		
		}
		
		return false;	
	}
	
	
	public Collection<Album> getTopAlbums() {
		return topAlbums;
	}

	public ArrayList<Integer> getReleaseDates(){
		return releaseDates;
	}
	
	
	private void setAlbums(Collection<Album> collection){
		topAlbums = collection;
		releaseDates = new ArrayList<Integer>();
		
		for(Album id : topAlbums){
			Album info = null;
			try {
				info = Album.getInfo(id.getArtist(), id.getMbid(), LastFMParser.getApiKey());
			} catch (Exception e) {
				System.err.println("No infos retrieved for album " + id.getName());
			}
			if(info != null){
//				System.out.print(artist.getName() + ": " + id.getName() + " (");
				if(info.getReleaseDate() != null){
					Date releaseDate = info.getReleaseDate();
					SimpleDateFormat simpleDateformat = new SimpleDateFormat("yyyy");
					releaseDates.add(Integer.parseInt(simpleDateformat.format(releaseDate)));
//					System.out.println(simpleDateformat.format(releaseDate) + ")");
				}else{
//					System.out.println("UNKNOWN)");
				}

			}
		}
//		System.out.print(artist.getName());
		calcMean();
	}
	
	private void calcMean(){
		if(releaseDates != null && releaseDates.size() > 0){
			int sumYears = 0;
			
			for(Integer year : releaseDates)
				sumYears += year;
			
			meanYear = sumYears / releaseDates.size();
			System.out.println("Mittelwert: " + meanYear);
		}	
		else 
			System.err.println("No release dates available. " );
	}
	





	
	
}
