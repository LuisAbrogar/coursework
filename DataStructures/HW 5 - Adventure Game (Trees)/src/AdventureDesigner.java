/**
 * This method acts as a driver for the Scene Tree Class, and uses it for an adventure game
 *  @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.util.Scanner;
import java.io.PrintStream;
public class AdventureDesigner {
	public static SceneTree story = new SceneTree();
	/**
	 * This method is the driver for this class
	 */
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		PrintStream p = System.out;
		boolean running = true;
		p.println("Creating a story...");
		p.print("Please enter a title: ");
		String name = stdin.nextLine();
		p.print("Please enter a scene: ");
		String desc = stdin.nextLine();
		try {
			story.addNewNode(name,desc);
		}
		catch (Exception e) {
			p.println("Failed to add");
		}
		while(running) {
		p.print("A) Add Scene\r\n" + 
				"R) Remove Scene\r\n" + 
				"S) Show Current Scene\r\n" + 
				"P) Print Adventure Tree\r\n" + 
				"B) Go Back A Scene\r\n" + 
				"F) Go Forward A Scene\r\n" + 
				"G) Play Game\r\n" + 
				"N) Print Path To Cursor\r\n" + 
				"M) Move Scene\r\n" + 
				"Q) Quit\n" +
				"Enter Selection: ");
		String choice = stdin.next();
		choice = choice.toLowerCase();
		switch(choice) {
			//Add Scene
			case "a":
				stdin.nextLine();
				p.print("Please enter a title: ");
				String title = stdin.nextLine();
				p.print("Please enter a scene: ");
				String scene = stdin.nextLine();
				p.println(title);
				p.println(scene);
				try {
				story.addNewNode(title,scene);
				p.println("Scene added successfully");
				break;
				}
				catch (Exception e) {
					p.println("Scene failed to add");
					break;
				}
			//Remove Scene
			case "r":
				p.print("Please Enter an Option (A/B/C): ");
				String opt = stdin.next();
				opt = opt.toLowerCase();
				try {
				if (opt == "a" || opt == "b" || opt == "c")
					story.removeScene(opt);
				else p.print("Invalid Option!");
				break;
				}
				catch(Exception e) {}
				
			//Show Current Scene	
			case "s":
				story.getCursor().displayFullScene();
				break;
			//Print Tree
			case "p":
				story.printer();
				break;
			//Go back a Scene
			case "b":
				try {
				story.moveCursorBackwards();
				break;
				}
				catch (Exception e) {
					break;
				}
			//Go Forward a Scene
			case "f":
				p.println("Which Option do you want to jump to?: ");
				String option = stdin.next();
				option = option.toLowerCase();
				try {
					story.moveCursorForward(option);
				}
				catch (Exception e) {
					
				}
			//Play Game
			case "g":
				p.println("Starting Game...\n");
				playGame();
				break;
			//Print Path to Cursor
			case "n":
				p.println("Path: " + story.getPathFromRoot());
				break;
			case "m":
				p.println("Note: I did not finish this method");
				break;
			case "q":
				p.println("System Quitting...");
				running = false;
				break;
			default:
				p.println("Invalid Choice");
				break;
				
			}
		
		}
	}
	/**
	 * This method provides the gameplay of the tree
	 */
	public static void playGame() {
		SceneNode ptr = story.getRoot();
		Scanner stdin = new Scanner(System.in);
		
		while(!ptr.isEnding()) {
			ptr.displayScene();
			System.out.print("Enter an option: ");
			String option = stdin.next();
			option = option.toLowerCase();
			try {
			story.moveCursorForward(option);
			ptr = story.getCursor();
			}
			catch (Exception e) {
			}
		}
		ptr.displayScene();
		System.out.println("The End!\n\nReturning to creation mode");
	}
	
}
