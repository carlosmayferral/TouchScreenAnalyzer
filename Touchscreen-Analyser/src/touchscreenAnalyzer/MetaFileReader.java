package touchscreenAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class MetaFileReader {

	public void read(File file, HashMap<String, String> groupInfoMap) {
		
		
		//Open file and read lines into HashMap
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String textLine = "";
			
			while((textLine = reader.readLine()) != null) {
				groupInfoMap.put(textLine.split(",")[0], textLine.split(",")[1]);
			}
			
			reader.close();
			
			System.out.println("Animal Ids succesfully mapped to groups using file " + file.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
