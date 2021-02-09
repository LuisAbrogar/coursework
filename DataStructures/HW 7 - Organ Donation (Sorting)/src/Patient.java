/**
 * This class represents an instance of a patient - donor or recipient
 *@author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
public class Patient {
	//Member Variables
	private String name, organ;
	private int age, ID;
	private BloodType bloodtype;
	private boolean isDonor;
	//Constructors
	public Patient() {}
	public Patient(int patientID, String patientName, int patientAge, String patientOrgan, String patientBloodType) {
		ID = patientID;
		name = patientName;
		age = patientAge;
		organ = patientOrgan;
		bloodtype = new BloodType(patientBloodType);
	}
	//Getter Methods
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public BloodType getBloodType() {
		return bloodtype;
	}
	//Setter Methods
	public void setID(int id) {
		ID = id;
	}
	public void setDonor() {
		isDonor = true;
	}
	public void setRecip() {
		isDonor = false;
	}
	//Member Functions
	/**
	 * This method compares this Patient's ID to an Object o's ID
	 * @param o
	 * The object to be compared to this patient
	 * @return
	 * 1 if this Patient's ID is greater than, -1 if this Patient's ID is less than, 0 if o is not a Patient
	 */
	public int compareTo(Object o) {
		if (o instanceof Patient) {
			Patient hold = (Patient) o;
			if (this.getID() > hold.getID()) {
				return 1;
			}
			else
				return -1;
		}
		else
			return 0;
	}
	
	
	public String toString() {
		String holder = "";
		if (name.length() > 13) 
			holder += ID + "\t| " + name + "	| " + age + "\t| " + organ + "\t\t| " + bloodtype.getBType() + "\t\t|";
		else
			holder += ID + "\t| " + name + "\t\t| " + age + "\t| " + organ + "\t\t| " + bloodtype.getBType() + "\t\t|";
		return holder;
	}
}
