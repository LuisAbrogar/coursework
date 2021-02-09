/**
 * This class represents a mathematical Equation/Expression
 * @author Luis Abrogar (ID: 111614684), CSE 214 - R09 Fall '20
 */
import java.lang.Math;

public class Equation {
	private String equation, postfix, prefix;
	private String binary, hex;
	private double answer;
	private boolean balanced, openPfirst = false;
	
	
	public Equation() {}
	public Equation(String e) {
		equation = e;
		balanced = this.isBalanced();
		prefix = this.toPrefix();
		postfix = this.toPostfix();
	}
	
	public String getEquation() {
		return equation;
	}
	/*
	 * Determines if the equation has properly matched parentheses, or is balanced
	 * @returns
	 * true if it is balanced, false if not
	 */
	public boolean isBalanced() {
		EquationStack holder = new EquationStack();
		for(int x = 0; x < equation.length(); x++) {
			if (equation.charAt(x) == '(') {
				openPfirst = true;
				break;
			}
			else if (equation.charAt(x) == ')') {
				openPfirst = false;
				break;
			}
			else
				continue;
		}
		if (openPfirst) {
			for(int x = 0; x < equation.length(); x++) {
				if (equation.charAt(x) == '(') 
					holder.push(Character.toString(equation.charAt(x)));
				else if(equation.charAt(x) == ')')
					holder.pop();
			}
		}
		else {
			for(int x = 0; x < equation.length(); x++) {
				if (equation.charAt(x) == ')') 
					holder.push(Character.toString(equation.charAt(x)));
				else if(equation.charAt(x) == '(')
					holder.pop();
			}
		}
		if (holder.isEmpty()) 
			return true;
		else
			return false;
		}
	/**
	 * Checks if a character is an operator
	 * @param c
	 * The character to be checked
	 * @return
	 * True if it is an operator, False if not
	 */
	public static boolean isOp(char c) {
		return (c == '+' || c == '-' ||
				c == '*' || c == '/' ||
				c == '%' || c == '^' ||
				c == '(' || c == ')');
	}
	/**
	 * Gives an operation its priority value
	 * @param op
	 * The operation 
	 * @return
	 * Priority level (from 3 to 0) based on PEMDAS or the Order of Operations
	 */
	public static int priority(char op) {
		if (op == '+' || op == '-')
			return 1;
		else if (op == '*' || op == '/' || op == '%' )
			return 2;
		else if (op == '^')
			return 3;
		else
			return 0;
	}
	/**
	 * Converts a string in Infix notation to Prefix notation
	 * @return
	 * The string in prefix notation
	 */
	public String toPrefix() {
 		//If not balanced, return 0
		if (!this.isBalanced())
			return "0";
		else {
			EquationStack operands = new EquationStack();
			EquationStack operations = new EquationStack();
			for (int x = 0; x < equation.length() ; x++) {
			//Encounters an open parenthesis, push onto operation stack
				if (equation.charAt(x) == '(') {
					operations.push(Character.toString(equation.charAt(x)));
				}
			//Encounter a closed parenthesis
				else if (equation.charAt(x) == ')') {
					while(!operations.isEmpty() && operations.peek().charAt(0) != '(') {
						String op1 = operands.peek();
						operands.pop();
						if(operands.peek() == null)
							break;
						String op2 = operands.peek();
						if(op2 != null)
							operands.pop();
						String op = operations.peek();
						operations.pop();
						String group = op + op2 + op1;
						operands.push(group);
					}
					operations.pop();
					continue;
				}
			//Encounter an operand
				else if(!isOp(equation.charAt(x))) {
					String num = Character.toString(equation.charAt(x));
					//Checks if there are multiple digits
					if (x < (equation.length() - 1)) {
						while(!isOp(equation.charAt(x + 1))) {
							num += Character.toString(equation.charAt(x+1));
							x++;
							if (!(x < equation.length() - 1))
								break;
						}
					}
					operands.push(num + "");
					continue;
					}
			//Encounter an operation
				else {
					while(!operations.isEmpty() && priority(equation.charAt(x)) <= priority(operations.peek().charAt(0))) {
						String op1 = operands.peek();
						operands.pop();
						String op2 = operands.peek();
						operands.pop();
						String op = operations.peek();
						operations.pop();
						String group = op + op2 + op1; 
						operands.push(group);
					}
					operations.push(Character.toString(equation.charAt(x)));
				}
			}
			//If there is more operations not dealt with
			while(!operations.isEmpty()) {
				String op1 = operands.peek();
				operands.pop();
				String op2 = operands.peek();
				operands.pop();
				String op = operations.peek();
				operations.pop();
				String group = op + op2 + op1; 
				operands.push(group);
			}
			return operands.pop();
		}
	}
	
	/**
	 * Converts an equation in Infix Notation to Postfix Notation
	 * @return
	 * The string in Postfix Notation
	 */
	public String toPostfix() {
		String result = "";
		EquationStack holder = new EquationStack();
		if(!this.isBalanced())
			return "0";
		else {
			for (int x = 0; x < equation.length(); x++) {
				System.out.println(x + " " + holder);
				//Encounter operand
				System.out.println(equation.charAt(x));
				if(!isOp(equation.charAt(x))) {
					result += equation.charAt(x);
					//Checks if there are multiple digits
					if (x < (equation.length() - 1)) {
						while(!isOp(equation.charAt(x + 1))) {
							result += equation.charAt(x+1);
							x++;
							System.out.println(result);
								if (!(x < equation.length() - 1)) {
									break;
								}	
						}
					}	
				}
				//Encounter open parenthesis
				else if(equation.charAt(x) == '(')
					holder.push(Character.toString(equation.charAt(x)));
				//Encounter close parenthesis
				else if(equation.charAt(x) == ')') {
					while(!holder.isEmpty() && holder.peek().charAt(0) != '(') {
						result += holder.pop();
					}
					if (holder.peek().charAt(0) == '(')
						holder.pop();
				}
				//Encounter operation
				else {
					while(!holder.isEmpty() && priority(equation.charAt(x)) <= priority(holder.peek().charAt(0))) {
						result += holder.pop();
					}
					holder.push(Character.toString(equation.charAt(x)));
					System.out.println(holder);
				}
			}
			//If stack is not empty by end of previous iterations
			while(!holder.isEmpty()) {
				result += holder.pop();
			}
			return result;
		}
	}
	/**
	 * Converts a number in decimal to its binary representation
	 * @param number
	 * The number to be converted
	 * @return
	 * The binary representation of the number, as a String
	 */
	public String decToBin(int number) {
		String bin = "";
		int denom;
		int temp = number;
		if (number == 0) 
			return bin += "0";
		for(int x = 12; x >= 0; x--) {
			denom = (int) Math.pow(2, x);
			if (denom > temp) 
				continue;
			else {
				if ((int)(number / denom) > 0) {
					bin += "1";
					number -= denom;
					continue;
				}
				else {
					bin += "0";
					continue;
				}
			}
			
		}
		return bin;
	}
	/**
	 * Converts a number in decimal to its hexadecimal representation
	 * @param number
	 * The number to be converted
	 * @return
	 * The hexadecimal representation of the number, as a String
	 */
	public String decToHex (int number) {
		String hexa = "";
		int denom, cond;
		int temp = number;
		
		if (number == 0)
			return hexa += "0";
		for (int x = 12; x >= 0; x--) {
			denom = (int) Math.pow(16, x);
			if (denom > temp)
				continue;
			else {
				cond = (int)(number / denom);
				if (cond > 0) {
					if (cond == 10)
						hexa += "A";
					else if (cond == 11)
						hexa += "B";
					else if (cond == 12)
						hexa += "C";
					else if (cond == 13)
						hexa += "D";
					else if (cond == 14)
						hexa += "E";
					else if (cond == 15)
						hexa += "F";
					else 
						hexa += Integer.toString(cond);
					number -= cond * denom;
					continue;
				}
			}
		}
		return hexa;
	}

	//public String toString() {}
}

