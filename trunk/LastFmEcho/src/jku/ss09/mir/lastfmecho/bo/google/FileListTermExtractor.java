package jku.ss09.mir.lastfmecho.bo.google;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import jku.ss09.mir.lastfmecho.bo.MirArtist;
import jku.ss09.mir.lastfmecho.utils.HTMLFileFilter;
import jku.ss09.mir.lastfmecho.utils.IOUtils;

public class FileListTermExtractor {

	public FileListTermExtractor() {		
	}


	public void run(String targetDir,Map<String,Integer> terms) {
		
		
		System.out.println("Extract terms from " + targetDir);
		
		//initalize map on 0 count for each term
		Map<String,Integer> weightedTerms = terms;		
		weightedTerms.keySet();
		for (String term  : weightedTerms.keySet()) {
			weightedTerms.put(term,0);
		}

		File dir = new File(targetDir);

		if (dir.isDirectory()) {
			String[] children = dir.list(new HTMLFileFilter());
			for (int i=0; i<children.length; i++) {
				File file = new File(targetDir + children[i]);
				if (file.isFile()) {		
					String fileContent = runFile(file);
					extractTerms(fileContent,terms);
				}
			}
		}
	}

	private void extractTerms(String fileContent, Map<String, Integer> terms) {
		
		fileContent = fileContent.toLowerCase();
		
		for (String term  : terms.keySet()) {
			int totalCount = terms.get(term);
			int thisFileCount = StringUtils.countMatches(fileContent, " " + term.toLowerCase() + " ");
			//int thisFileCount = StringUtils.countMatches(IOUtils.removeTagsSpecialChar(fileContent), term.toLowerCase());
			
			
			
			totalCount += thisFileCount;
			terms.put(term, totalCount);
			
			
			if (term.toLowerCase().equals("rap") && thisFileCount > 0)
			{
				int test = 10;
			}
			
		}
	}


	/**
	 * parses a file line by line 
	 */
	private String runFile(File file) {

		
		StringBuffer fileBuffer = new StringBuffer();
		try {
			FileReader reader = new FileReader(file);
			BufferedReader in = new BufferedReader(reader);
						
			int i = 1;
			String line;
			while ((line = in.readLine()) != null ) {				 
				fileBuffer.append(line);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileBuffer.toString();
	}
	

}
