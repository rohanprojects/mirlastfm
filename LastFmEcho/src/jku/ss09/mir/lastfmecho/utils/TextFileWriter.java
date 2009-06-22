package jku.ss09.mir.lastfmecho.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class TextFileWriter {


	private String targetPath;
	private BufferedWriter bufferedOut;

	public TextFileWriter(String targetPath) throws IOException
	{
		this.targetPath = targetPath;

		bufferedOut = new BufferedWriter(new FileWriter(targetPath));
	
	}


	public void write(String line) throws IOException {
		bufferedOut.write(line);
	}

	public void writeLine(String line) throws IOException {
		bufferedOut.write(line +  System.getProperty("line.separator"));
	}

	public void close() throws IOException {

		bufferedOut.close();
	}



	
	public static void main(String[] args) {
		//test 
		
		String dirPath = System.getProperty("user.dir");
		String targetDir = dirPath + "/data/results" + "epoch.txt";
		try {
			TextFileWriter writer = new TextFileWriter(targetDir);
			writer.write("test");
			writer.write("test");
			writer.write("test");
			writer.writeLine("newLine");
			writer.writeLine("432,432,54,543,54,3,5,4,3");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}




}
