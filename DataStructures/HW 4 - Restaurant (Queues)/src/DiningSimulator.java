/**
 * Acts as a driver for the Restaurant Class, simulating the implementation
 * @author Luis Abrogar (ID: 111614684)
 *  CSE 214 R09 Spring '20
 *
 */
import java.util.Scanner;
import java.io.PrintStream;
import java.util.Random;
import java.util.LinkedList;
public class DiningSimulator {
	// MENU ITEMS - Indexes 0 to 4 pertain to a menu item with the following characteristics below
 	private static String[] abvfoods = {"CB", "S", "G", "CT", "W"}; //Cheese burger,Steak,GrilledCheese,Tenders,Wings respectively
 	private static String[] foods = {"Cheeseburger", "Steak", "Grilled Cheese", "Chicken Tenders", "Chicken Wings"};
 	private static int[] fPrice = {15,25,10,10,20};					//Prices for corresponding items (same index)
	private static int[] avgTime = {25,30,15,25,30};				//Average cook time for corresponding item (same index)
	
	//Member Variables
	private static LinkedList<Restaurant> restaurants = new LinkedList<Restaurant>();
	private static int chefs, maxCustomerSize, numRestaurants, customersLost, totalServiceTime, customersServed, profit, duration;
	private static double arrivalProb;
	public static PrintStream p = System.out;
	public static Scanner stdin = new Scanner(System.in);
	
	/**
	 * This method "rolls" for an event, given a percentage chance that it can occur
	 * @param chance
	 * The percentage amount that the event will occur
	 * @return
	 * True if the event occurred, False if not
	 */
	public static boolean roll(double chance) {
		boolean value = new Random().nextDouble() <= chance;
		return value;
	}
	/**
	 * This method returns a random integer between two values
	 * @param min
	 * Minimum value
	 * @param max
	 * Maximum value
	 * @return
	 * A random integer between the max and the min values
	 */
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int value = rand.nextInt(max - min);
		return value + min;
	}
	/**
	 * This method assigns a customer's order (food, price, time) to it using the menu consisting of arrays above
	 * @param c
	 * The customer
	 * @param num
	 * The menu item number
	 */
	public static void setOrder(Customer c, int num) {
		c.setFood(abvfoods[num]);
		c.setPrice(fPrice[num]);
		if (chefs >= 5) 
			c.setTimeServe(15 + avgTime[num] - 10);
		else if (chefs == 4) 
			c.setTimeServe(15 + avgTime[num] - 5);
		else if (chefs == 3)
			c.setTimeServe(15 + avgTime[num]);
		else if (chefs == 2)
			c.setTimeServe(15 + avgTime[num] + 5);
		else if (chefs == 1)
			c.setTimeServe(15 + avgTime[num] + 10);
	}
	/**
	 * This method simulates the operation of a group of restaurants, controlled by some parameters
	 * @param times
	 * The number of simulation times that will occur (5 min increments)
	 * @param numRests
	 * The number of restaurants in the group
	 * @param chance
	 * The chance that a customer walks into a restaurant
	 * @param maxCusts
	 * The maximum number of customers a restaurant can hold
	 * @param numChefs
	 * The number of chefs that a restaurant has
	 */
	public static void simulate(int times, int numRests, double chance, double maxCusts, int numChefs) {
		//reset values to be calculated
		int CustomerCounter = 0;
		customersLost = 0;
		totalServiceTime = 0;
		customersServed = 0;
		profit = 0;
		
		p.println("Begin Simulation...");
		//Initialize each restaurant
		for(int x = 0; x < numRests; x++) {
			Restaurant r = new Restaurant();
			restaurants.add(r);
		}
		//Timing Simulation
		for(int x = 0; x < times; x++) {
			p.println("Time: " + (x+1));
			/**
			 * Checks each time if a customer is finished eating in each restaurant
			 * If so, it adds their money and time spent to the calculated totals
			 * Skips the first time since everything would be empty prior to being filled
			 */
			if (x != 0) {
				for (int rest = 0; rest < numRests; rest++) {
					Restaurant rPtr = restaurants.get(rest);
					//If the restaurant is empty skip to the next one
					if (rPtr.isEmpty())
						continue;
					else {
						//checking each customer inside the restaurant
						for (int cust = 0; cust < rPtr.size(); cust++) {
							Customer cPtr = rPtr.get(cust);
							cPtr.decTime(5);
							//if customer is finished eating, add their values and dequeue them
							if (cPtr.getServeTime() == 0) {
								p.println("Customer #" + cPtr.getOrderNumber() + " has enjoyed their food! $" + cPtr.getPrice() + " Profit!");
								//adding values
								profit += cPtr.getPrice();
								customersServed++;
								totalServiceTime += cPtr.getArrival();
								//removing from queue
								rPtr.dequeue(cust);
							}
							//if not finished eating, continue checking
							else continue;
						}
					}
				}
			}
			//Rolling for Each Restaurant
			for(int rest = 0; rest < numRests; rest++) {
				for(int cust = 0; cust < 3; cust++) {
					boolean custEnters = roll(chance);
					//if customer enters
					if (custEnters) {
						p.println("Customer #" + (CustomerCounter + 1) + " has entered Restaurant " + (rest + 1));
						Customer c = new Customer(CustomerCounter + 1);
						//does not queue customer when restaurant is full || customer leaves
						if(restaurants.get(rest).size() >= maxCusts) {
							p.println("Customer #" + (CustomerCounter + 1) + " cannot be seated! They have left the restaurant");
							customersLost++;
							CustomerCounter++;
						}
						//customer gets seated
						else {
							//give order and add them to queue
							int order = randInt(0,5);
							p.println("Customer #" + (CustomerCounter + 1) + " has been seated with order " + foods[order]);
							setOrder(c, order);
							restaurants.get(rest).enqueue(c);
							CustomerCounter++;
						}
					}
					else continue;
				}
			}
			//prints the current information within each restaurant
			for (int rest = 0; rest < numRestaurants; rest++) {
				p.println("R" + (rest + 1) + ": " + restaurants.get(rest).toString());
			}
			p.println();
			}
		//Print results
		p.println();
		p.println("Simulation Ending...");
		p.println("----------------------------------------------------------------------");
		p.println("Total Customer Time: " + totalServiceTime + " mins");
		p.println("Total Customers Served: " + customersServed);
		p.printf("Average Customer Time Lapse: %.2f mins\n", (double) (totalServiceTime / customersServed));
		p.println("Total Profit: $" + profit);
		p.println("Customers that left: " + customersLost);
		p.println();
		}
	
	/**
	 * This method is simply the main method that drives this class
	 */
	public static void main(String[] args) {
		boolean active = true;
		while(active) {
			p.println("Starting Simulator...");
			p.print("Enter the number of restaurants: ");
			numRestaurants = stdin.nextInt();
			p.print("Enter the max number of customers a restaurant can serve: ");
			maxCustomerSize = stdin.nextInt();
			p.print("Enter the arrival probability of a customer: ");
			arrivalProb = stdin.nextDouble();
			p.print("Enter the number of chefs: ");
			chefs = stdin.nextInt();
			p.print("Enter the number of simulation units: ");
			duration = stdin.nextInt();
			
			simulate(duration, numRestaurants, arrivalProb, maxCustomerSize, chefs);
	
			p.print("Do you want to try another simulation? (y/n): ");
			String choice = stdin.next().toLowerCase();
			switch(choice) {
			case "y":
				continue;
			case "n":
				active = false;
				p.println("System Quitting..");
				break;
			default:
				p.println("Invalid choice, quitting by default");
				active = false;
				break;
			}
		}
		}
	}

