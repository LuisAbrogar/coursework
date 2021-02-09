/**
 * This class represents a Train Track, which is a list of Train objects that
 * contain references to the Train nodes along with a cursor to show the currently selected Train.
 * 
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
import java.util.Scanner;
public class Track {
	private Train head, tail, cursor;
	private Track next, prev;
	private double utilizationRate;
	private int trackNumber;
	private double utilRate;
	
	
	public Track() {}
	/**
	 * This is a constructor for the Track class
	 * @param num
	 * The Track Number
	 */
	public Track(int num) {
		trackNumber = num;
	}
	/**
	 * The following are getter methods
	 * @return
	 * The corresponding member variable
	 */
	public int getNum() {
		return trackNumber;
	}
	public Train getCursor() {
		return cursor;
	}
	public Train getHead() {
		return head;
	}
	public Track getNext() {
		return next;
	}
	public Track getPrev() {
		return prev;
	}
	/**
	 * Sets the Track next to this current Track
	 * @param nextTrack
	 * The next Track
	 */
	public void setNext(Track nextTrack) {
		next = nextTrack;
	}
	/**
	 * Sets the Previous Track from this current Track
	 * @param prevTrack
	 * The previous Track
	 */
	public void setPrev(Track prevTrack) {
		prev = prevTrack;
	}
	
	/**
	 * This method adds a Train to this Track
	 * 
	 * @param newTrain
	 * 	The Train to be added
	 */
	public void addTrain(Train newTrain) {
		if (cursor == null) {
			cursor = newTrain;
			head = newTrain;}
		else if (cursor.getNext() == null) {
			cursor.setNext(newTrain);
			newTrain.setPrev(cursor);
			cursor = newTrain;
			TrainSort();
		}
		else {
			cursor.getNext().setPrev(newTrain);
			newTrain.setNext(cursor.getNext());
			cursor.setNext(newTrain);
			newTrain.setPrev(cursor);
			cursor = newTrain;
			TrainSort();
			tail = newTrain;
		}
	}
	/**
	 * This method prints the information of the currently selected Train
	 */
	public void printSelectedTrain() {
		System.out.println(cursor.toString());
	}
	/**
	 * This method removes the selected Train from the track
	 * @return
	 * The reference to the removed train, null if there is no selected Train
	 */ 
	public Train removeSelectedTrain() {
		Train holder = new Train();
		holder = cursor;
		if (cursor == null)
			return null;
		else if ((cursor.getNext() == null) && (cursor.getPrev() == null)) {
			cursor = null;
			return holder;
		}
		else if (cursor.getPrev() == null) {
			cursor.getNext().setPrev(null);
			cursor = cursor.getNext();
			return holder;
		}
		else if (cursor.getPrev() == null) 
			cursor = cursor.getNext();
		else if (cursor.getNext() == null) {
			cursor.getPrev().setNext(null);
			cursor = cursor.getPrev();
			return holder;
		}
		else {
			cursor.getNext().setPrev(cursor.getPrev());
			cursor.getPrev().setNext(cursor.getNext());
			cursor = cursor.getNext();
			return holder;
		}
		return holder;
	}
	/**
	 * This method moves the reference from the current selected Train to the next one
	 * 
	 * @return
	 * 	True if a next Train exists, false if not
	 */
	public boolean selectNextTrain() {
		if (cursor.getNext() == null) 
			return false;
		else {
			cursor = cursor.getNext();
			return true;
		}
	}
	/**
	 * This method moves the reference from the current selected Train to the previous one
	 * 
	 * @return
	 * 	True if a previous Train exists, false if not
	 */
	public boolean selectPrevTrain() {
		if (cursor.getPrev() == null)
			return false;
		else {
			cursor = cursor.getPrev();
			return true;
		}
	}
	/**
	 * This method turns all of the Train information on this Track into a String
	 * 
	 * @returns
	 * The Track information
	 */
	public String toString() {
		Train temp = new Train();
		String trackInfo = "";
		temp = head;
		
		while(temp != null) {
			if(temp == this.getCursor())
				trackInfo += "     *      " + temp.getTrainNum() + "	         " + temp.getDestination() + "		" + String.format("%04d", temp.getArrivaltime()) + "		" + String.format("%04d", temp.getDeparture()) + "\n";
			else
				trackInfo += "            " + temp.getTrainNum() + "	         " + temp.getDestination() + "		" + String.format("%04d", temp.getArrivaltime()) + "		" + String.format("%04d", temp.getDeparture()) + "\n";
			temp = temp.getNext();
		}
		
		return trackInfo; 
	}
	/**
	 * This method checks if a Train already exists on this track
	 * @param check
	 * The train to be checked
	 * @param method
	 * The method this is used in
	 * @throws AlreadyExistsException
	 * Thrown if it already exists
	 */
	public void exists(Train check, String method) throws AlreadyExistsException {
		Train temp = new Train();
		temp = getHead();
		while(temp != null) {
			if (check.equals(temp)) {
				throw new AlreadyExistsException("Train", method, ( "Train " + check.getTrainNum() + " to " + check.getDestination() + "Already Exists"));
			}		
		}
	}
	/**
	 * Sorting algorithm for the Trains
	 */
	public void TrainSort() {
		Train current = null, index = null;
		int tempNum, tempArr, tempTran;
		String tempDest;
		
		for(current = getHead(); current.getNext() != null; current = current.getNext()) {
			for(index = current.getNext(); index != null; index = index.getNext()) {
				if (current.getArrivaltime() > index.getArrivaltime()) {
					//Hold current value
					tempNum = current.getTrainNum();
					tempArr = current.getArrivaltime();
					tempTran = current.getTransferTime();
					tempDest = current.getDestination();
					//Swap values with next Train
					current.setTrainNum(index.getTrainNum());
					current.setArrival(index.getArrivaltime());
					current.setTransfer(index.getTransferTime());
					current.setDestination(index.getDestination());
					//Load next Train with held values
					index.setTrainNum(tempNum);
					index.setArrival(tempArr);
					index.setTransfer(tempTran);
					index.setDestination(tempDest);
				}
			}
		}
		
	}
	
	
	
}
