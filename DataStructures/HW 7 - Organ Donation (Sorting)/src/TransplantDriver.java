/**
 * This simply acts as the driver for the donation program
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;
public class TransplantDriver {
	public static final String DONOR_FILE = "donors.txt";
	public static final String RECIPIENT_FILE = "recipients.txt";
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		PrintStream p = System.out;
		boolean active = true;
		try {
		TransplantGraph input = TransplantGraph.buildFromFiles(DONOR_FILE, RECIPIENT_FILE);
		
		while(active) {
			p.print("Menu:\r\n" + 
					"    (LR) - List all recipients\r\n" + 
					"    (LO) - List all donors\r\n" + 
					"    (AO) - Add new donor\r\n" + 
					"    (AR) - Add new recipient\r\n" + 
					"    (RO) - Remove donor\r\n" + 
					"    (RR) - Remove recipient\r\n" + 
					"    (SR) - Sort recipients\r\n" + 
					"    (SO) - Sort donors\r\n" + 
					"    (Q) - Quit\r\n" + 
					" \r\n" + 
					"Please select an option: ");
			String option = stdin.nextLine();
			option = option.toLowerCase();
			p.println();
			switch(option) {
			//list recipients
			case "lr":
				input.printAllRecipients();
				break;
			//list donors
			case "lo":
				input.printAllDonors();
				break;
			//add donor
			case "ao":
				p.print("Please enter the organ donor name: ");
				String name = stdin.nextLine();
				p.print("Please enter the organs " + name + " is donating: ");
				String organ = stdin.nextLine();
				p.print("Please enter the blood type of " + name + " : ");
				String type = stdin.nextLine();
				p.print("Please enter the age of " + name + " : ");
				int age = stdin.nextInt();
				int id = input.getDonorListSize();
				Patient incoming = new Patient(id,name,age,organ,type);
				input.addDonor(incoming);
				p.println("The organ donor with ID " + id + " was successfully added to the donor list!" );
				stdin.nextLine();
				break;
			//add recipient
			case "ar":
				p.print("Please enter new recipient's name: ");
				String name2 = stdin.nextLine();
				p.print("Please enter the recipient's blood type: ");
				String type2 = stdin.nextLine();
				p.print("Please enter the recipient's age: ");
				int age2 = stdin.nextInt();
				p.print("Please enter the organ needed: ");
				stdin.nextLine();
				String organ2 = stdin.nextLine();
				int id2 = input.getRecipListSize();
				Patient incoming2 = new Patient(id2,name2,age2,organ2,type2);
				input.addRecipient(incoming2);
				p.println(name2 + " is now on the organ transplant waitlist!");
				break;
			//remove donor
			case "ro":
				p.print("Please enter the name of the organ donor to remove: ");
				String rDonor = stdin.nextLine();
				input.removeDonor(rDonor);
				break;
			//remove recipient	
			case "rr":
				p.print("Please enter the name of the recipient to remove: ");
				String rRecip = stdin.nextLine();
				input.removeRecipient(rRecip);
				break;
			case "sr":
				p.print("(I) Sort by ID\r\n" + 
						"    (N) Sort by Number of Recipients\r\n" + 
						"    (B) Sort by Blood Type\r\n" + 
						"    (O) Sort by Organ Donated\r\n" + 
						"    (Q) Back to Main Menu\r\n" + 
						"\r\n" + 
						"Please select an option: ");
				String subR = stdin.nextLine();
				subR = subR.toLowerCase();
				boolean insideR = true;
				while(insideR) {
					switch(subR) {
					case "i":
					case "n":
					case "b":
					case "o":
					case "q":
						System.out.println("Returning to main menu.");
						insideR = false;
						break;
					}
				}
			case "so":
				p.print("(I) Sort by ID\r\n" + 
						"    (N) Sort by Number of Recipients\r\n" + 
						"    (B) Sort by Blood Type\r\n" + 
						"    (O) Sort by Organ Donated\r\n" + 
						"    (Q) Back to Main Menu\r\n" + 
						"\r\n" + 
						"Please select an option: ");
				String subD = stdin.nextLine();
				subD = subD.toLowerCase();
				boolean insideD = true;
				while(insideD) {
					switch(subD) {
					case "i":
					case "n":
					case "b":
					case "o":
					case "q":
						System.out.println("Returning to main menu.");
						insideD = false;
						break;
					}
				}
			case "q":
				active = false;
				break;
				}
			}	
		}
		catch(FileNotFoundException e) {
			System.out.println("FILE NOT FOUND!");
		}
	}
}
