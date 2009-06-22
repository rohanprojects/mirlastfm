package jku.ss09.mir.lastfmecho.export.ml;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import comirva.util.external.TextFormatTool;

import jku.ss09.mir.lastfmecho.utils.TextFileWriter;

public class ArffExporter {

		
		private String targetPath;
		private final String separatorString =  ",";

		public ArffExporter(String targetPath) {
			this.targetPath = targetPath;
		}
		
		
		
		
		public boolean export(String header, double [][] array2D, List<String> columnLabels, List<String> classLabels)
		{
			
			Set<String> labelSet = new HashSet<String>();
			for (String labelString : classLabels) {
				labelSet.add(TextFormatTool.removeUnwantedChars(labelString));
			}
			
			String classString = "";
			for (String labelFromSet : labelSet) {
				classString+= labelFromSet + ",";
			}
			 
			
			
			
			if (classLabels != null && array2D.length != classLabels.size() ) {
				System.out.println("FileExport: export failed");
				return false;
			}
			if (columnLabels != null && array2D[0].length != columnLabels.size() ) {
				System.out.println("FileExport: export failed");
				return false;
			}
			
			try {
				TextFileWriter writer = new TextFileWriter(targetPath);
				writer.writeLine("% " + header);
				writer.writeLine("@RELATION java");
				// add xLabelsString if any
				
				if (columnLabels != null) {
					for (String label : columnLabels) {
						writer.writeLine("@ATTRIBUTE " + TextFormatTool.removeUnwantedChars(label) + " NUMERIC");
					}					
				}
				

				writer.writeLine("@ATTRIBUTE class  {"+classString+"}");
				writer.writeLine("");
				writer.writeLine("");
				writer.writeLine("@DATA");
				
				DecimalFormat formatter = new DecimalFormat("#.######");
				
				
				for (int i = 0; i < array2D.length; i++) {
					String line = "";
					for (int j = 0; j < array2D[i].length; j++) {				
//						line+= formatter.getNumberInstance(Locale.US).format(array2D[i][j]) + separatorString;
						line+= array2D[i][j] + separatorString;
					}
					if (classLabels != null) {
						line+= TextFormatTool.removeUnwantedChars(classLabels.get(i)) + separatorString; 
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

