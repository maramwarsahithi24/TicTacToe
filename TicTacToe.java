import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 3;
    private static char[][] board = new char[SIZE][SIZE];
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        boolean playAgain = true;
        while (playAgain) {
            initBoard();
            playGame();
            System.out.print("Play again? (y/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            playAgain = resp.equals("y") || resp.equals("yes");
        }
        System.out.println("Thanks for playing!");
        sc.close();
    }

    private static void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void playGame() {
        char currentPlayer = 'X';
        int moves = 0;
        boolean gameOver = false;

        while (!gameOver) {
            displayBoard();
            System.out.println("Player " + currentPlayer + "'s turn. Enter row and column:");

            int row = -1, col = -1;
            while (true) {
                String line = sc.nextLine().trim();
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("Please enter two numbers separated by space (row col). Try again:");
                    continue;
                }
                try {
                    row = Integer.parseInt(parts[0]);
                    col = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numbers. Try again:");
                    continue;
                }
                if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
                    System.out.println("Row and column must be between 0 and " + (SIZE - 1) + ". Try again:");
                } else if (board[row][col] != ' ') {
                    System.out.println("Cell already taken. Choose another:");
                } else {
                    break; // valid move
                }
            }

            board[row][col] = currentPlayer;
            moves++;

            if (isWinner(currentPlayer)) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins! 🎉");
                gameOver = true;
            } else if (moves == SIZE * SIZE) {
                displayBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("\n Current Board:\n");
        System.out.print("    ");
        for (int c = 0; c < SIZE; c++) {
            System.out.print(c + "   ");
        }
        System.out.println();
        System.out.println("  ┌───┬───┬───┐");
        for (int r = 0; r < SIZE; r++) {
            System.out.print(r + " │");
            for (int c = 0; c < SIZE; c++) {
                System.out.print(" " + board[r][c] + " │");
            }
            System.out.println();
            if (r < SIZE - 1)
                System.out.println("  ├───┼───┼───┤");
        }
        System.out.println("  └───┴───┴───┘\n");
    }

    private static boolean isWinner(char player) {
        // Check rows
        for (int r = 0; r < SIZE; r++) {
            boolean win = true;
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] != player) { win = false; break; }
            }
            if (win) return true;
        }

        // Check columns
        for (int c = 0; c < SIZE; c++) {
            boolean win = true;
            for (int r = 0; r < SIZE; r++) {
                if (board[r][c] != player) { win = false; break; }
            }
            if (win) return true;
        }

        // Check main diagonal
        boolean diag1 = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] != player) { diag1 = false; break; }
        }
        if (diag1) return true;

        // Check anti-diagonal
        boolean diag2 = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][SIZE - 1 - i] != player) { diag2 = false; break; }
        }
        return diag2;
    }
}