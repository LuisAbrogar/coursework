/**
 * This Exception is thrown when an Object already exists
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
public class AlreadyExistsException extends Exception {
	public AlreadyExistsException(String ObjectType, String method, String errorMessage ) {
		super(errorMessage);
		System.out.println(ObjectType + " failed to " + method + " : " + errorMessage);
	}
}
