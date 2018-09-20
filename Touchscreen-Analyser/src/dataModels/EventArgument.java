package dataModels;

public class EventArgument {
	
	private String name;

	private float value;
	
	
	public EventArgument(String name, float value) {
		this.name = name;
		this.value = value;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the value
	 */
	public float getValue() {
		return value;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventArgument [name=" + name + ", value=" + value + "]";
	}
	
	
	
	
	
}
