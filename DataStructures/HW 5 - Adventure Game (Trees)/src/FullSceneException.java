/**
 * This exception is thrown when a particular scene is full, or already has 3 children
 *  @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
public class FullSceneException extends Exception {
	public FullSceneException() {
		System.out.println("Current Scene is Full!");
	}
}
