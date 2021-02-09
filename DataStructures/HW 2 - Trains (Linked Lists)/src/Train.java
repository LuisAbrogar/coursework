/**
 * This class represents a Train approaching a Station. This object type relates to Tracks, Stations,
 * and other Trains in order to provide information on their relationships.
 * 
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
public class Train {
	private Train next, prev; //links
	private int trainNumber, arrivalTime, transferTime, departureTime;
	private String destination;
	
	public Train() {} //constructor
	/**
	 * Constructor for the Train Object
	 * @param tnum
	 * Train Number
	 * @param dest
	 * Destination
	 * @param atime
	 * Arrival Time
	 * @param ttime
	 * Transfer Time
	 */
	public Train(int tnum, String dest, int atime, int ttime) {
		trainNumber = tnum;
		destination = dest;
		arrivalTime = atime;
		transferTime = ttime;
	}
	
	/**The following are getter Methods
	 * 
	 * @return
	 * The Corresponding Member Variable
	 */
	
	public int getTrainNum() {
		return trainNumber;
	}
	public int getArrivaltime() {
		return arrivalTime;
	}
	public int getTransferTime() {
		return transferTime;
	}
	public int getDeparture() {
		departureTime = addTime(String.valueOf(arrivalTime), String.valueOf(transferTime));
		return departureTime;
	}
	public String getDestination() {
		return destination;
	}
	
	/**
	 * The following are setter Methods
	 */
	public void setTrainNum(int num) {
		trainNumber = num;
	}
	public void setArrival(int aTime) {
		arrivalTime = aTime;
	}
	public void setTransfer(int tTime) {
		transferTime = tTime;
	}
	public void setDestination(String dest) {
		destination = dest;
	}
	
	
	/**
	 * This method sets the next Train arriving after this train
	 * @param nextTrain
	 * 	The train that follows this current train
	 */
	public void setNext (Train nextTrain) {
		this.next = nextTrain;
	}
	
	/**
	 * This method sets the previous Train that will arrive before this train
	 * @param prevTrain
	 * 	The train that comes before this current Train
	 */
	public void setPrev (Train prevTrain) {
		this.prev = prevTrain;
	}
	
	/**
	 * This method returns the next Train arriving after this train
	 * @return
	 * 	The train that follows this current Train
	 */	
	public Train getNext() {
		return this.next;
	}
	
	/**
	 * This method returns the previous Train that will arrive before this Train
	 * @return
	 * 	The train that comes before this current Train
	 */
	public Train getPrev() {
		return this.prev;
	}
	
	/**
	 * This method checks if another Train object is equal to this Train
	 * @param o
	 * 	The object to be compared
	 * @return
	 * 	True if they are equal, false if they are not or if 'o' is not a Train object
	 */
	public boolean equals(Object o) {
		if (o instanceof Train) {
			Train o2 = (Train) o;
			return (this.trainNumber == o2.trainNumber);
		}
		else {
			System.out.println("Not instance of Train");
			return false;
		}
	}
	
	/**
	 * This method converts this Train's information into a String
	 * 
	 * @return
	 * 	This Train's information
	 */
	public String toString() {
		String info = "";
		info += "Train Number: " + this.trainNumber + "\n" +
				"Train Destination: " + this.destination + "\n" +
				"Arrival Time: " + String.format("%04d", this.arrivalTime) + "\n" +
				"Departure Time: " + String.format("%04d", this.departureTime ) + "\n";
		return info;
	}
	/**
	 * This method takes 2 times in 24 hr format and adds them
	 * @param time1
	 * Time 1 to be added
	 * @param time2
	 * Time 2 to be added
	 * @return
	 * The sum
	 */
	public static int addTime(String time1, String time2) {
		String hr1 = time1.substring(0,2);
		String hr2 = time2.substring(0,2);
		String min1 = time1.substring(2,4);
		String min2 = time2.substring(2,4);
		
		int sumHr, sumMin, carry;
		
		sumHr = Integer.parseInt(hr1) + Integer.parseInt(hr2);
		sumMin = Integer.parseInt(min1) + Integer.parseInt(min2);
		sumMin %= 60;
		carry = sumMin / 60;
		sumHr += carry;
		
		String time = String.valueOf(sumHr) + String.valueOf(sumMin);
		return Integer.valueOf(time);
	}
	
	
	
	

}
