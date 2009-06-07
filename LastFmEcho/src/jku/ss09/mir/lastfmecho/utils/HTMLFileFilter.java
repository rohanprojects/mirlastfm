package jku.ss09.mir.lastfmecho.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;




public class HTMLFileFilter implements FilenameFilter, FileFilter{
    
	
    //FILENAMEFILTER INTERFACE
	public boolean accept(File dir, String name) {
        return (name.endsWith(".html"));
    }

    
    
    
    //FILEFILTER INTERFACE
	@Override
	public boolean accept(File pathname) {
	        if (pathname.isDirectory()) return true;
	        String name = pathname.getName().toLowerCase();
	        return name.endsWith(".html");
	    }//end accept
}




