/**
 * This class is an instance of the game "Connect 4"
 * @author Luis Abrogar (ID: 111614684) - CSE 114 Spring '19
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class connect4 {
	//Game Elements ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static int[][] p1 = new int[6][7];						//Array recording P1's moves
	public static int[][] p2 = new int[6][7];						//Array recording P2's moves
	public static char[][] board = new char [6][7];					//Game Board
	public static boolean win1 = false;								//True when P1 wins
	public static boolean win2 = false;								//True when P2 wins
	public static 	boolean fullBoard = false;						//True when the Game Board is full
	
	public static Scanner stdin = new Scanner(System.in);			//Scanner for user input

	//Game Body -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * This method acts as the driver for the game
	 */
	public static void main(String[] args) {												
		System.out.println(".............................");
		System.out.println("---------CONNECT 4-----------");
		System.out.println(".............................\n");


		while(win1 == false && win2 == false && fullBoard == false) {
		//Player 1
				play(1);
				if(win1 == true) break;
				if (fullBoard) break;
		//Player 2
				play(2);
				if(win2 == true) break;
				if (fullBoard) break;
		}		
	
		if (win1 == true)
			System.out.println("Player 1 wins!");
		else if(win2 == true)
			System.out.println("Player 2 wins!");
		else if(fullBoard)
			System.out.println("DRAW!");
		
		stdin.close();
		
	}


// Game Methods -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void play(int playerNum) {																//Asks for input from the player and runs the game
		//int tester = (int) (Math.random() * 6); 		//Remove comments to generate random moves and test functionality
		switch(playerNum) {
		case 1:
			try {
			fullBoard = checkBoard(board);
			System.out.print("Player 1: Drop a red disk at column (0 - 6): ");
			//move(tester, board, 'R');
			move(stdin.nextInt(), board, 'R');
			win1 = check(p1); 
			break;
			}
			catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("OUT OF BOUNDS!");	
				play(1);
				}
			catch (InputMismatchException ex) {
				System.out.println("INVALID INPUT!");
				stdin.next();
				play(1);
				}
		case 2:
			try {
			fullBoard = checkBoard(board);
			System.out.print("Player 2: Drop a yellow disk at column (0 - 6): ");
			//move(tester,board,'Y');
			move(stdin.nextInt(), board, 'Y');
			win2 = check(p2);
			break;
			}
			catch (ArrayIndexOutOfBoundsException ex) {
				System.out.println("OUT OF BOUNDS!");
				play(2);
				
				}
			catch (InputMismatchException ex) {
				System.out.println("INVALID INPUT!");
				stdin.next();
				play(2);
				}
		}
	}
	
	/**
	 * This method  displays the current state of the game board
	 * @param in
	 * The game board, represented as a character array
	 */
	public static void printBoard(char[][] in) {															
		System.out.println("  0   1   2   3   4   5   6 ");
		for (int row = 0; row < 6; row++) {
			System.out.print("| ");
			
			for (int column = 0; column < 7; column++) {
				System.out.print(in[row][column] + " | ");
			}
			System.out.println();
	}
	System.out.println(".............................");
	System.out.print("\n");
	}
	/**
	 * This method records a player's move to its respective array
	 * @param column
	 * The column that the player drops the coin in
	 * @param in
	 * The game baord, represented as a character array
	 * @param color
	 * R for the red player, Y for the yellow player
	 */
	public static void move(int column, char[][] in, char color) {											//Records a player's move to its respective array
		int row = 5;
		while (in[row][column] != 0) {
			row--;
		}
		in[row][column] = color;
		printBoard(in);
		if (color == 'R') 
			p1[row][column] = 1;
		if (color == 'Y')
			p2[row][column] = 1;
	}
	
	/**
	 * This method checks the total moves made by a player and checks for win conditions
	 * @param player
	 * The moves a player made, represented as an integer array
	 * @return
	 * True if connect 4 is found, false if not
	 */
	public static boolean check(int[][] player) {	 														//Checks for win conditions, returns true when a connect 4 is found
		int colSum = 0;
		int rowSum = 0;
		
		//DIAGONAL CONNECT 4 -----------------------------------------------
		if (diagCheck(player, 1, 0, 4, 0, 3) ||		//Right Diagonals
			diagCheck(player, 1, 0, 5, 0, 2) ||  
			diagCheck(player, 1, 0, 6, 0, 1) ||     //RMain Diag1
			diagCheck(player, 1, 0, 6, 0, 0) ||		//RMain Diag2
			diagCheck(player, 1, 0, 5, 1, 0) ||  
			diagCheck(player, 1, 0, 4, 2, 0) ||
			
			diagCheck(player, -1, 0, 4, 0, 3) || 	//Left Diagonals
			diagCheck(player, -1, 0, 5, 0, 4) ||
			diagCheck(player, -1, 0, 6, 0, 5) ||  	//LMain Diag1
			diagCheck(player, -1, 0, 6, 0, 6) ||  	//LMain Diag2
			diagCheck(player, -1, 1, 6, 0 ,7) ||
			diagCheck(player, -1, 2, 6, 0, 8) 
			
			)
			return true;
		
		//HORIZONTAL CONNECT 4 --------------------------------------------
		for (int x = 0; x < 7; x++) {
			for (int y = 5; y > 0; y-- ) {				
				if (player[y][x] == 0) {
					rowSum = 0;
					continue;
				}
				rowSum += player[y][x];
				if (rowSum == 4)
					return true;
			}
		}
		//VERTICAL CONNECT 4 --------------------------------------------
		for(int x = 0; x < 6; x++) {
			for (int y = 0 ; y < 7; y++) {				
				if (player[x][y] == 0) {
					colSum = 0;
					continue;
				}
			colSum += player[x][y];
			if (colSum == 4)
				return true;
			}
			colSum = 0;
		} 
		
		return false;
		
	}
	/**
	 * This method checks diagonals, controlled by the parameters
	 */
	public static boolean diagCheck(int[][] p, int mode, int start, int limit, int mod1, int mod2 ) {		
		int sum = 0;
		for(int x = start; x < limit; x++) {
			if (p[x + mod1][(mode * x) + mod2] ==0) {
				sum = 0;
				continue;
			}
			sum += p[x + mod1][(mode * x) + mod2];
			if (sum == 4)
				return true;
		}
		return false;
	}
	
	/**
	 * This method checks if the game board is full
	 * @param board
	 * The game board, represented as a character array
	 * @return
	 * True if it is full, false if not
	 */
	public static boolean checkBoard(char[][] board) {														
		for(int x = 0; x < 6; x++) {	
			for (int y = 0; y < 7; y++) {
				if (board[x][y] == 0)
					return false;
			}
		}
		
		return true;
	}
}

	
