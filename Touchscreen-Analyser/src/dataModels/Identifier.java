package dataModels;

public class Identifier {
	
	public static final int NUMBER_OF_ID_VARIABLES = 2;

	private String animalId;
	
	private String groupId;
	
	public Identifier(String animalId, String groupId) {
		this.animalId = animalId;
		this.groupId = groupId;
	}
	
	public String getAnimalId() {
		return this.animalId;
	}
	
	public String getGroupId() {
		return this.groupId;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other.getClass().equals(this.getClass())) {
			Identifier otherIdentifier = (Identifier)other;
			if (this.getGroupId() != null) {
				if (otherIdentifier.getAnimalId().equals(this.getAnimalId()) && otherIdentifier.getGroupId().equals(this.getGroupId())) {
					return true;
				}
			}
			else {
				if (otherIdentifier.getGroupId() == null && otherIdentifier.getAnimalId().equals(this.getAnimalId())) {
					return true;
				}
			}

		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if (this.groupId == null) {
			return this.animalId.hashCode();
		}
		else {
			String idString = this.animalId + this.groupId;
			return idString.hashCode();
		}
	}
}
