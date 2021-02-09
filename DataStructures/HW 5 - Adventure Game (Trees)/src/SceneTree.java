/**
 * This class represents a Tree of Scene Nodes, and contains methods that traverse it.
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
public class SceneTree {
	//Member Variables
	private SceneNode root, cursor;
	//Helper Variables
	public String path = "";
	/**
	 * GETTER METHODS
	 */
	public SceneNode getCursor() {
		return cursor;
	}
	public SceneNode getRoot() {
		return root;
	}
	//Constructors
	public SceneTree() {}
	//Methods
	/**
	 * Moves the cursor backwards from its current node to its parent node
	 * @throws NoSuchNodeException
	 * If there is no parent node
	 */
	public void moveCursorBackwards() throws NoSuchNodeException {
		if (cursor == root) throw new NoSuchNodeException();
		else {
			cursor = cursor.getParent();
		}
	}
	/**
	 * Moves the cursor forward, depending on the given choice
	 * @param option
	 * A for left, B for middle, C for right
	 * @throws NoSuchNodeException
	 * If there is no Node in the left/middle/right
	 */
	public void moveCursorForward(String option) throws NoSuchNodeException {
		option = option.toLowerCase();
		SceneNode holder = new SceneNode();
		switch(option) {
			case "a":
				holder = cursor.getLeft();
				if (holder == null) throw new NoSuchNodeException();
				cursor = holder;
				path += cursor.getTitle() + " , ";
				break;
			case "b":
				holder = cursor.getMid();
				if (holder == null) throw new NoSuchNodeException();
				cursor = holder;
				path += cursor.getTitle() + " , ";
				break;
			case "c":
				holder = cursor.getRight();
				if (holder == null) throw new NoSuchNodeException();
				cursor = holder;
				path += cursor.getTitle() + " , ";
				break;
		}	
	}
	/**
	 * This method adds a new node to the tree
	 * @param title
	 * Title of the scene
	 * @param sceneDescription
	 * Scene Description
	 * @throws FullSceneException
	 * If the scene is already full
	 */
	public void addNewNode(String title, String sceneDescription) throws FullSceneException {
		SceneNode newNode = new SceneNode(title,sceneDescription);
		if(root == null) {
			root = newNode;
			cursor = root;
			path += cursor.getTitle() + " , ";
		}
		else {
			cursor.addSceneNode(newNode);
		}
	}
	/**
	 * This method removes a scene from the tree, given a choice
	 * @param option
	 * A for left, B for middle, C for right
	 * @throws NoSuchNodeException
	 * If there is no node on the left/middle/right
	 */
	public void removeScene(String option) throws NoSuchNodeException {
		option = option.toLowerCase();
		SceneNode holder = new SceneNode();
		switch(option) {
		case "a":
			holder = cursor.getLeft();
			if (holder == null) throw new NoSuchNodeException();
			if (cursor.getMid() != null) {
				if (cursor.getRight() != null) {
					cursor.setLeft(cursor.getMid());
					cursor.setMid(cursor.getRight());
					cursor.setRight(null);
				}
				else {
					cursor.setLeft(cursor.getMid());
					cursor.setMid(null);
				}
			}
			break;
		case "b":
			holder = cursor.getMid();
			if (holder == null) throw new NoSuchNodeException();
			if (cursor.getRight() != null) {
				cursor.setMid(cursor.getRight());
				cursor.setRight(null);
			}
			break;
		case "c":
			holder = cursor.getRight();
			if (holder == null) throw new NoSuchNodeException();
			cursor.setRight(null);
			break;
		}
	}
	public SceneNode findID(int id, SceneNode node) {
		if (node == null) return null;
		if (node.getSceneID() == id)
			return node;
		
		findID(id, node.getLeft());
		findID(id, node.getMid());
		findID(id, node.getRight());
		return null;
	}
	public void moveScene(int sceneIDToMoveTo) throws NoSuchNodeException, FullSceneException {
		
	}
	/**
	 * Gives path to cursor from root
	 * @return
	 * Path as a string
	 */
	public String getPathFromRoot() {
		return path;
	}
	/**
	 * Helper method for the print method
	 * @param node
	 * Current node pointer
	 */
	public static void traverse(SceneNode node) {
		if (node == null) return;
		else {
			if (node.getLeft() != null) {
				System.out.println("\t A)" + node.getLeft().toString());
			}
			if (node.getMid() != null) {
				System.out.println("\t B)" + node.getMid().toString());
			}
			if (node.getRight() != null) {
				System.out.println("\t C)" + node.getRight().toString());
			}
			
			traverse(node.getLeft());
			traverse(node.getMid());
			traverse(node.getRight());
		}
	}
	/**
	 * This method prints the current tree
	 */
	public void printer() {
		String desc = "";
		if (cursor == root)
			desc += root.toString() + " * \n";
		else
			desc += root.toString() + "\n";
		System.out.print(desc);
		traverse(root);
		}
		
}
