package com.mhamed.fifel;

import com.mhamed.fifel.model.entities.Board;
import com.mhamed.fifel.model.enums.GameState;
import com.mhamed.fifel.model.enums.Mark;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The main class
 */
public class Main {

    private Board board;

    private GameState currentState;

    private Mark currentPlayer;

    private static Scanner scanner = new Scanner(System.in);



    public Main(){ }
    // method to run the game
    public void run() {
        board = new Board();

        // Initialiseer de game board and
        // print this with empty content
        initGame();
        board.paint();

        //speel één maal (spelers spelen om de beurt)
        do {
            playerTurn(currentPlayer);
            board.paint();
            updateGame(currentPlayer);

            // game-over ? print een message
            if (currentState == GameState.CROSS_WON) {
                System.out.println("'X' won! thanks for playing!");
            } else if (currentState == GameState.NOUGHT_WON) {
                System.out.println("'O' won! thanks for playing!");
            } else if (currentState == GameState.DRAW) {
                System.out.println("It's Draw! thanks for playing!");
            }
            // andere speler's beurt
            currentPlayer = (currentPlayer == Mark.CROSS) ? Mark.NOUGHT : Mark.CROSS;
        }
        // repeat tot game-over
        while (currentState == GameState.PLAYING);

    }


    // Initialiseer de game-board inhoud en de huidige states
    public void initGame() {
        // clear the board contents
        board.init();
        // CROSS speelt eerst
        currentPlayer = Mark.CROSS;
        currentState = GameState.PLAYING;
        // ready for start
    }



     // speler neemt de beurt met input validatie
     // update huidige kolom en rij van de game board en cellinhoud.
    public void playerTurn(Mark mark) {
        boolean validInput = false;
        String player;
        do {
            if (mark == Mark.CROSS) {
                player = "'X'";
            } else {
                player = "'O'";
            }
            try {
                System.out.print("Player " + player +", enter the coordinates of your placement (1, 2 or 3) \nrow: ");
                int row = scanner.nextInt();
                System.out.print("column: ");
                int col = scanner.nextInt();
                validInput = isValidInput(mark, row, col);
            }
            catch (InputMismatchException ex){
                System.out.println("Invalid coordinate(s): must be a number. Try again..");
                scanner.next(); // avoid infinity loop by pointing scanner to the token value that causes exception
            }
        }
        // repeat tot dat input valid is
        while (!validInput);
    }

    public boolean isValidInput(Mark mark, int row, int col) {
        boolean  validInput = false;
        row = row - 1;
        col = col - 1;
        if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                && board.getCells()[row][col].getContent() == Mark.EMPTY) {
            board.getCells()[row][col].setContent(mark);
            board.setCurrentRow(row);
            board.setCurrentCol(col);
            validInput = true;
        } else {
            System.out.println("This placement at (" + (row + 1) + "," + (col + 1)
                    + ") is not valid or already taken. Try again...");
        }
        return validInput;
    }


    // Update de huidige gameState na elke spelerbeurt
    public  void updateGame(Mark mark) {
        if (board.hasWon(mark)) {  // check de winaar
            currentState = (mark == Mark.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
        } else if (board.isDraw()) {  // check gelijkspel
            currentState = GameState.DRAW;
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Mark getCurrentPlayer() {
        return currentPlayer;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    // The entry main() method
    public static void main(String[] args) {
        new Main().run();
    }
}


