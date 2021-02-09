/**
 * This class represents the blood type of a patient, and deals with compatibility
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
public class BloodType {
	//Member Variables
	private String bloodType;
	//Constructor
	public BloodType() {}
	public BloodType(String type) {
		bloodType = type;
	}
	//Getter Method
	public String getBType() {
		return bloodType;
	}
	//Member Functions
	/**
	 * This method determines if two blood types are compatible with each other
	 * @param recipient
	 * The recipient
	 * @param donor
	 * The donor
	 * @return
	 * true if they are compatible, false if not
	 */
	public static boolean isCompatible(BloodType recipient, BloodType donor) {
		String reciever = recipient.getBType().toLowerCase();
		String giver = donor.getBType().toLowerCase();
		switch(reciever) {
		case "o":
			if (giver == "o") 
				return true;
			else 
				return false;
		case "a":
			if (giver == "o" || giver == "a")
				return true;
			else
				return false;
		case "b":
			if (giver == "o" || giver == "b") 
				return true;
			else
				return false;
		case "ab":
			return true;
		default:
			System.out.println("Invalid recipient bloodtype");
			return false;
		}
	}
}
