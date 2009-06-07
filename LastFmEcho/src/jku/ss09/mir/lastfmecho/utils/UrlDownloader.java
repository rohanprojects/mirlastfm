package jku.ss09.mir.lastfmecho.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;


public class UrlDownloader {


	
	private int maxPages;

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}

	public UrlDownloader() {
		maxPages = 10;
	}

	public void runDirectory(File dir) {
		if (dir.isDirectory() && dir.getName().compareTo(".svn") != 0) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				runDirectory(new File(dir, children[i]));
			}
		}
		if (dir.getName().endsWith(".dat")) {
			String filePath = dir.getAbsolutePath();
			String dirPath = dir.getParentFile().getAbsolutePath();
			runFile(filePath,dirPath);
			// danger of file delete while browsing directory 
		} else if (dir.getName().endsWith(".html")) {
			dir.delete();
		}
	} 

	public void runFile(String fileName, String targetDir)
	{


		System.out.println("Downloading files from " + targetDir + "/" +fileName);

		//		//delete all previous html from folder
		//		if (IOUtils.deleteFilesInDir(targetDir) == false)
		//		{
		//			System.out.println("Error: Not all html files could be deleted");
		//			return;
		//		}
		//		


		try {
			File file = new File(fileName);
			FileReader reader = new FileReader(file);
			BufferedReader in = new BufferedReader(reader);
			String urlString;

			int i = 1;
			while ((urlString = in.readLine()) != null && i < maxPages+1) {
				//	             System.out.println(string);

				String targetFileName = targetDir + "/" + i +".html";

				if (downloadUrl(urlString, targetFileName ) == true) {					
					i++;
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}





	private boolean downloadUrl(String urlString, String targetFileName) {
		URL urlObject = null;

		try {
			urlObject = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.err.println("Error: " + e.getMessage());
			return false;
		}



		if (urlObject != null) {
			try 
			{

				BufferedReader bufferedIn = new BufferedReader(new InputStreamReader(urlObject.openStream()));
				BufferedWriter bufferedOut = new BufferedWriter(new FileWriter(targetFileName));

				String inputLine;
				while ((inputLine = bufferedIn.readLine()) != null) {
					//					System.out.println(inputLine);
					bufferedOut.write(inputLine + "\n");
				}
				bufferedIn.close();
				bufferedOut.close();
				// if a specific file in a domain cannot be retrieved e.g. http://www.cc.com/abofefut.htm
			} catch (FileNotFoundException e) {
				System.err.println("Error: " + e.getMessage());
				return false;
				//  if the domain itself cannot be resolved				
			} catch (UnknownHostException e) {
				System.err.println("Error: " + e.getMessage());
				return false;
				//  Server returned HTTP response code: 403 for URL: 
			}  catch (IOException e) {
				System.err.println("Error: " + e.getMessage());
				return false;
			}

			return true;

		} else {
			System.out.println("Error: Url - " + urlString + " cannot be read" );
			return false;
		}
	}

}
