/**
 * This is an exception that is thrown if a node trying to be found does not exist
 *  @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
public class NoSuchNodeException extends Exception {
	public NoSuchNodeException() {
		super();
		System.out.println("No Such Node Exists!");
		
	}
}
