import java.util.Scanner;

class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private int boardSize;

    public TicTacToe(int size) {
        boardSize = size;
        board = new char[boardSize][boardSize];
        currentPlayer = 'X';
        initializeBoard();
    }

    // Initialize the board with empty spaces
    private void initializeBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Print the board
    public void printBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Check if the board is full
    private boolean isBoardFull() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    // Check for a win
    private boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < boardSize; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }
        // Check diagonals
        return checkDiagonals();
    }

    private boolean checkRow(int row) {
        char first = board[row][0];
        if (first == '-') return false;
        for (int j = 1; j < boardSize; j++) {
            if (board[row][j] != first) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int col) {
        char first = board[0][col];
        if (first == '-') return false;
        for (int i = 1; i < boardSize; i++) {
            if (board[i][col] != first) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals() {
        char first = board[0][0];
        if (first != '-') {
            boolean win = true;
            for (int i = 1; i < boardSize; i++) {
                if (board[i][i] != first) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        first = board[0][boardSize - 1];
        if (first != '-') {
            boolean win = true;
            for (int i = 1; i < boardSize; i++) {
                if (board[i][boardSize - 1 - i] != first) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }
        return false;
    }

    // Make a move
    public boolean makeMove(int row, int col) {
        if (row < 0 || row >= boardSize || col < 0 || col >= boardSize || board[row][col] != '-') {
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    // Switch the current player
    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!makeMove(row, col)) {
                System.out.println("This move is not valid.");
                continue;
            }

            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull()) {
                printBoard();
                System.out.println("The game is a draw!");
                break;
            }

            switchPlayer();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.print("Enter the board size (3 for standard Tic Tac Toe): ");
        int size = scanner.nextInt();
        TicTacToe game = new TicTacToe(size);
        game.playGame();
    }
}
