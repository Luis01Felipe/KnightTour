package io.github.luis01felipe.knighttour;

public class KnightTourSolver {

    private static final int BOARD_SIZE = 8;
    private static final int[] rowMoves = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] colMoves = {1, 2, 2, 1, -1, -2, -2, -1};

    private final int[][] board;

    public KnightTourSolver() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
    }

    public static void main(String[] args) {
        KnightTourSolver solver = new KnightTourSolver();
        if (solver.solveFrom(0, 0)) {
            solver.printBoard();
        } else {
            System.out.println("Solução não encontrada.");
        }
    }

    public boolean solveFrom(int row, int col) {
        board[row][col] = 1;
        return solve(row, col, 2);
    }

    private boolean solve(int row, int col, int moveCount) {
        if (moveCount > BOARD_SIZE * BOARD_SIZE) {
            return true; // Todas as casas foram visitadas
        }

        for (int i = 0; i < 8; i++) {
            int nextRow = row + rowMoves[i];
            int nextCol = col + colMoves[i];

            if (isValidMove(nextRow, nextCol)) {
                board[nextRow][nextCol] = moveCount;
                if (solve(nextRow, nextCol, moveCount + 1)) {
                    return true;
                }
                board[nextRow][nextCol] = 0; // Backtrack
            }
        }

        return false;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < BOARD_SIZE &&
                col >= 0 && col < BOARD_SIZE &&
                board[row][col] == 0;
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = 0;
            }
        }
    }

    private void printBoard() {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.printf("%2d ", cell);
            }
            System.out.println();
        }
    }
}
