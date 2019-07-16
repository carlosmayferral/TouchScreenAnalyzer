package TinaAnalysis;

import dataModels.SessionParameters;

public class TinaSessionParameters extends SessionParameters {

	private float cue_time;
	private float hold_time;
	private float target_time;
	private float finish_time;

	public TinaSessionParameters(float cue_time, float hold_time, float target_time, float finish_time) {
		this.cue_time = cue_time;
		this.hold_time = hold_time;
		this.target_time = target_time;
		this.finish_time = finish_time;
	}

	public float getCueTime() {
		return this.cue_time;
	}

	public float getHoldTime() {
		return this.hold_time;
	}
	
	public float getTargetTime() {
		return this.target_time;
	}
	
	public float getFinishTime() {
		return finish_time;
	}

}
