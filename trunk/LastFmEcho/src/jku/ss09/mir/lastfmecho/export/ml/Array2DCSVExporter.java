package jku.ss09.mir.lastfmecho.export.ml;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import jku.ss09.mir.lastfmecho.utils.TextFileWriter;

public class Array2DCSVExporter {
	
	
	
	
	
	private String targetPath;
	private final String separatorString;

	public Array2DCSVExporter(String targetPath,String separatorString) {
		this.targetPath = targetPath;
		this.separatorString = separatorString;
	}
	
	
	
	public boolean export(String header, double [][] array2D) {
		return export(header,array2D,null,null);
	}
	
	public boolean export(String header, double [][] array2D, List<String> xLabels, List<String> yLabels)
	{
		
		
		if (xLabels != null && array2D.length != xLabels.size() ) {
			System.out.println("FileExport: export failed");
			return false;
		}
		if (yLabels != null && array2D.length != yLabels.size() ) {
			System.out.println("FileExport: export failed");
			return false;
		}
			
		
		
		try {
			TextFileWriter writer = new TextFileWriter(targetPath);
			writer.writeLine(header);
			
			// add xLabelsString if any
			
			if (xLabels != null) {
				String xLabelString ="";
				for (String label : xLabels) {
					xLabelString+= label + separatorString;
				}
				writer.writeLine(separatorString + xLabelString);
			}
			
			
			DecimalFormat formatter = new DecimalFormat(".######");
			
			
			for (int i = 0; i < array2D.length; i++) {
				String line = "";
				if (yLabels != null) {
					line+= yLabels.get(i) + separatorString; 
				}
				for (int j = 0; j < array2D[i].length; j++) {
					line+= formatter.format(array2D[i][j]) + separatorString;				
				}
				writer.writeLine(line);
			}
			
			writer.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
		
		
		
		
	}
	
	
	
	
	
	
	

}
