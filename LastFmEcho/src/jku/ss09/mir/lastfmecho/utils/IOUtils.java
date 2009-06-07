package jku.ss09.mir.lastfmecho.utils;

import java.io.File;
import java.util.List;





public class IOUtils {
	
	
	
	
	public static boolean deleteFilesInDir(String targetDir) {
        
		boolean success = true;
		
		File dir = new File(targetDir);
		
		if (dir.isDirectory()) {
            String[] children = dir.list(new HTMLFileFilter());
            for (int i=0; i<children.length; i++) {
            	File file = new File(targetDir + children[i]);
                success = success && file.delete();
                
            }
		}
		return success;
	}	
}
