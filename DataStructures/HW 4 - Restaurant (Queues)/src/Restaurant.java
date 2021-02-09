/**
 * Represents a Restaurant that takes Customer Objects and seats them
 * @author Luis Abrogar (ID 111614684)
 *  CSE 214 R09 Spring '20 
 */

import java.util.LinkedList;
public class Restaurant extends LinkedList<Customer>{
	public Restaurant() {}
	
	/**
	 * This method adds a customer to the Restaurant Queue
	 * @param c
	 * The customer to be added
	 */
	public void enqueue(Customer c) {
		super.add(c);
	}
	/**
	 * This method takes a customer out of the Restaurant Queue
	 * @param index
	 * The index of the customer to be removed
	 * @return
	 * The removed customer
	 */
	public Customer dequeue(int index) {
		Customer holder = get(index);
		super.remove(index);
		return holder;
	}
	
	/**
	 * This method checks for the first customer in the Queue
	 * @return
	 * The first customer
	 */
	public Customer peek() {
		return super.peekFirst();
	}
	/**
	 * This method checks for the size of the Queue
	 * @return
	 * The size of the Queue
	 */
	public int size() {
		return super.size();
	}
	/**
	 * This method checks if the Queue is empty
	 * @return
	 * True if it is empty, False if not
	 */
	public boolean isEmpty() {
		return (super.size() == 0); 
	}
	
	/**
	 * This method simply converts the Restaurant Queue's information to a String format
	 */
	public String toString() {
		if (this.isEmpty())
			return "{}";
		String holder = "{";
		for(int x = 0; x < this.size()-1; x++) {
			holder += this.get(x).toString() + " , ";
		}
		holder += this.get(this.size()-1).toString();
		holder += "}";
		return holder;
	}
}
