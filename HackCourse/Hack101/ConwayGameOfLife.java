import java.util.Scanner;

public class ConwayGameOfLife {
	
	static int countSurrounding(int[][] board, int row, int col) {
        int count = 0;
        int[][] surrounding = {{row - 1, col - 1},
                               {row - 1, col   },
                               {row - 1, col + 1},
                               {row    , col - 1},
                               {row    , col + 1},
                               {row + 1, col - 1},
                               {row + 1, col   },
                               {row + 1, col + 1}};
        for (int i[]: surrounding) {
            try {
                if (board[i[0]][i[1]] == 1) {
                    count++;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {}
        }
        return count;
    }

    static void printBoard(int[][] board) {
        for (int[] i: board) {
            for (int j: i) {
                if (j == 1) {
                	
                	System.out.print("▣" + " ");
                }
                else {
         
                    System.out.print("▢" + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	int n = input.nextInt();
    	 int[][] nextBoard  = new int[15][15];
    	
    	for(int i = 0; i < n; i++){
    		int x = input.nextInt();
    		int y = input.nextInt();
    		nextBoard[x][y] = 1;
    	}
        int[][] board = new int[nextBoard.length][nextBoard[0].length];
        for (int gen = 0; gen < 15; gen++) {
            for (int i = 0; i < nextBoard.length; i++) {
                for (int j = 0; j < nextBoard[i].length; j++) {
                    board[i][j] = nextBoard[i][j];
                }
            }
            printBoard(board);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 1 && !(countSurrounding(board, i, j) == 2 || countSurrounding(board, i, j) == 3)) {
                        nextBoard[i][j] = 0;
                    }
                    else if    (board[i][j] == 0 && (countSurrounding(board, i, j) == 2 || countSurrounding(board, i, j) == 3)) {
                        nextBoard[i][j] = 1;
                    }
                }
            }
        }
    }
}