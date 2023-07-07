package fiveChoiceAnalysis;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;

class FiveChoiceParameterReaderTest {

	private FiveChoiceParameterReader parameterReader;
	
	static final String trainingParametersOneFileName = "TrainingParameters1.csv";
	
	static final String trainingParametersTwoFileName = "TrainingParameters2.csv";
	
	static final String accuracyParametersOneFileName = "AccuracyParameters1.csv";
	
	static final String accuracyParametersTwoFileName = "AccuracyParameters2.csv";
	
	static final String brightnessParametersOneFileName = "BrightnessParameters1.csv";
	
	static final String brightnessParametersTwoFileName = "BrightnessParameters2.csv";
	
	static final String delayParametersOneFileName = "DelayParameters1.csv";
	
	static final String delayParametersTwoFileName = "DelayParameters2.csv";
	
	@BeforeEach
	void setup() {
		parameterReader = new FiveChoiceParameterReader();
	}

	@Test
	void trainingOneParametersAreCorrect() {
		Event [] events = this.readParameterEventsFromFile(trainingParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(5, parameters.getStimulusDelay(), 0.01);
		Assert.assertEquals(2, parameters.getStimulusDuration(), 0.01);
		Assert.assertEquals(5, parameters.getItiLength(), 0.01);
		Assert.assertEquals(5, parameters.getStimulusBrightness(), 0.01);
	}
	
	//Training session Parameters
	
	@Test
	void traininTwoDelayIsSeven() {
		Event [] events = this.readParameterEventsFromFile(trainingParametersTwoFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(7, parameters.getStimulusDelay(), 0.01);
	}
	
	@Test
	void traininTwoStimDurIsThirtyTwo() {
		Event [] events = this.readParameterEventsFromFile(trainingParametersTwoFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(32, parameters.getStimulusDuration(), 0.01);
	}
	
	@Test
	void trainingTwoITIIsTen() {
		Event [] events = this.readParameterEventsFromFile(trainingParametersTwoFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(10, parameters.getItiLength(), 0.01);
	}
	
	//Stimdur Session Parameters
	@Test
	void accuracyOneDelayIsFive() {
		Event [] events = this.readParameterEventsFromFile(accuracyParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(5, parameters.getStimulusDelay(), 0.01);
	}
	
	@Test
	void accuracyOneStimDurIsZeroPointEight() {
		Event [] events = this.readParameterEventsFromFile(accuracyParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(0.8, parameters.getStimulusDuration(), 0.01);
	}
	
	@Test
	void accuracyOneITILengthIsFive() {
		Event [] events = this.readParameterEventsFromFile(accuracyParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(5, parameters.getItiLength(), 0.01);
	}
	
	@Test
	void accuracyTwoStimDurIsZeroPointTwo() {
		Event [] events = this.readParameterEventsFromFile(accuracyParametersTwoFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(0.2, parameters.getStimulusDuration(), 0.01);
	}
	
	//Brightness
	
	@Test
	void brightnessOneDelayIsFive() {
		Event [] events = this.readParameterEventsFromFile(brightnessParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(5, parameters.getStimulusDelay(), 0.01);
	}
	
	@Test
	void brightnessOneStimDurIsTwo() {
		Event [] events = this.readParameterEventsFromFile(brightnessParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(2, parameters.getStimulusDuration(), 0.01);
	}
	
	@Test
	void brightnessOneITIIsFive() {
		Event [] events = this.readParameterEventsFromFile(brightnessParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(5, parameters.getItiLength(), 0.01);
	}
	
	@Test
	void brightnessOneBrightnessIsTwo() {
		Event [] events = this.readParameterEventsFromFile(brightnessParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(2, parameters.getStimulusBrightness(), 0.01);
	}
	
	@Test
	void brightnessTwoBrightnessIsThree() {
		Event [] events = this.readParameterEventsFromFile(brightnessParametersTwoFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(3, parameters.getStimulusBrightness(), 0.01);
	}
	
	//Delay 
	
	@Test
	void delayOneStimDurIsTwo() {
		Event [] events = this.readParameterEventsFromFile(delayParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(2, parameters.getStimulusDuration(), 0.01);
	}
	
	@Test
	void delayOneITIIsFive() {
		Event [] events = this.readParameterEventsFromFile(delayParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(5, parameters.getItiLength(), 0.01);
	}
	
	@Test
	void delayOneDelayBlockIsZero() {
		Event [] events = this.readParameterEventsFromFile(delayParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(0, parameters.getDelayBlock(), 0.01);
	}
	
	@Test
	void delayOneFirstDelayIsThree() {
		Event [] events = this.readParameterEventsFromFile(delayParametersOneFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(3, parameters.getStimulusDelay(), 0.01);
	}
	
	@Test
	void delayTwoDelayBlockIsOne() {
		Event [] events = this.readParameterEventsFromFile(delayParametersTwoFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(1, parameters.getDelayBlock(), 0.01);
	}
	
	@Test
	void delayTwoFirstDelayIsEight() {
		Event [] events = this.readParameterEventsFromFile(delayParametersTwoFileName);
		FiveChoiceSessionParameters parameters = (FiveChoiceSessionParameters) parameterReader.readParameters(events);
		Assert.assertEquals(8, parameters.getStimulusDelay(), 0.01);
	}
	
	/**
	 * This test method gathers events from a file to be used for input into the readParameters method
	 * @param filename The .csv file where the events are located.
	 * @return
	 */
	private Event[] readParameterEventsFromFile(String filename) {
		String filepath = "testfiles/FiveChoiceParameterReader/" + filename;
		
		ArrayList<Event> events = new ArrayList<Event>();
		
		//open file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			String textLine = "";
			while ((textLine = reader.readLine())!= null) {
				events.add(new Event(textLine));
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Couldn't find test file " + filepath);
			e.printStackTrace();
		}
		
		//Return events
		Event[] eventsArray = new Event[events.size()];
		return events.toArray(eventsArray);
		
	}

}
