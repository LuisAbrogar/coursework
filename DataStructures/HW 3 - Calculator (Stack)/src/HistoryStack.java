/**
 * This class represents a stack of Equation Objects that act as the user's history
 *  @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 *
 */
import java.util.Stack;
public class HistoryStack extends Stack<String> {
	public HistoryStack() {}
	
	public String push(Equation newEquation) {
		String x =super.push(newEquation.getEquation());
		return x;
	}
	public String pop() {
		String x = super.pop();
		return x;
	}
	public boolean isEmpty() {
		return super.empty();
	}
	public int size() {
		return 0;
	}
	public void undo() {}
	public void redo() {}
	//public Equation getEquation(int position) {}
	//public String toString() {}
}
