/**
 * This class represents a calculator, which drives the Equation, EquationStack, and HistoryStack Objects
 *  @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.util.Scanner;
public class Calculator {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		boolean active = true;
		while(active) {
		System.out.print("Main Menu:\n" +
						 "[A] Add new equation\r\n" + 
						 "[F] Change equation from history\r\n" + 
						 "[B] Print previous equation\r\n" + 
						 "[P] Print full history\r\n" + 
						 "[U] Undo\r\n" + 
						 "[R] Redo\r\n" + 
						 "[C] Clear history\r\n" + 
						 "[Q] Quit\n\n"
						 + "Select an option: ");
		String selection = stdin.next().toLowerCase();
		switch(selection) {
		//add equation
			case "a":
				System.out.print("Enter an Equation: ");
				String input = stdin.next();
				Equation in = new Equation(input);
				continue;
			case "f":
				continue;
			case "b":
				continue;
			case "p":
				continue;
			case "u":
				continue;
			case "r":
				continue;
			case "c":
				continue;
			case "q":
				System.out.println("System Quitting");
				active = false;
				continue;
			default:
				System.out.println("Invalid Choice");
				continue;
		}
		}
		
		stdin.close();
		
	
	}
	
}
