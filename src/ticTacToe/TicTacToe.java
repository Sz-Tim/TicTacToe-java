package ticTacToe;


import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {	
	
	// input board size
	public static int getBoardSize(String prompt) {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		while(true) {
			System.out.print(prompt);
			String input = in.nextLine();
			int n = 0;
			try {
				n = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.print("Invalid board size. Please select a number greater than 2.");
				continue;
			}
			if(n < 3) {
				System.out.print("Invalid board size. Please select a number greater than 2.");
				continue;
			}
			return(n);
		}
		
	}
	
	
	
	
	// input valid move
	public static int getValidMove(String prompt, char[][] B) {
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		while(true) {
			System.out.print(prompt);
			String input = in.nextLine();
			int num = 0;
			try {
				num = Integer.parseInt(input);
			} catch(Exception e) {
				System.out.println("Invalid move. Please select a row or column from 1-"+B.length);
				continue;
			}
			if(num < 1 || num > B.length) {
				System.out.println("Invalid move. Please select a row or column from 1-"+B.length);
				continue;
			}
			return num-1;
		}
		
	}
	
	
	
	
	// make move
	public static int[] makeMove(String turnPrompt, char[][] B, char player) {
		
		System.out.println(turnPrompt);
		int[] move = new int[2];
		while(true) {
			move[0] = getValidMove("Enter row:", B);
			move[1] = getValidMove("Enter column:",  B);
			if(isSpaceFree(B, move[0], move[1])) {
				break;
			}
			System.out.printf("[%d,%d] is already taken. Please select an open space.\n", move[0], move[1], B.length);
		}
		return move;
		
	}
	
	
	
	
	// check to make sure space is free
	public static boolean isSpaceFree(char[][] B, int move_row, int move_col) {
		
		char freeSpace = ' '; 
		if(B[move_row][move_col] == freeSpace) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	
	// check to see if last move won by completing a row
	public static boolean checkRow(char[][] B, char player, int move_row) {
		
		int row_total = 0;
		for(int i = 0; i < B.length; i++) {
			if(B[move_row][i] == player) {
				row_total++;
			}
		}	
		if(row_total == B.length) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	// check to see if last move won by completing a row
	public static boolean checkCol(char[][] B, char player, int move_col) {
		
		int col_total = 0;
		for(int i = 0; i < B.length; i++) {
			if(B[i][move_col] == player) {
				col_total++;
			}
		}	
		if(col_total == B.length) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	// check to see if any diagonals win
	public static boolean checkDiags(char[][] B, char player) {
		
		int diag1_total = 0, diag2_total = 0;
		for(int i = 0; i < B.length; i++) {
			if(B[i][i] == player) {
				diag1_total++;
			}
			if(B[i][B.length-1-i] == player) {
				diag2_total++;
			}
		}	
		if(diag1_total == B.length || diag2_total == B.length) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	// check if the move is a winning move
	public static boolean checkForWin(char[][] B, char player, int[] move) {
		
		if(checkRow(B, player, move[0]) || checkCol(B, player, move[1]) || checkDiags(B, player)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	
	// print current game board
	public static void printBoard(char[][] B) {
		
		// print board top
		char[] boardEdge = new char[B.length*4-1];
		Arrays.fill(boardEdge, '-');
		System.out.print("|");
		System.out.print(boardEdge);
		System.out.println("|");
		
		// print board rows
		for(int i = 0; i < B.length; i++) {
			System.out.print("| ");
			for(int j = 0; j < B.length; j++) {
				System.out.print(B[i][j] + " | ");
			}
			// print row boundaries
			System.out.println();
			System.out.print("|");
			System.out.print(boardEdge);
			System.out.println("|");
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		int n = getBoardSize("Enter a board size (3 for standard 3x3 board):");
		char[][] board = new char[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				board[i][j] = ' ';
			}
		}
		int somebodyWon = 0;
		printBoard(board);
		
		// run game play
		int i = 0;
		while(i < Math.pow(board.length, 2)) {
			
			if(i%2 == 0) {
				
				// player 1: X
				int[] move_X = makeMove("X's turn", board, 'X');
				board[move_X[0]][move_X[1]] = 'X';
				if(checkForWin(board, 'X', move_X)) {
					somebodyWon = 1;
					System.out.println("\nX wins!");
					printBoard(board);
					break;
				}
				printBoard(board);
				System.out.println();
				
			} else {
				
				// player 2: O
				int[] move_O = makeMove("O's turn", board, 'O');
				board[move_O[0]][move_O[1]] = 'O';
				if(checkForWin(board, 'O', move_O)) {
					somebodyWon = 1;
					System.out.println("\nO wins!");
					printBoard(board);
					break;
				}
				printBoard(board);
				System.out.println();
				
			}
			
			i++;
			
		}
		
		if(somebodyWon == 0) {
			System.out.println("Draw! Start again to find the superior player.");
			printBoard(board);
		}

	}

}
