package progressiveRatioAnalysis.analyzerHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import dataModels.Event;

public class ProgressiveRatioTouchCounter {

	ArrayList<Float> validTouches;
	ArrayList<Float> itiTouches;
	ArrayList<Float> interRatioTouches;
	ArrayList<Float> rewardPeriodTouches;

	private enum State {
		ITI_Period, VALID_Period, INTER_RATIO_PERIOD, REWARD_COLLECTION_PERIOD
	}

	public ProgressiveRatioTouchCounter(Event[] events) {

		validTouches = new ArrayList<Float>();
		itiTouches = new ArrayList<Float>();
		interRatioTouches = new ArrayList<Float>();
		rewardPeriodTouches = new ArrayList<Float>();

		// For state machine
		State state = State.ITI_Period;

		for (Event event : events) {
			switch (state) {
			case ITI_Period:
				if (event.getItem_Name().equals("ITI TO IMAGE TRANSITION")) {
					state = State.VALID_Period;

				}
				if (event.getEvent_Name().equals("Touch Down Event") && (event.getArgumentValue(1) == 3)) {
					itiTouches.add(event.getEvent_Time());
				}
				continue;
			case VALID_Period:
				if (event.getItem_Name().equals("Image Touched")) {
					validTouches.add(event.getEvent_Time());
				}
				if (event.getItem_Name().equals("PROGRESSIVE RATIO END CALC")) {
					state = State.REWARD_COLLECTION_PERIOD;
				}
				if (event.getItem_Name().equals("Start ITI")) {
					state = State.ITI_Period;
				}
				continue;
			case REWARD_COLLECTION_PERIOD:
				if (event.getEvent_Name().equals("Touch Down Event") && (event.getArgumentValue(1) == 3)) {
					this.rewardPeriodTouches.add(event.getEvent_Time());
				}
				if (event.getItem_Name().equals("Reward Collected Start Reward Next Trial Delay")) {
					state = State.INTER_RATIO_PERIOD;
				}
				continue;
			case INTER_RATIO_PERIOD:
				if (event.getItem_Name().equals("REWARD NEXT TRIAL DELAY END START NEXT TRIAL")) {
					state = State.ITI_Period;
				}
				if (event.getEvent_Name().equals("Touch Down Event") && (event.getArgumentValue(1) == 3)) {
					this.interRatioTouches.add(event.getEvent_Time());
				}
				continue;
			}
		}
	}

	public int getValidTouches() {
		return this.validTouches.size();
	}

	public int getItiTouches() {
		return this.itiTouches.size();
	}

	public int getRewardPeriodTouches() {
		return this.rewardPeriodTouches.size();
	}

	public int getInterRatioIntervalTouches() {
		return this.interRatioTouches.size();
	}

	public float getAverageTimeBetweenValid() {
		if (this.validTouches.size() < 2) {
			return Float.NaN;
		} else {
			float total = 0;
			float touch1 = Float.NaN;
			boolean skip = true;

			for (Float touch : this.validTouches) {
				if (skip) {
					skip = false;
					touch1 = touch;
					continue;
				}
				total += touch - touch1;
				touch1 = touch;
			}
			return (total / (this.getValidTouches()-1));
		}
	}

	public float getMedianTimeBetweenValid() {
		if (this.validTouches.size() < 2) {
			return Float.NaN;
		} else {
			float[] intervals = new float[this.validTouches.size() - 1];
			float touch1 = Float.NaN;
			boolean skip = true;
			int i = 0;

			for (Float touch : this.validTouches) {
				if (skip) {
					skip = false;
					touch1 = touch;
					continue;
				}
				intervals[i] = touch - touch1;
				i++;
				touch1 = touch;
			}
			Arrays.sort(intervals);
			//if even, return midpoint between center values
			if(intervals.length % 2 == 0) {
				float centerRight = intervals[intervals.length/2];
				float centerLeft = intervals[(intervals.length/2) -1];
				return centerLeft + (centerRight - centerLeft)/2;
			}
			//if odd return the middle value
			return intervals[intervals.length / 2];
		}
	}

	public float getMedianTimeBetweenCenter() {
		ArrayList<Float> centerTouches = new ArrayList<>();
		centerTouches.addAll(validTouches);
		centerTouches.addAll(itiTouches);
		Collections.sort(centerTouches);
		if (centerTouches.size() < 2) {
			return Float.NaN;
		} else {
			float[] intervals = new float[centerTouches.size() - 1];
			float touch1 = Float.NaN;
			boolean skip = true;
			int i = 0;

			for (Float touch : centerTouches) {
				if (skip) {
					skip = false;
					touch1 = touch;
					continue;
				}
				intervals[i] = touch - touch1;
				i++;
				touch1 = touch;
			}
			Arrays.sort(intervals);
			//if even, return midpoint between center values
			if(intervals.length % 2 == 0) {
				float centerLeft = intervals[(intervals.length/2)-1];
				float centerRight = intervals[(intervals.length/2)];
				return centerLeft + (centerRight - centerLeft)/2;
			}
			//if odd return the middle value
			return intervals[intervals.length / 2];
		}
	}

	public float getAverageTimeBetweenCenter() {
		ArrayList<Float> centerTouches = new ArrayList<>();
		centerTouches.addAll(validTouches);
		centerTouches.addAll(itiTouches);
		Collections.sort(centerTouches);
		if (centerTouches.size() < 2) {
			return Float.NaN;
		} else {
			float total = 0;
			float touch1 = Float.NaN;
			boolean skip = true;

			for (Float touch : centerTouches) {
				if (skip) {
					skip = false;
					touch1 = touch;
					continue;
				}
				total += touch - touch1;
				touch1 = touch;
			}
			return (total / (centerTouches.size()-1));
		}
	}

}
