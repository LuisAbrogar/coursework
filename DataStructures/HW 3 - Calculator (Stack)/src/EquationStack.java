/**
 * This class represents a Stack that can be used by the Equation Class to perform operations
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.util.Stack;
public class EquationStack extends Stack<String>{
	
	public EquationStack() {
}

	public String push(String s) {
		String x =super.push(s);
		return x;
	}
	public String pop() {
		String x = super.pop();
		return x;
	}
	public boolean isEmpty() {
		return super.empty();
	}
	public String peek() {
		String x = super.peek();
		return x;
	}
	public int size() {
		return 0;
	}
	
}
