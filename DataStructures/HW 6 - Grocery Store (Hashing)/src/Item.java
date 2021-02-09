/**
 * This class represents an instance of an item in a Grocery Store
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
public class Item {
	//Member variables
	private String itemCode;
	private String name;
	private int qtyInStore;
	private int averageSalesPerDay;
	private int onOrder;
	private int arrivalday;
	private double price;
	//Getter Methods
	public String getCode() {
		return itemCode;
	}
	public String getName() {
		return name;
	}
	//Setter Methods
	public void setQty(int qty) {
		qtyInStore += qty;
	}
	public void setOrder(int order) {
		onOrder = order;
	}
	
	
	//Constructors
	public Item() {}
	public Item(String code, String Name, int qty, int avg, double Price) {
		itemCode = code;
		name = Name;
		qtyInStore = qty;
		averageSalesPerDay = avg;
		price = Price;
	}
	
	/**
	 * This method simply converts the Item's information to a string used for keeping inventory
	 */
	public String toString() {
		String holder = "";
		if (name.length() <= 8) {
			holder += itemCode + "   " + name + "\t\t" + qtyInStore + "\t" + averageSalesPerDay + "\t" + 
						price + "\t\t" + onOrder + "\t\t" + arrivalday;
		}
		else {
			holder += itemCode + "   " + name + "\t" + qtyInStore + "\t" + averageSalesPerDay + "\t" + 
					price + "\t\t" + onOrder + "\t\t" + arrivalday;
		}
		return holder;
	}
	/**
	 * This method simply returns a short description of the item
	 * @return
	 * The description (code and name)
	 */
	public String description() {
		return (itemCode + ": " + name + " ");
	}
}
