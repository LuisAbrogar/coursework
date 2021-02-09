/**
 * This class is an instance of a transplant graph
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TransplantGraph {
	//Member Variables
	public static final int MAX_PATIENTS = 100;
	private ArrayList<Patient> donors = new ArrayList<Patient>();
	private ArrayList<Patient> recipients = new ArrayList<Patient>();
	private boolean[][] connections = new boolean[MAX_PATIENTS][MAX_PATIENTS];
	
	//Constructors
	public TransplantGraph() {}
	//Getter Methods
	public int getDonorListSize() {
		return donors.size();
	}
	public int getRecipListSize() {
		return recipients.size();
	}
	//Member Functions
	public static TransplantGraph buildFromFiles(String donorFile, String recipientFile) throws FileNotFoundException {
		TransplantGraph input = new TransplantGraph();
		FileReader donorList = new FileReader(donorFile);
		FileReader recipList = new FileReader(recipientFile);
		Scanner donorScan = new Scanner(donorList);
		Scanner recipScan = new Scanner(recipList);
		
		while (donorScan.hasNextLine()) {
			String s = donorScan.nextLine();
			String[] list = s.split(", ");
			int id = Integer.parseInt(list[0]);
			String name = list[1];
			int age = Integer.parseInt(list[2]);
			String organ = list[3];
			String type = list[4];
			Patient temp = new Patient(id,name,age,organ,type);
			input.addDonor(temp);
		}
		while (recipScan.hasNextLine()) {
			String s = recipScan.nextLine();
			String[] list = s.split(", ");
			int id = Integer.parseInt(list[0]);
			String name = list[1].trim();
			int age = Integer.parseInt(list[2]);
			String organ = list[3].trim();
			String type = list[4].trim();
			Patient temp = new Patient(id,name,age,organ,type);
			input.addRecipient(temp);
		}
		//input.setConnections();
		return input;
	}
	public void addRecipient(Patient patient) {
		patient.setRecip();
		recipients.add(patient);
	}
	public void addDonor(Patient patient) {
		patient.setDonor();
		donors.add(patient);
	}
	public void removeRecipient(String name) {
		for (int x = 0; x < recipients.size(); x++) {
			if (recipients.get(x).getName().toLowerCase().trim().compareTo(name.toLowerCase().trim()) == 0) {
				System.out.println(name + " was removed from the transplant waitlist");
				recipients.remove(x);
				this.updateRIndex();
				return;
			}
		}
		System.out.println("Patient " + name + " is not on the recipient list");
	}
	public void removeDonor(String name) {
		for (int x = 0; x < donors.size(); x++) {
			if (donors.get(x).getName().toLowerCase().trim().compareTo(name.toLowerCase().trim()) == 0) {
				System.out.println(name + " was removed from the organ donor list");
				donors.remove(x);
				this.updateDIndex();
				return;
			}
		}
		System.out.println("Patient " + name + " is not on the donor list");
	}
	public void printAllRecipients() {
		System.out.println("Index\t| Recipient Name \t| Age \t| Organ Needed \t| Blood Type | Donor IDs");
		System.out.println("==========================================================================");
		for (int x = 0; x < recipients.size(); x++) {
			System.out.println(recipients.get(x).toString() + getRecipConnections(x));
		}
	}
	public void printAllDonors() {
		System.out.println("Index\t| Recipient Name \t| Age \t| Organ Needed \t| Blood Type | Donor IDs");
		System.out.println("==========================================================================");
		for (int x = 0; x < donors.size(); x++) {
			System.out.println(donors.get(x).toString() + getDonorConnections(x));
		}
	}
	public void updateDIndex() {
		for (int x = 0; x < donors.size(); x++) {
			donors.get(x).setID(x);
		}
	}
	public void updateRIndex() {
		for (int y = 0; y < recipients.size(); y++) {
			recipients.get(y).setID(y);
		}
	}
	public void setConnections() {
		for(int x = 0; x < donors.size(); x++) {
			for(int y = 0; y < recipients.size(); y++) {
				connections[x][y] = BloodType.isCompatible(recipients.get(x).getBloodType(), donors.get(y).getBloodType());
			}
		}
	}
	public String getDonorConnections(int index) {
		String conn = "";
		for(int y = 0; y < recipients.size(); y++) {
			if (BloodType.isCompatible(recipients.get(y).getBloodType(), donors.get(index).getBloodType())) {
				conn += y + " ";
			}
		}
		return conn;
	}
	public String getRecipConnections(int index) {
		String conn = "";
		for(int x = 0; x < donors.size(); x++) {
			if (BloodType.isCompatible(recipients.get(index).getBloodType(), donors.get(x).getBloodType())) {
				conn += x + " ";
			}
		}
		return conn;
	}
	
}
