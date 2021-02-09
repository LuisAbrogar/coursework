/**
 * This class represents a Scene that acts as a Node for a Tree of Scenes
 *  @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.io.PrintStream;
public class SceneNode {
	//Helper Variables
	PrintStream p = System.out;
	//Member Variables
	private String title, sceneDescription;
	private int sceneID;
	private SceneNode parent;
	private SceneNode left, middle, right;
	//Static Variables
	private static int numScenes = 1;
	//Constructors
	public SceneNode() {}
	public SceneNode(String name, String scene) {
		title = name;
		sceneDescription = scene;
		sceneID = numScenes;
		numScenes++;
	}
	
	/**
	 * SETTER METHODS
	 */
	public void setParent(SceneNode p) {
		parent = p;
	}
	public void setLeft(SceneNode l) {
		left = l;
	}
	public void setMid(SceneNode m) {
		middle = m;
	}
	public void setRight(SceneNode r) {
		right = r;
	}
	/**
	 * GETTER METHODS
	 */
	public SceneNode getParent() {
		return parent;
	}
	public SceneNode getLeft() {
		return left;
	}
	public SceneNode getMid() {
		return middle;
	}
	public SceneNode getRight() {
		return right;
	}
	public int getSceneID() {
		return sceneID;
	}
	public String getTitle() {
		return title;
	}
	//Methods
	/**
	 * This method checks if a node is full
	 * @return
	 * True if full, false if not
	 */
	public boolean isFull() {
		if (left != null && middle != null && right != null) return true;
		else return false;
	}
	/**
	 * This method adds a scene to the first available slot of a SceneNode, from left to right
	 * @param scene
	 * The scene to be added
	 * @throws FullSceneException
	 * If the SceneNode is already full
	 */
	public void addSceneNode(SceneNode scene) throws FullSceneException {
		if (this.isFull())
			throw new FullSceneException();
		else if (left == null) {
			left = scene;
			left.setParent(this);
			return;
		}
		else if (middle == null) {
			middle = scene;
			middle.setParent(this);
			return;
		}
		else {
			right = scene;
			middle.setParent(this);
			return;
		}
	}
	/**
	 * This method checks if this scene is an ending, or it does not have any children
	 * @return
	 * true if it is an ending, false if not
	 */
	public boolean isEnding() {
		if (left == null && middle == null && right == null)
			return true;
		else return false;
	}
	/**
	 * This method prints the scene information as it would be seen during gameplay
	 */
	public void displayScene() {
		p.println(title + "\n" + sceneDescription + "\n");
		if (left != null)
			p.println("A) " + left.title);
		if (middle != null)
			p.println("B) " + middle.title);
		if (right != null)
			p.println("C) " + right.title);
	}
	/**
	 * This method prints the scene information as it would be seen during creation mode
	 */
	public void displayFullScene() {
		p.println("Scene ID: #" + sceneID);
		p.println("Title: " + title);
		p.println("Scene: " + sceneDescription);
		p.print("Leads to: ");
		if (this.isEnding()) {
			p.println("None");
			return;
		}
		else if (left != null)
			p.print("'" + left.title + "' (#" + left.sceneID + ")");
		else if (middle != null)
			p.print(", '" + middle.title + "' (#" + middle.sceneID + ")");
		else if (right != null)
			p.print(", '" + right.title + "' (#" + right.sceneID + ")");
		p.println();
	}
	/**
	 * This method converts this SceneNode's information to a printable string
	 */
	public String toString() {
		String desc = "";
		desc += title + " (#" + sceneID + ")";
		return desc;
	}
}
