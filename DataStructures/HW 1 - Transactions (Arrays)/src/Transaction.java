/**
 * This class represents a single transaction that was made
 * 
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09  , Spring '20
 *
 */
public class Transaction{
	private String date, description;
	private double amount;
	
	/**
	 * This is a Constructor used to create a new Transaction object
	 * @param t_date
	 * 	The date of the transaction
	 * @param t_description
	 * 	The description of the transaction
	 * @param t_amount
	 * 	The amount of the transaction
	 */
	public Transaction(String t_date, String t_description, double t_amount) {
		date = t_date;
		description = t_description;
		amount = t_amount;
	}
	/**
	 * This is a Constructor used to create a new Transaction object, without any specified parameters
	 */
	public Transaction() {};
	
	/**
	 * This method gets the Transaction's date
	 * @return
	 * 	date of the Transaction
	 */
	public String getDate() {
		return date;
	} 
	/**
	 * This method gets the Transaction's description
	 * @return
	 * 	description of the Transaction
	 */
	public String getDescription() {
		return description;
	}
	/** 
	 * This method gets the Transaction's amount
	 * @return
	 * 	transaction amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * This method checks if this transaction is equal to another transaction object
	 * @return
	 * 	true if they are equal, false if they are not
	 */
	public boolean equals(Object obj) {
		if(obj instanceof Transaction) {
			Transaction obj2 = (Transaction) obj;
			return (obj2.getDate().compareTo(this.getDate()) == 0) && (obj2.getDescription().compareTo(this.getDescription()) == 0)
					&& (obj2.getAmount() == this.getAmount());			
		}
		else
			return false;
		
	}
	
	/**
	 * This method creates a deep clone of this transaction
	 * @return
	 * 	a clone of this transaction if it is a Transaction object, if not it returns an empty Transaction
	 */
	public Object clone() {
		if (this instanceof Transaction) {
		Transaction copy = new Transaction(this.getDate(),this.getDescription(),this.getAmount());
		return copy;
		}
		else { 
			System.out.println("Not instance of transaction");
			return (Transaction) null;
		
		}
	}
}

