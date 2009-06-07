package jku.ss09.mir.lastfmecho.utils;

import java.io.File;
import java.io.FilenameFilter;




public class HTMLFileFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".html"));
    }
}




