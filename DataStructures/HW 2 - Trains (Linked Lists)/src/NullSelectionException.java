/**
 * This exception is thrown when a selection is null
 * @author Luis
 *
 */
public class NullSelectionException extends Exception {
	public NullSelectionException(String ObjectType, String method, String errorMessage ) {
		super(errorMessage);
		System.out.println(ObjectType + " failed to " + method + " : " + errorMessage);
	}
}
