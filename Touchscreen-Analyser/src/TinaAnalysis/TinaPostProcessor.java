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
			String currentResultText = sc.nextLine();
			resultLines.add(new PostProcessedResult(currentResultText));
		}
		
		//Once it is all in memory, the writing loop commences
		
		int indexOfFirstErrorInTrain=-1;
		int indexOfStartOfSession = 0;
		int indexOfStartOfMouse = 0;
		int numberOfAnticipationErrors=0;
		int numberOfNonAnticipationErrors=0;
		int numberOfAnticipationErrorsPerMouse = 0;
		int numberOfNonAnticipationErrorsPerMouse = 0;
		
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
				
				
				//settle existing train?
				if (indexOfFirstErrorInTrain > -1) {
					int trainLength = currentIndex - indexOfFirstErrorInTrain;
					resultLines.get(indexOfFirstErrorInTrain).setErrorTrainPostTrial(trainLength);
				}
				
				//set new mouse if mouse is new, fill mouse column
				if (!resultLines.get(currentIndex).getAnimalId().equals(resultLines.get(indexOfStartOfMouse).getAnimalId())) {
					float mouseAverage = (float)numberOfAnticipationErrorsPerMouse /
							(numberOfNonAnticipationErrorsPerMouse + numberOfAnticipationErrorsPerMouse);
					mouseAverage *= 100;
					System.out.println("Writing Mouse Average until" + currentIndex);
					for (int i = indexOfStartOfMouse; i < currentIndex; i++) {
						resultLines.get(i).setPercentageAnticipationErrorsPerMouse(mouseAverage);
					}
					indexOfStartOfMouse = currentIndex;
					numberOfAnticipationErrorsPerMouse = 0;
					numberOfNonAnticipationErrorsPerMouse = 0;
				}
				
				indexOfStartOfSession = currentIndex;
				numberOfAnticipationErrors = 0;
				numberOfNonAnticipationErrors = 0;
				indexOfFirstErrorInTrain =-1;
				
				//if not in same session (new session but is an anticipation error)
				//then start a train
				if (resultLines.get(currentIndex).getAnticipationError() > 0) {
					indexOfFirstErrorInTrain = currentIndex;
				}
				else {
					resultLines.get(currentIndex).setErrorTrainPostTrial(0);
				}
				
			}
			
			//Finally count if anticipation error or not
			if (resultLines.get(currentIndex).getAnticipationError() > 0) {
				numberOfAnticipationErrors++;
				numberOfAnticipationErrorsPerMouse++;
			}
			else if (
					resultLines.get(currentIndex).getAnticipationError() < 1
					&&
					resultLines.get(currentIndex).getIfTouchscreenError() < 1) {
				numberOfNonAnticipationErrors++;
				numberOfNonAnticipationErrorsPerMouse++;
			}
			
			currentIndex++;
			
		}
		//Set percentage of last session after loop
		float percentage = (float)numberOfAnticipationErrors / (numberOfAnticipationErrors + numberOfNonAnticipationErrors);
		percentage = percentage * 100;
		for (int i = indexOfStartOfSession; i < currentIndex; i++) {
			resultLines.get(i).setPercentageAnticipationErrors(percentage);
		}
		//Set percentage for last Mouse
		float mouseAverage = (float)numberOfAnticipationErrorsPerMouse /
				(numberOfNonAnticipationErrorsPerMouse + numberOfAnticipationErrorsPerMouse);
		mouseAverage *= 100;
		for (int i = indexOfStartOfMouse; i < currentIndex; i++) {
			resultLines.get(i).setPercentageAnticipationErrorsPerMouse(mouseAverage);
		}
		//Close in stream
		sc.close();
		
		//Open bufferedWriter
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.print(header);
			pw.print(",Concecutive_Ant_Errors_Before_Trial");
			pw.print(",Ant_Error_Train_After_Trial");
			pw.print(",Percentage_Anticipation_Errors");
			pw.println(",Percentage_Anticipation_Errors_Per_Mouse");
			
			
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
