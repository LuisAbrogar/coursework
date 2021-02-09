/**
 * This class represents a Train Station, which is a collection of Train Tracks.
 * 
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.io.PrintStream;
import java.util.Scanner;


public class Station {
	private Track head, tail, cursor;
	private int TrackCount = 0;
	public static Scanner stdin = new Scanner(System.in);
	public static PrintStream p = System.out;
	public Station() {}
	/**
	 * This method simply returns the head of the Track
	 * @return
	 * Head of the Track
	 */ 
	public Track getHead() {
		return head;
	}
	/**
	 * This method adds a track to this station
	 * @param newTrack
	 * 	The track to be added
	 */
	public void addTrack (Track newTrack) {
		try {
		if (cursor == null) {
			cursor = newTrack;
			head = newTrack;
			TrackCount++;
			}
		else if (cursor.getNext() == null) {
			if(!this.exists(newTrack, "Add")) {
				cursor.setNext(newTrack);
				newTrack.setPrev(cursor);
				cursor = newTrack;
				TrackCount++;
			}
		}
		else {
			if(!this.exists(newTrack, "Add")) {
				cursor.getNext().setPrev(newTrack);
				newTrack.setNext(cursor.getNext());
				cursor.setNext(newTrack);
				newTrack.setPrev(cursor);
				cursor = newTrack;
				tail = newTrack;
				TrackCount++;
			}
		}
	}
		catch(AlreadyExistsException e) {
			return;
		}
	}
	/**
	 * This method removes the track pointed to by the cursor
	 * @return
	 * 	The cursor
	 */
	public Track removeSelectedTrack() {
		Track temp = new Track();
		temp = cursor;
		if (cursor == null)
			return null;
		else if ((cursor.getNext() == null) && (cursor.getPrev() == null)) {
			cursor = null;
			TrackCount--;
			return temp;
		}
		else if (cursor.getPrev() == null) {
			cursor.getNext().setPrev(null);
			cursor = cursor.getNext();
			TrackCount--;
			return temp;
		}
		else if (cursor.getPrev() == null)  {
			cursor = cursor.getNext();
			TrackCount--;
			return temp;
		}
		else if (cursor.getNext() == null) {
			cursor.getPrev().setNext(null);
			cursor = cursor.getPrev();
			TrackCount--;
			return temp;
		}
		else {
			cursor.getNext().setPrev(cursor.getPrev());
			cursor.getPrev().setNext(cursor.getNext());
			cursor = cursor.getNext();
			TrackCount--;
			return temp;
		}
	}
	/*
	 * This method prints the information for the selected track
	 */
	public void printSelectedTrack() {
		p.println("Track " + this.cursor.getNum() + " :");
		printHeader();
		p.print(this.cursor.toString());
		}
	
	/**
	 * This method simply prints the header for the track information
	 */
	public static void printHeader() {
	 System.out.print("Selected  Train Number     Train Destination         Arrival Time     Departure Time \n" +
						"-------------------------------------------------------------------------------------\n");
	}
	/**
	 * This Method checks if the time is in a valid 24 hr format
	 * @param arr
	 * The arrival time of the Train
	 * @param trans
	 * The Transfer time of the train
	 * @return
	 * True if it is valid, false if not
	 */
	public static boolean validTime(String arr, String trans) {
		String hr1 = arr.substring(0,2);
		String hr2 = trans.substring(0,2);
		String min1 = arr.substring(2,4);
		String min2 = trans.substring(2,4);
		
		int depHr, depMin, carry;
		
		if(arr.length() > 4) {
			p.println("Train failed to add: Invalid Arrival Time");
			return false;
		}
		if(trans.length() > 4) {
			p.println("Train failed to add: Invalid Transfer Time");
			return false;
		}
		if((hr1.compareTo("24") < 0) && (min1.compareTo("60") < 0)) {
			if((hr2.compareTo("24") < 0) && (min2.compareTo("60") < 0)) {
				depHr = Integer.parseInt(hr1) + Integer.parseInt(hr2);
				depMin = Integer.parseInt(min1) + Integer.parseInt(min2);
				depMin %= 60;
				carry = depMin / 60;
				depHr += carry;
				String time = String.valueOf(depHr) + String.valueOf(depMin);
				
				if (time.compareTo("2400") > 0) {
					p.println("Train failed to add: Invalid Departure Time");
					return false;
				}
				else
					return true;	
			}
			else {
				p.println("Train failed to add: Invalid Transfer Time");
				return false;
			}
		}
		else {
			p.println("Train failed to add: Invalid Arrival Time");
			return false;
		}
	}
	/**
	 * This method checks if a Track already exists
	 * 
	 * @param check
	 * The Track to be checked for
	 * @param method
	 * The Method being used (Helps create error message for the Exception)
	 * @return
	 * True if it exists, False if not
	 * @throws AlreadyExistsException
	 * Throws this if it already exists
	 */
	public boolean exists(Track check, String method) throws AlreadyExistsException {
		Track temp = new Track();
		temp = getHead();
		try {
		while(temp != null) {
			if (check.equals(temp)) 
				throw new AlreadyExistsException("Track", method, ( "Track " + check.getNum() +  " Already Exists"));
			else
				return false;		
		}
		}
		catch(AlreadyExistsException e) {
			return true;
		}
		return false;
	}
	/**
	 * This is simply the main method for this class
	 * @param args
	 */
	public static void main(String[] args) {
		Station sbu = new Station();
		boolean active = true;
		while(active) {
					p.print("|-----------------------------------------------------------------------------|\n" +
							"| Train Options                       | Track Options                         |\n" +
							"|    A. Add new Train                 |    TA. Add Track                      |\n" +
							"|    N. Select next Train             |    TR. Remove selected Track          |\n" +
							"|    V. Select previous Train         |    TS. Switch Track                   |\n" +
							"|    R. Remove selected Train         |   TPS. Print selected Track           |\n" +
							"|    P. Print selected Train          |   TPA. Print all Tracks               |\n" +
							"|-----------------------------------------------------------------------------|\n" +
							"| Station Options                                                             |\n" +
							"|   SI. Print Station Information                                             |\n" +
							"|    Q. Quit                                                                  |\n" +
							"|-----------------------------------------------------------------------------|\n\n"); 
		p.print("Choose an Operation: ");
		String selection = stdin.next().toLowerCase();
		switch(selection) {
		//Train Options	
			//add train
			case "a":
				p.print("Enter Train Number: ");
				int trainNum = stdin.nextInt();
				p.print("Enter Train Destination: ");
				String trainDest = stdin.nextLine();
				trainDest += stdin.nextLine();
				p.print("Enter Train Arrival Time: ");
				int trainArr = stdin.nextInt();
				p.print("Enter Train Transfer Time: ");
				int trainTrans = stdin.nextInt();
				
				String trans = String.format("%.04s", String.valueOf(trainTrans));
				String arr = String.format("%.04s", String.valueOf(trainArr));
				p.println(arr);
				p.println(trans);
				boolean valid = validTime(arr, trans);
				if (!valid)
					continue;
				if(sbu.cursor == null) {
					System.out.println("Train not added: There is no Track to add the Train to");
					continue;
				}
				else {
					Train input = new Train(trainNum, trainDest, trainArr, trainTrans);
					sbu.cursor.addTrain(input);
					continue;
				}
			//select next train	
			case "n":
				boolean next = sbu.cursor.selectNextTrain();
				if(next)
					p.println("Cursor has been moved to the Next Train");
				else
					p.println("Cursor has not moved");
				continue;
			//select previous train	
			case "v":
				boolean prev = sbu.cursor.selectPrevTrain();
				if(prev)
					p.println("Cursor has been moved to the Previous Train");
				else
					p.println("Cursor has not moved");
				continue;
			//remove selected train	
			case "r":
				Train removedTrain = new Train();
				removedTrain = sbu.cursor.removeSelectedTrain();
				if(removedTrain == null) {
					p.println("No train removed");
					continue;
				}
				else {
					p.println("Train Number " + removedTrain.getTrainNum() + " to " + removedTrain.getDestination() + " has been removedTrain from Track " + sbu.cursor.getNum());
					continue;
				}
			//print selected train
			case "p":
				sbu.cursor.printSelectedTrain();
				continue;
		//Track Options
			//add track
			case "ta":
				p.print("Enter Track Number: ");
				int trackNum = stdin.nextInt();
				Track add = new Track(trackNum);
				sbu.addTrack(add);
				continue;
			//remove selected track	
			case "tr":
				Track removedTrack = new Track();
				removedTrack = sbu.removeSelectedTrack();
				if (removedTrack == null) {
					p.println("No track removed");
					continue;
				}
				else {
					p.println("Closed Track " + removedTrack.getNum());
					continue;
				}
			//track select	
			case "ts":
				p.print("Enter Track Number: ");
				int findNum = stdin.nextInt();
				boolean notFound = true;
				Track finder = new Track();
				finder = sbu.head;
				if (finder == null) {
					p.println("No tracks in this station");
					continue;
				}
				while(finder != null) {
					if(finder.getNum() == findNum)
						notFound = false;
					else 
						finder = finder.getNext();
				}
				if (notFound) {
					p.println("Could not switch tracks: Track " + findNum + " does not exist");
					continue;
				}
				else {
					p.println("Switched to Track " + findNum);
					sbu.cursor = finder;
					continue;
				}
			//print selected track
			case "tps":
				sbu.printSelectedTrack();
				continue;
			//print ALL tracks
			case "tpa":
				Station temp = new Station();
				temp = sbu;
				if (temp.cursor == null)
					continue;
				while (temp.cursor != null) {
					temp.printSelectedTrack();
					temp.cursor.getNext();
				}
				continue;
		//Station Options
			case "si":
			//quit
			case "q":
				active = false;
				continue;
			default:
				p.println("Invalid Choice");
				continue;
		}
	}
		p.print("System Quit");
}
}
