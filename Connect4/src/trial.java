//TEST FILE FOR THE IMPLEMENTATION OF THE DIAGONALS
public class trial {
	public static void main(String[] args) {
		char[][] in = new char[6][7];
		
		int x = 0;
			for (int y = 0; y < 4; y++ ) {
				in[y][y + 3] = 'X';
			}
			
			for (int y = 0; y < 4; y++) {
				in[y+2][y] = 'A';
			}
			
			for (int y = 0; y < 5; y++) {
				in[y+1][y] = 'B';
			}
			printer(in);
			
			
		System.out.print((int) (Math.random() * 6));

	}
	public static void printer(char[][] in) {
		System.out.println("  0   1   2   3   4   5   6 ");
		for (int row = 0; row < 6; row++) {
		System.out.print("| ");
		for (int column = 0; column < 7; column++) {
			System.out.print(in[row][column] + " | ");
		}
		System.out.println();
		
	}
	System.out.println(".............................");
	}
	
	public static boolean diagCheck(int[][] p, int mode, int start, int limit, int mod1, int mod2 ) {
		int sum = 0;
		for(int x = start; x < limit; x++) {
			if (p[x + mod1][(mode * x) + mod2] ==0) {
				sum = 0;
				continue;
			}
			sum += p[x + mod1][x + mod2];
			if (sum == 4)
				return true;
		}
		return false;
	}
	
	}


