/**
 * This class acts as the driver for the Grocery Store and updates its inventory
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.util.Scanner;
import java.io.*;
import java.io.PrintStream;
public class GroceryDriver {
	public static void main(String[] args) {
		PrintStream p = System.out;
		Scanner stdin = new Scanner(System.in);
		boolean active = true;
		int dayCounter = 0;
		//if there is a save file
		try {
			FileInputStream file = new FileInputStream("grocery.obj");
	        ObjectInputStream fin  = new ObjectInputStream(file);        
	        HashedGrocery driver = (HashedGrocery) fin.readObject();
	        fin.close();   
	        while(active) {
				if (driver.getDay() != dayCounter) {
					p.print("Business Day " + driver.getDay() + ". ");
					dayCounter++;
				}
				p.print("\nMenu: \n"
						+ "(L): Load item catalog\n"
						+ "(A): Add items\n"
						+ "(B): Process Sales\n"
						+ "(C): Display all items\n"
						+ "(N): Move to next business day\n"
						+ "(Q): Quit\n\n"
						+ "Enter option: ");
				String option = stdin.nextLine();
				option = option.toLowerCase();
				switch(option) {
				case "l":
					try {
					p.print("Enter file to load: ");
					String filename = stdin.nextLine();
					driver.addItemCatalog(filename);
					}
					catch(Exception e) {
						p.println(e.getMessage());
					}
					break;
				case "a":
					p.print("Enter item code: ");
					String code = stdin.nextLine();
					p.print("Enter item name: ");
					String name = stdin.nextLine();
					p.print("Enter Quantity in store: ");
					int qty = stdin.nextInt();
					p.print("Enter Average sales per day: ");
					int avg = stdin.nextInt();
					p.print("Enter price: ");
					double price = stdin.nextDouble();
					Item input = new Item(code,name,qty,avg,price);
					driver.addItem(input);
					break;
				case "b":
					try {
					p.print("Enter filename: ");
					String filename = stdin.nextLine();
					driver.processSales(filename);
					}
					catch(Exception e) {
					}
					break;
				case "c":
					p.println("Item code   Name                Qty   AvgSales   Price    OnOrder    ArrOnBusDay");
					p.println("--------------------------------------------------------------------------------");
					p.print(driver.toString());
					break;
				case "n":
					p.println("Advancing business day...");
					driver.nextBusinessDay();
					break;
				case "q":
					p.println("System quitting...");
					active = false;
					driver.save();
					break;
				default:
					p.println("Invalid Option");
					break;
				}
	        }
		}
		//if there is no save file
		catch(ClassNotFoundException y) {
			p.print("Class not found");
		}
		catch(IOException x) {
			p.print("grocery.obj does not exist. Creating new HashedGrocery object...");
			HashedGrocery driver = new HashedGrocery();
		
		
		while(active) {
			if (driver.getDay() != dayCounter) {
				p.print("Business Day " + driver.getDay() + ". ");
				dayCounter++;
			}
			p.print("\nMenu: \n"
					+ "(L): Load item catalog\n"
					+ "(A): Add items\n"
					+ "(B): Process Sales\n"
					+ "(C): Display all items\n"
					+ "(N): Move to next business day\n"
					+ "(Q): Quit\n\n"
					+ "Enter option: ");
			String option = stdin.nextLine();
			option = option.toLowerCase();
			switch(option) {
			case "l":
				try {
				p.print("Enter file to load: ");
				String filename = stdin.nextLine();
				driver.addItemCatalog(filename);
				}
				catch(Exception e) {
					p.println(e.getMessage());
				}
				break;
			case "a":
				p.print("Enter item code: ");
				String code = stdin.nextLine();
				p.print("Enter item name: ");
				String name = stdin.nextLine();
				p.print("Enter Quantity in store: ");
				int qty = stdin.nextInt();
				p.print("Enter Average sales per day: ");
				int avg = stdin.nextInt();
				p.print("Enter price: ");
				double price = stdin.nextDouble();
				Item input = new Item(code,name,qty,avg,price);
				driver.addItem(input);
				break;
			case "b":
				try {
				p.print("Enter filename: ");
				String filename = stdin.nextLine();
				driver.processSales(filename);
				}
				catch(Exception e) {
				}
				break;
			case "c":
				p.println("Item code   Name                Qty   AvgSales   Price    OnOrder    ArrOnBusDay");
				p.println("--------------------------------------------------------------------------------");
				p.print(driver.toString());
				break;
			case "n":
				p.println("Advancing business day...");
				driver.nextBusinessDay();
				break;
			case "q":
				p.println("System quitting...");
				active = false;
				driver.save();
				break;
			default:
				p.println("Invalid Option");
				break;
			}
		}}
		
	}
}
