/**
 * This is an exception thrown by a member of the GeneralLedger class
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09  , Spring '20
 *
 */
public class FullGeneralLedgerException extends Exception {
	/**
	 * The Constructor for this exception
	 * @param errorMessage
	 * 	The error message
	 * @param method
	 * 	Specifies which method threw this exception
	 */
		public FullGeneralLedgerException(String errorMessage, String method) {
			super(errorMessage);
			System.out.println(errorMessage + " - Failed to " + method + " Transaction");
		}
	}