package cptBaselineAnalysis.analyzerHelpers;

import java.util.Arrays;

import dataModels.Event;

public class CPTBaselineTouchAndLatencyCounter {

	private int[] itiTouches;
	
	private int[] sDtouches;
	
	private int[] limitedHoldTouches;
	
	private int[] timeoutOrRewardTouches;
	
	private float responseLatency;
	
	private float rewardCollectionLatency;
	
	private enum TrialState{
		ITI,
		SD,
		LH,
		PT
	}
	
	public CPTBaselineTouchAndLatencyCounter() {
		this.itiTouches = new int[3];
		this.sDtouches = new int[3];
		this.limitedHoldTouches = new int[3];
		this.timeoutOrRewardTouches = new int[3];
		
		this.responseLatency = Float.NaN;
		this.rewardCollectionLatency = Float.NaN;
		
		
		
		//initialize to 0 
		int[][] allArrays = new int[4][];
		allArrays[0] = this.itiTouches;
		allArrays[1] = this.sDtouches;
		allArrays[2] = this.limitedHoldTouches;
		allArrays[3] = this.timeoutOrRewardTouches;
		
		for (int i = 0 ; i < allArrays.length; i++) {
			for (int j = 0 ; j < allArrays[i].length; j++) {
				allArrays[i][j] = 0;
			}
		}
	}
	
	public void countTouches(Event[] events) {
		TrialState state = TrialState.ITI;
		
		float referenceTime = Float.NaN;
		
		
		for (Event event : events) {
			
			if (event.equals(CPTBaselineReferenceEvents.IMAGE_DISPLAY)) {
				state = TrialState.SD;
				referenceTime = event.getEvent_Time();
			}
			if (event.equals(CPTBaselineReferenceEvents.IMAGE_WITHDRAWN)) {
				state = TrialState.LH;
			}
			if (event.equals(CPTBaselineReferenceEvents.HIT_EVENT) || 
					event.equals(CPTBaselineReferenceEvents.TIMEOUT_EVENT)) {
				state = TrialState.PT;
				referenceTime = event.getEvent_Time();
			}
			
			if (event.getEvent_Name().equals("Touch Down Event")){
				int location = (int)event.getArgumentValue(1);
				if (location == 0) continue;
				switch(state) {
				case ITI:
					this.itiTouches[location-1]++;
					continue;
				case SD:
					this.sDtouches[location-1]++;
					if (((Float)responseLatency).isNaN() && location == 2) {
						this.responseLatency = event.getEvent_Time()- referenceTime;
					}
					continue;
				case LH:
					this.limitedHoldTouches[location -1]++;
					if (((Float)responseLatency).isNaN() && location == 2) {
						this.responseLatency = event.getEvent_Time()- referenceTime;
					}
					continue;
				case PT:
					this.timeoutOrRewardTouches[location-1]++;
					continue;
				}
			}
			
			if (event.getEvent_Name().equals("Input Transition On Event") &&
					event.getItem_Name().equals("Tray #1") && 
					state == TrialState.PT){
				this.rewardCollectionLatency = event.getEvent_Time() - referenceTime;
			}
			
		}
		return;
	}
	
	public int[] getITITouchArray() {
		return Arrays.copyOf(this.itiTouches, itiTouches.length);
	}
	
	public int[] getSDTouchArray() {
		return Arrays.copyOf(this.sDtouches, sDtouches.length);
	}
	
	public int[] getLHTouchArray() {
		return Arrays.copyOf(this.limitedHoldTouches, this.limitedHoldTouches.length);
	}
	
	public int[] getRewardOrTimeoutTouches() {
		return Arrays.copyOf(this.timeoutOrRewardTouches, this.timeoutOrRewardTouches.length);
	}

	/**
	 * @return the responseLatency
	 */
	public float getResponseLatency() {
		return responseLatency;
	}
	
	public float getRewardCollectionLatency() {
		return this.rewardCollectionLatency;
	}
	
}
