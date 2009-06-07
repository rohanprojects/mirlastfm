package jku.ss09.mir.lastfmecho.bo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import jku.ss09.mir.lastfmecho.comirva.utils.GoogleUrlRetriever;

public class MusicFileParser {

	private List<MirArtist> artistList; 
	private Set<MirGenre> genreSet;

	public MusicFileParser() {

	}

	public boolean run()
	{

		artistList = new ArrayList<MirArtist>();
		genreSet = new HashSet<MirGenre>();

		String dirPath = System.getProperty("user.dir");
		dirPath+= "/data/";


		//fetch MirGenre and MirArtist 
		String artistFile = dirPath + "artists_224.txt";
		String genreFile = dirPath + "artists_224_genres_only.txt";

		List<String> artistStringList = new ArrayList<String>();
		List<String> genreStringList = new ArrayList<String>();
		try {
			retrieveCSVData(artistFile, artistStringList);
			retrieveCSVData(genreFile, genreStringList);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		if(artistStringList.size() != genreStringList.size()) {
			return false;
		}
				
		for (int i = 0; i < artistStringList.size(); i++) {
			MirArtist artist = new MirArtist(artistStringList.get(i));
			MirGenre genre =  getGenreByName(genreStringList.get(i));
			artist.setGenre(genre);
			artistList.add(artist);
		}
		
		
		return true;
	}

	private MirGenre getGenreByName(String genreName) {

		//find existing genre
		for (MirGenre mirGenre : genreSet) {
			if (mirGenre.getName().equals(genreName))
			{
				return mirGenre;
			}
		}

		//if not found add new Genre 
		MirGenre newGenre = new MirGenre(genreName);
		genreSet.add(newGenre);
		return newGenre;
	}

	private void retrieveCSVData(String artistFile, List<String> artists) throws FileNotFoundException, IOException {
		
		BufferedReader readerFile = new BufferedReader(new FileReader(artistFile));
		
		String nextString = readerFile.readLine();
		while (nextString != null) {
			artists.add(nextString);
			nextString = readerFile.readLine();
		}		
		
//		Scanner sc;
//		sc = new Scanner(new File(artistFile));
//		sc.useDelimiter("\r\n");
//		while (sc.hasNext()) {
//			String nextString = sc.next();
//			artists.add(nextString);
//		}
	}
	
	

	public List<MirArtist> getArtistList() {
		return artistList;
	}

	public Set<MirGenre> getGenreSet() {
		return genreSet;
	}

}
