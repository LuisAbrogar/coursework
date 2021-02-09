/**
 * This class represents a General Ledger - or a list of Transactions 
 * This interacts with the member variables and member functions of the Transaction class
*
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09  , Spring '20
 *
 */

public class GeneralLedger {
	private static final int MAX_TRANSACTIONS = 50; //Capacity of the ledger - can be adjusted
	private Transaction[] ledger;
	private double totalDebitAmount = 0;
	private double totalCreditAmount = 0;
	private int counter = 0;
	private int current;
	
	/**
	 * This is a Constructor used to create a new GeneralLedger object
	 */
	public GeneralLedger() {
		this.ledger = new Transaction[MAX_TRANSACTIONS];
	}
	
	//Getter Methods
	/**
	 * This method gets the list of Transactions
	 * @return
	 * 	ledger - the transaction list
	 * 
	 */
	public Transaction[] getLedger() {
		return this.ledger;
	}
	/**
	 * This method gets the total amount of Debit transactions (positive)
	 * @return
	 * 	total debit transactions
	 */
	public double getTotalDebit() {
		return this.totalDebitAmount;
	}
	/**
	 * This method gets the total amount of Credit transactions (negative)
	 * @return
	 * 	total credit transactions
	 */
	public double getTotalCredit() {
		return this.totalCreditAmount;
	}
	/**
	 * This method gets the Ledger's counter, which helps calculate how much transactions is in it
	 * @return
	 * 	the counter
	 */
	public int getCounter() {
		return this.counter;
	}
	/**
	 * This method gets the current index of the Transaction in the ledger that is pointed to by the .exists method
	 * @return
	 * 	index of the transaction pointed to by the .exists method
	 */
	public int getCurrent() {
		return this.current;
	}
	/**
	 * This method gets the Maximum Capacity of the ledger
	 * @return
	 * 	MAX_TRANSACTIONS - the maximum ledger capacity
	 */
	public int getMax() {
		return MAX_TRANSACTIONS;
	}
	
	//Main Methods
	/**
	 * This method adds a Transaction into the ledger
	 * 
	 * @param newTransaction
	 * 	The Transaction to be added
	 * 
	 * @throws InvalidTransactionException
	 * 	When the date is invalid; year is >2050 or <1900, day is >30 or <=0, month is >12 or <= 0, or date has more or less than 10 digits in total
	 * 	When the amount is invalid; the amount is 0
	 * 
	 * @throws TransactionAlreadyExistsException
	 * 	When the transaction being added is already in the ledger, there should be no identical transactions
	 * 
	 * @throws FullGeneralLedgerException
	 * 	When the ledger is already full
	 */
	public void addTransaction(Transaction newTransaction) throws InvalidTransactionException, TransactionAlreadyExistsException, FullGeneralLedgerException {
		String date = newTransaction.getDate();
		double amt = newTransaction.getAmount();
		boolean validinput = true;
		try {
		if((date.length() > 10) || (date.length() < 10))
			throw new InvalidTransactionException("Invalid Date", "Add");
		if ((date.substring(0,4).compareTo("2050") > 0) || (date.substring(0,4).compareTo("1900") <= 0) || 						//Verify Year
		   (date.substring(5,7).compareTo("12") > 0) || (date.substring(5,7).compareTo("0") <= 0) ||									//Verify Month
		   (date.substring(8,date.length()).compareTo("30") > 0) || (date.substring(8,date.length()).compareTo("0") <= 0)) 			//Verify Day
			throw new InvalidTransactionException("Invalid Date", "Add");
		if (amt == 0)
			throw new InvalidTransactionException("Invalid Amount", "Add");
		if (this.isEmpty() == false) 
			if (this.exists(newTransaction)) 
				throw new TransactionAlreadyExistsException("Transaction already exists", "Add");
		if (this.counter == this.getMax())
			throw new FullGeneralLedgerException("Ledger is full", "Add");
		
		while (validinput) {
			if (ledger[counter] == null) {
				ledger[counter] = newTransaction;
				//Amount sorter
				if (amt > 0)
					totalDebitAmount = totalDebitAmount + amt;
				else
					totalCreditAmount = totalCreditAmount + amt;
				counter++;
				break;
			}
			else
				counter++;
		}
		}
		catch (InvalidTransactionException e){
			validinput = false;
		}
		catch (TransactionAlreadyExistsException e) {
			validinput = false;
		}
		catch (FullGeneralLedgerException e) {
			validinput = false;
		}
	}
	
	/**
	 * This method removes a Transaction in the ledger, using its position in the list
	 * 
	 * @param position
	 * 	The position of the Transaction to be removed
	 * 
	 * @throws InvalidLedgerPositionException
	 * 	When the position given is invalid
	 */
	public void removeTransaction(int position) {
		try {
		if (position > counter) 
			throw new InvalidLedgerPositionException("Invalid Position", "Remove");
		for(int x = position; x < counter; x++) {
			this.ledger[x -1] = (Transaction) ledger[x].clone();
		}
		counter--;
		}
		catch (InvalidLedgerPositionException e) {
		}
	}
	/**
	 * This method prints the whole ledger
	 */
	public void printAllTransactions() {
		if (this.isEmpty())
			System.out.println("There are currently no Transactions in the General Ledger");
		else {
		printHeader();
		String printer = this.toString();
		System.out.println(printer);
		}
	}
	/**
	 * This method retrieves a Transaction object using its position in the list
	 * @param x
	 * 	The position of the Transaction to retrieve
	 * @return 
	 * 	The Transaction in that position, otherwise return nothing
	 * 
	 * @throws InvalidLedgerPositionException
	 * 	When the position given is invalid
	 */
	public Transaction getTransaction(int x) {
		try {
		if (x > this.getCounter())
			throw new InvalidLedgerPositionException("Invalid Position", "Get");
		printHeader();
		if (this.ledger[x - 1].getAmount() < 0)
			System.out.println((x) + "		"+ this.ledger[x - 1].getDate()+ "				" + (this.ledger[x - 1].getAmount() * -1) + "		" + this.ledger[x - 1].getDescription());
		else
			System.out.println((x) + "		"+ this.ledger[x - 1].getDate()+ "		" + (this.ledger[x - 1].getAmount()) + "				" + this.ledger[x - 1].getDescription());
		return ledger[x - 1];
		}
		catch (InvalidLedgerPositionException e) {
			return (Transaction) null;
		}
	}
	/**
	 * This method prints all the Transactions that have the given date
	 * @param generalLedger
	 * 	The ledger to search in/filter
	 * @param date
	 * 	The date of the Transactions to look for
	 */
	public static void filter(GeneralLedger generalLedger, String date) {
		boolean status = false;
		for (int x = 0; x < generalLedger.getCounter(); x++ ) {
			if(generalLedger.ledger[x].getDate().compareTo(date) == 0) {
				if (generalLedger.ledger[x].getAmount() < 0) {
					System.out.println((x + 1) + "		"+ generalLedger.ledger[x].getDate()+ "				" + (generalLedger.ledger[x].getAmount() * -1) + "		" + generalLedger.ledger[x].getDescription());
					status = true; }
				else {
					System.out.println((x + 1) + "		"+ generalLedger.ledger[x].getDate()+ "		" + (generalLedger.ledger[x].getAmount()) + "				" + generalLedger.ledger[x].getDescription()); 
					status = true; }
			}
		}
		if (status)
			return;
		else {
			System.out.println("No Transactions found in the given date");
			return;
		}
	}
	/**
	 * This method creates a deep copy of this GeneralLedger
	 */
	public Object clone() {
		try {
		if (this instanceof GeneralLedger) {
			GeneralLedger copy = new GeneralLedger();
			for (int x = 0; x < this.getCounter(); x++) {
				copy.addTransaction(this.ledger[x]);
			}
			return copy;
		}
		else {
			System.out.println("ledger clone failed");
			return this;
		}
		}
		catch(Exception e) {
			return this;
		}

	}
	/**
	 * This method verifies whether or not a Transaction is in the ledger
	 * 
	 * @param transaction
	 *	The transaction to look for 
	 * @return
	 * 	true if the Transaction is in the ledger, false if not
	 */
	public boolean exists(Transaction transaction) {
		Transaction[] test = this.getLedger();
		try {
		if (transaction == null)
			throw new IllegalArgumentException();
		for(int x = 0; x < this.getCounter(); x++) {
			if(transaction.equals(test[x])) {
				this.current = x;
				return true;
			}
		}
		return false;
		}
		catch (IllegalArgumentException e){
		return false;
		}
	}
	/**
	 * This method simply returns the number of transactions in the ledger
	 * @return
	 *  amount of transactions in the ledger
	 */
	public int size() {
		return (counter - 1);
	}
	/**
	 * This method converts the full list into a string to be printed by the print method
	 */
	public String toString() {
		String stringLedger = "";
		this.sortList();
		for (int x = 0; x < this.getCounter(); x++) {
			if (this.ledger[x].getAmount() < 0)
				stringLedger += (x + 1) + "		"+ this.ledger[x].getDate()+ "				" + (this.ledger[x].getAmount() * -1) + "		" + this.ledger[x].getDescription() + "\n";
			else
				stringLedger += (x + 1) + "		"+ this.ledger[x].getDate()+ "		" + (this.ledger[x].getAmount()) + "				" + this.ledger[x].getDescription() + "\n";
		}
		return stringLedger;
	}
	
	//Helper Methods
	/**
	 * This method is a sorting algorithm used to sort the Transaction dates
	 */
	public void sortList() {
		for(int x = 0; x < this.getCounter(); x++) {
			for(int y = 0; y <= this.getCounter() - 1; y++) {
				 if(ledger[x].getDate().compareTo(ledger[y].getDate()) < 0) {
					 Transaction holder = (Transaction) ledger[y].clone();
					 ledger[y] = ledger[x];
					 ledger[x] = holder;
				}
			}
		}
	}
	/**
	 * This method if the Ledger is empty
	 * @return
	 * true if it is, false if not
	 */
	public boolean isEmpty() {
		if (this.counter == 0) 
			return true;
		else
			return false;
	}
	/**
	 * Checks if this ledger is equal to another ledger, or the backup
	 * @param backup
	 * 	The ledger to compare the current ledger with
	 * @return
	 */
	public boolean isEqualTo(GeneralLedger backup) {
		for (int x = 0; x < counter; x++) {
			if (backup.ledger[x].equals(this.ledger[x])) 
				continue;
			else
				return false;
		}
		return true;
	}
	/**
	 * This simply prints out the header for the list - for aesthetic/readability purposes
	 */
	public static void printHeader() {
		System.out.println("No.		Date			Debit		Credit		Description");
		System.out.println("-------------------------------------------------------------------------------------------");
	}
		
	
}
