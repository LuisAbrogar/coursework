/**
 * This class represents an instance of a Grocery Store as a hash table of Items
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import org.json.simple.JSONObject;
    import org.json.simple.JSONArray;
    import org.json.simple.parser.*;
    import org.json.simple.parser.ParseException;
import java.util.Hashtable;
import java.util.Set;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.*;
public class HashedGrocery implements Serializable {
	public static final long serialVersionUID = 1L;
	
	//Variables for parsing
	//Member Variables
	private int businessDay = 1;
	private Hashtable<String,Item> hashTable = new Hashtable<String, Item>();
	
	public int getDay() {
		return businessDay;
	}
	//Constructor
	public HashedGrocery() {}
	//Functions
	/**
	 * This method adds an Item to the hash table
	 * @param item
	 * The item to be added
	 */
	public void addItem(Item item) {
		hashTable.put(item.getCode(), item);
		System.out.println(item.description() + "added to inventory");
	}
	/**
	 * This method changes the quantity value of an item
	 * @param item
	 * The item to be adjusted
	 * @param adjustByQty
	 * The updated quantity
	 */
	public void updateItem(Item item, int adjustByQty) {
		item.setQty(adjustByQty);		
	}
	public void addItemCatalog(String filename) throws IOException, ParseException{
		try {
		FileInputStream input = new FileInputStream(filename);
		InputStreamReader reader = new InputStreamReader(input);
		JSONParser parser = new JSONParser();
		JSONArray objs = (JSONArray) parser.parse(reader);
		for (int x = 0; x < objs.size(); x++) {
			JSONObject obj = (JSONObject) objs.get(x);
			String code = (String) obj.get("itemCode");
			String name = (String) obj.get("itemName");
			int avg =  Integer.parseInt((String) obj.get("avgSales"));
			int qty = Integer.parseInt((String) obj.get("qtyInStore"));
			double price = Double.parseDouble((String) obj.get("price"));
			Item item = new Item(code,name,qty,avg,price);
			this.addItem(item);
		}
		
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		catch (ParseException e) {
			System.out.println("Parse Exception raised");
			System.out.println(e.getMessage());
		}
		
	}
	/**
	 * This method processes sales given a file that describes the sales
	 * @param filename
	 * The file describing the sales
	 * @throws IOException
	 * @throws ParseException
	 */
	public void processSales(String filename) throws IOException, ParseException {
		try {
		FileInputStream input = new FileInputStream(filename);
		InputStreamReader reader = new InputStreamReader(input);
		JSONParser parser = new JSONParser();
		JSONArray objs = (JSONArray) parser.parse(reader);
		for (int x = 0; x < objs.size(); x++) {
			JSONObject obj = (JSONObject) objs.get(x);
			String code = (String) obj.get("itemCode");
			int sold = Integer.parseInt((String) obj.get("qtySold"));
			if (hashTable.get(code) == null) {
				System.out.print(code + ": Cannot buy as it is not in the grocery store");
				continue;
			}
			this.updateItem(hashTable.get(code), (sold * -1));
			System.out.println(sold + " units of " + hashTable.get(code).getName() + " are sold.");
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		catch (ParseException e) {
			System.out.println("Parse Exception raised");
			System.out.println(e.getMessage());
		}
	}
	/**
	 * This method advances the business day
	 */
	public void nextBusinessDay() {
		businessDay++;
	}
	/**
	 * This method converts the grocery store's inventory to a string
	 */
	public String toString() {
		Set<String>items = hashTable.keySet();
		String holder = "";
		for(String key : items) {
			holder += hashTable.get(key).toString() + "\n";
		}
		return holder;
	}
	/**
	 * This method saves the grocery store information
	 */
	public void save() {
		try {
		FileOutputStream file = new FileOutputStream("grocery.obj");
		ObjectOutputStream fout = new ObjectOutputStream(file);
		
		fout.writeObject(this);
		fout.close();
		}
		catch(Exception e) {
			
		}
	}
	
}
