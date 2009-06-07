package jku.ss09.mir.lastfmecho.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





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
	
	
	
	
	/**
	 * 
	 * 
	 * ReadTextfile METHOD1
	 * 
	 * @param fullPathFilename
	 * @return
	 * @throws IOException
	 */
	public static String readTextFile(String fullPathFilename) throws IOException {
		StringBuffer sb = new StringBuffer(1024);
		BufferedReader reader = new BufferedReader(new FileReader(fullPathFilename));
				
		char[] chars = new char[1024];
		int numRead = 0;
		while( (numRead = reader.read(chars)) > -1){
			sb.append(String.valueOf(chars));	
		}

		reader.close();

		return sb.toString();
	}
	
	
	/**
	 * 
	 * Read file and remove html markup tags and scripts  
	 * Code used from http://www.infernodevelopment.com/how-write-html-parser-java
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String readHTMLFileRemoveString(String filePath) throws IOException
	{
		
		String sourceLine;
		String content = "";

		BufferedReader source = new BufferedReader(new FileReader(filePath));
		
		// Append each new HTML line into one string. Add a tab character.
		while ((sourceLine = source.readLine()) != null) {
			content += sourceLine + "\t";	
		}
		
 
		// Remove style tags & inclusive content
		Pattern style = Pattern.compile("<style.*?>.*?</style>");
		Matcher mstyle = style.matcher(content);
		while (mstyle.find()) content = mstyle.replaceAll("");
 
		// Remove script tags & inclusive content
		Pattern script = Pattern.compile("<script.*?>.*?</script>");
		Matcher mscript = script.matcher(content);
		while (mscript.find()) content = mscript.replaceAll("");
 
		// Remove primary HTML tags
		Pattern tag = Pattern.compile("<.*?>");
		Matcher mtag = tag.matcher(content);
		while (mtag.find()) content = mtag.replaceAll("");
 
		// Remove comment tags & inclusive content
		Pattern comment = Pattern.compile("<!--.*?-->");
		Matcher mcomment = comment.matcher(content);
		while (mcomment.find()) content = mcomment.replaceAll("");
 
		// Remove special characters, such as &nbsp;
		Pattern sChar = Pattern.compile("&.*?;");
		Matcher msChar = sChar.matcher(content);
		while (msChar.find()) content = msChar.replaceAll("");
 
		// Remove the tab characters. Replace with new line characters.
		Pattern nLineChar = Pattern.compile("\t+");
		Matcher mnLine = nLineChar.matcher(content);
		while (mnLine.find()) content = mnLine.replaceAll("\n");
 
		// Print the clean content & close the Readers
		
		source.close();
		return content;
	}

		
	

}
