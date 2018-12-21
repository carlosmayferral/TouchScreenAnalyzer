package TinaAnalysis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import analysisSets.IPostProcessor;

public class TinaPostProcessor implements IPostProcessor {

	@Override
	public void process(File file) {
		
		//Try to open the file
		Scanner sc = this.openFile(file);
		
		//Read file into array of resultLines in memory
		ArrayList<PostProcessedResult> resultLines= new ArrayList<PostProcessedResult>();
		
		String header = sc.nextLine();
		
		while(sc.hasNextLine()) {
			resultLines.add(new PostProcessedResult(sc.nextLine()));
		}
		
		//Once it is all in memory, the writing loop commences
		
		int indexOfFirstErrorInTrain=-1;
		int indexOfStartOfSession = 0;
		int numberOfAnticipationErrors=0;
		int numberOfNonAnticipationErrors=0;
		
		int currentIndex = 0;
		
		while (currentIndex < resultLines.size()) {
			
			//If within same session, proceed to writing
			if (resultLines.get(currentIndex).isSameSession(resultLines.get(indexOfStartOfSession))) {
				//if there is an active train
				if (indexOfFirstErrorInTrain != -1) {
					
					int trainLength = currentIndex - indexOfFirstErrorInTrain;
					
					//set train before trial
					resultLines.get(currentIndex).setErrorTrainPreTrial(trainLength);
					
					//and if current trial is not an anticipation error
					if (resultLines.get(currentIndex).getAnticipationError() == 0) {
						
						//set train after trial
						resultLines.get(indexOfFirstErrorInTrain).setErrorTrainPostTrial(trainLength);
						
						//current trial has 0 errors post trial
						resultLines.get(currentIndex).setErrorTrainPostTrial(0);
						
						//reset train
						indexOfFirstErrorInTrain =-1;
					}
					//If there is an active train but current trial is an ant error
					else {
						
					}
				
					
				}
				//If there is not an active train
				else {
					//and current trial is an anticipation error
					if (resultLines.get(currentIndex).getAnticipationError() == 1) {
						indexOfFirstErrorInTrain = currentIndex;
					}
					//if it's not an anticipation error, then it results in a post train of 0
					else {
						resultLines.get(currentIndex).setErrorTrainPostTrial(0);
					}
				}
				
			}
			//Otherwise (if not in same session) write percentage and reset everything
			else {
				float percentage = (float)numberOfAnticipationErrors / (numberOfAnticipationErrors + numberOfNonAnticipationErrors);
				percentage = percentage * 100;
				for (int i = indexOfStartOfSession; i < currentIndex; i++) {
					resultLines.get(i).setPercentageAnticipationErrors(percentage);
				}
				indexOfStartOfSession = currentIndex;
				numberOfAnticipationErrors = 0;
				numberOfNonAnticipationErrors = 0;
				indexOfFirstErrorInTrain =-1;
			}
			
			//Finally count if anticipation error or not
			if (resultLines.get(currentIndex).getAnticipationError() > 0) {
				numberOfAnticipationErrors++;
			}
			else if (
					resultLines.get(currentIndex).getAnticipationError() < 1
					&&
					resultLines.get(currentIndex).getIfTouchscreenError() < 1) {
				numberOfNonAnticipationErrors++;
			}
			
			currentIndex++;
			
		}
		//Set percentage of last session
		float percentage = (float)numberOfAnticipationErrors / (numberOfAnticipationErrors + numberOfNonAnticipationErrors);
		percentage = percentage * 100;
		for (int i = indexOfStartOfSession; i < currentIndex; i++) {
			resultLines.get(i).setPercentageAnticipationErrors(percentage);
		}
		//Close in stream
		sc.close();
		
		//Open bufferedWriter
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.print(header);
			pw.print(",Concecutive_Ant_Errors_Before_Trial");
			pw.print(",Ant_Error_Train_After_Trial");
			pw.println(",Percentage_Anticipation_Errors");
			
			for (PostProcessedResult ppr : resultLines) {
				pw.println(ppr.generateResultString());
			}
			
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private Scanner openFile(File file) {
		try {
			Scanner sc = new Scanner(file);
			return sc;
		} catch (FileNotFoundException e) {
			System.out.println("Result file to be post-processed was not found");
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}

}
