package touchscreenAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import dataModels.Identifier;
import dataModels.MetaData;

public class MetaFileReader {

	public void read(File file, HashMap<Identifier, MetaData> groupInfoMap) {
		
		
		//Open file and read lines into HashMap
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String textLine = "";
			
			textLine = reader.readLine();
			
			String[] fileHeader = textLine.split(",");
			if(fileHeader.length < 2) {
				fileHeader = textLine.split("\t");
			}
			
			String[] dataHeaders = new String[fileHeader.length - Identifier.NUMBER_OF_ID_VARIABLES];
			
			//copy headers into array
			for (int i = 0; i < dataHeaders.length; i++) {
				dataHeaders[i] = fileHeader[i + Identifier.NUMBER_OF_ID_VARIABLES];
			}
			
			//set metadata headers
			MetaData.setHeaders(dataHeaders);
			
			//map identifiers to metadata
			while((textLine = reader.readLine()) != null) {
				//separate into array
				String[] metadataArray = textLine.split(",",-1);
				//if csv assumption failed, use tsv
				if (metadataArray.length < 2) {
					metadataArray = textLine.split("\t",-1);
				}
				//build ID
				Identifier id = new Identifier(metadataArray[0], (metadataArray[1].equals("NULL")) ? null : metadataArray[1]);
				//Build metadata
				MetaData metadata = new MetaData();
				String[] tempData = new String[dataHeaders.length];
				for (int i = 0; i < tempData.length; i++) {
					tempData[i] = metadataArray[i + Identifier.NUMBER_OF_ID_VARIABLES];
				}
				metadata.addData(tempData);
				
				groupInfoMap.put(id,metadata);
			}
			
			reader.close();
			
			System.out.println("Animal Ids succesfully mapped to metadata using file " + file.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
