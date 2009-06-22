package jku.ss09.mir.lastfmecho.bo;

import comirva.util.external.TextFormatTool;

public class AppUtils {
	
	
	public static String getArtistPath(String artistName) {
		
		String extractedName= TextFormatTool.removeUnwantedChars(artistName);
		String targetDir = System.getProperty("user.dir") + "/data/download/" + extractedName + "/";
		return targetDir;
		
	}
	

}
