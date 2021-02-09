/**
 * This Class acts as the Driver for the GeneralLedger Class, allowing a User to interact with a GeneralLedger Object.
 * The user inputs a letter, which corresponds to a menu item that carries out a particular function defined by the GeneralLedger class
 * 
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09  , Spring '20
 */
import java.util.Scanner;
public class GeneralLedgerManager {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		GeneralLedger list = new GeneralLedger();
		GeneralLedger backup = new GeneralLedger();
		boolean quit = false;
		
		while(quit == false) {
			System.out.print("Main Menu:\n" +
							 "(A):	Add Transaction\n" +
							 "(G):	Get Transaction\n" +
							 "(R):	Remove Transaction\n" +
							 "(P):	Print Transactions in General Ledger\n" +
							 "(F):	Filter by Transaction Date\n" +
							 "(L):	Look for Transaction Date\n" +
							 "(S):	Size\n" +
							 "(B):	Backup\n" +
							 "(PB):	Print Transaction in Backup\n" +
							 "(RB):	Revert to Backup\n" +
							 "(CB):	Compare Backup with Current\n" +
							 "(PF):	Print Financial Information\n" +
							 "(Q):	Quit\n\n" +
							 "Enter a Selection: ");
			String select = stdin.next().toLowerCase();
			switch(select) {
				case "a" : //add
					try {
					int before = list.getCounter();
					System.out.print("Enter Date: ");
					String date = stdin.next();
					System.out.print("Enter Amount: $");
					double amt = stdin.nextDouble();
					System.out.print("Enter Description: ");
					String desc = stdin.next();
					Transaction t = new Transaction(date,desc,amt);
					list.addTransaction(t);
					if (before != list.getCounter())
						System.out.println("Transaction Added Successfully\n\n");
					break;
					}	
					catch (InvalidTransactionException e) {
						
					}
					catch (TransactionAlreadyExistsException e) {
						
					}
					catch (FullGeneralLedgerException e) {
						
					}
				case "g": //get
					System.out.print("Enter Position: ");
					int pos = stdin.nextInt();
					Transaction g = list.getTransaction(pos);
					break;
					
				case "r": //remove
					int before = list.getCounter();
					System.out.print("Enter Position: ");
					list.removeTransaction(stdin.nextInt());
					if (before != list.getCounter())
						System.out.println("Transaction Removed Successfully\n\n");
					break;
					
				case "p": //print ledger
					list.printAllTransactions();
					System.out.print("\n\n");
					break;
					
				case "f": //filter
					System.out.print("Enter Date: ");
					String datef = stdin.next();
					GeneralLedger.filter(list, datef);
					break;
					
				case "l": //look
					System.out.print("Enter Date: ");
					String datel = stdin.next();
					System.out.print("Enter Amount: ");
					double amtl = stdin.nextDouble();
					System.out.print("Enter Description: ");
					String descl = stdin.next();
					Transaction lookup = new Transaction(datel, descl, amtl);
					if (list.exists(lookup))
						list.getTransaction(list.getCurrent());
					else
						System.out.print("Entry does not exist");
					break;
					
				case "s": //size
					System.out.println("There are currently " + list.getCounter() + " transactions in the ledger");
					break;
					
				case "b": //backup copy
					backup = (GeneralLedger) list.clone();
					if (backup.isEmpty())
						System.out.println("backup fail");
					else
						System.out.println("Backup successfully created");
					break;
					
				case "pb": //print backup
						backup.printAllTransactions();
					break;
					
				case "rb": //revert to backup
					if (backup.isEmpty())
						System.out.println("Backup does not exist");
					else {
						list = (GeneralLedger) backup.clone();
						System.out.println("Successfully reverted to backup");
					}
					break;
				case "cb": //compare with backup
					if (list.isEqualTo(backup)) 
						System.out.println("Original is equal to Backup");
					else
						System.out.println("Original is not equal to Backup");
					break;
				case "pf": //print financial info
					System.out.println("Debit Amount:	$" + list.getTotalDebit() + "\n" +
									   "Credit Amount:	$" + (list.getTotalCredit() *-1) + "\n" +
									   "Net Worth:	$" + (list.getTotalDebit() + list.getTotalCredit()) +"\n");
					
					break;
				case "q": //quit
					quit = true;
					System.out.println("System Quitting...");
					break;
				case "bp":
					backup.printAllTransactions();
					break;
				default:
					System.out.println("Invalid Menu Choice");
					break;
				}
	}
		stdin.close();
		
}
}