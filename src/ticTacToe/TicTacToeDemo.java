/* 
 * Tic Tac Toe in Java
 * 2021 June 28
 * Tim Szewczyk
 */



/*
 * This script is approximately copied from https://github.com/aoyshi/Java-Tic-Tac-Toe
 * 
 * I'll add in comments to understand what the code is doing, and then work on my own script
 */


package ticTacToe;

import java.util.Scanner;



public class TicTacToeDemo {

	
	// requests user input for 0, 1, or 2, returning an error for any other entries.
	// right now, I don't know why this is needed.
	public static int getValidInt(String prompt) {
		
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print(prompt);
			String input = in.nextLine();
			int num = 0;
			try {
				num =  Integer.parseInt(input);
			}
			catch(Exception e) {
				System.out.println("Invalid integer. Try again.");
				continue;
			}
			if(num < 0 || num > 2) {
				System.out.println("Integer must be between 0 and 2.");
				continue;
			}
			return num;
		}
	}
		
		
		
	// check to see if any rows have tic tac toe
	public static boolean checkRows(int[][] A) {
		
		for(int i=0; i<A.length; i++) {
			if( (A[i][0]==A[i][1]) && (A[i][1]==A[i][2]) && A[i][0]!=0) {					
				return true;
			}
		}
		return false;
		
	}
	
	
	
	// check to see if any columns have tic tac toe
	public static boolean checkCols(int[][] A) {
		
		for(int i=0; i<A.length; i++) {
			if( (A[0][i]==A[1][i]) && (A[1][i]==A[2][i]) && A[0][i]!=0) {					
				return true;
			}
		}
		return false;
		
	}
				
	
	
	/// check to see if any columns have tic tac toe
	public static boolean checkDiags(int[][] A) {
		
		if( (A[0][0]==A[1][1]) && (A[1][1]==A[2][2]) && A[0][0]!=0) {
			return true;
		} else if( (A[2][0]==A[1][1]) && (A[1][1]==A[0][2]) && A[1][1]!=0 ) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	// check if move is a winning move
	public static boolean checkHit(int[][] A) {
		
		if( checkRows(A) || checkCols(A) || checkDiags(A) ) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	// check to see if a square is occupied
	public static boolean isFree(int[][] A, int row, int col) {
		
		if(A[row][col]==0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	
	// request move, check if it's valid, then check if it's a winner
	public static boolean getWinner(String turnPrompt, int[][] A, int playerNumber) {
		
		System.out.println(turnPrompt);
		int row=0, col=0;
		while(true) {
			row = getValidInt("Enter row: ");
			col = getValidInt("Enter col: ");
			if(isFree(A, row, col)) {
				break;
			}
			System.out.printf("[%d,%d] is already filled. Try again!", row, col);
		}
		A[row][col] = playerNumber;
		return(checkHit(A));
		
	}
	
	
	
	// print out board to screen
	public static void printBoard(int[][] A) {
		
		System.out.println("\n-------------");
		
		for(int i=0; i<3; i++) {
			
			System.out.print("| ");
			
			for(int j=0; j<3; j++) {
				
				System.out.print(A[i][j] + " | ");
				
			}
			
			System.out.println();
			System.out.println("-------------");
		}
		System.out.println();
		
	}
	
	
	
	// main method
	public static void main(String[] args) {
		
		// initialize board
		int[][] grid = new int[3][3];
		int foundWinner = 0;
		printBoard(grid);
		
		// run gameplay: 9 possible turns
		int i = 0;
		while(i < 9) {
			
			if(i%2==0) {
				
				// player 1
				if(getWinner("Player 1 turn", grid, 1)) {
					foundWinner = 1;
					System.out.println("\nPlayer 1 wins!");
					printBoard(grid);
					System.out.println();
					break;
				}
				printBoard(grid);
				System.out.println();
				
			} else {
				
				//player 2
				if(getWinner("Player 2 turn", grid, 2)) {
					foundWinner=1;
					System.out.println("\nPlayer 2 wins!");
					printBoard(grid);
					System.out.println();
					break;
				}
				printBoard(grid);
				System.out.println();
			}
			i++;
		}
		
		if(foundWinner==0) {
			System.out.println("It's a draw :/ ");
		}

	}

}
