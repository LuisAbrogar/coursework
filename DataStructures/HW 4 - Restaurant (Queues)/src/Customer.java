/**
 * Represents an instance of a customer that will dine at a restaurant
 * @author Luis Abrogar (ID 111614684) 
 * CSE 214 R09 Spring '20
 *
 */
public class Customer {
	//Static Variables
	private static int totalCustomers;
	//Member Variables
	private int orderNumber, priceOfFood, timeArrived, timeToServe;
	private String food;
	
	public Customer() {}
	public Customer(int num) {
		orderNumber = num;
	}
	/**
	 * Setter Methods
	 */
	public void setFood(String f) {
		food = f;
	}
	public void setPrice(int p) {
		priceOfFood = p;
	}
	public void setOrderNumber(int o) {
		orderNumber = o;
	}
	public void setTimeServe(int t) {
		timeToServe = t;
		timeArrived = t;
	}
	/**
	 * Getter Methods
	 */
	public int getServeTime() {
		return timeToServe;
	}
	public int getPrice() {
		return priceOfFood;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public int getArrival() {
		return timeArrived;
	}
	/**
	 * This decrements the service time of a customer by a given amount
	 * @param x
	 * Amount of time to decrement the service time by
	 */
	public void decTime(int x) {
		timeToServe -= x;
	}
	/**
	 * This puts the Customer's information in a String format
	 */
	public String toString() {
		return ("[#" + orderNumber + " , " + food + " , " + timeToServe + " min]");
	}
}
