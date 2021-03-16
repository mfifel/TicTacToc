package com.mhamed.fifel.model.entities;

import com.mhamed.fifel.model.enums.Mark;

/**
 * de game-board model class.
 */
public class Board {

    public static final int ROWS = 3;
    public static final int COLS = 3;

    // board voor (3x3) instances
    private Cell[][] cells;

    private int currentRow, currentCol;



    public Board() {
        cells = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                // creëer een leeg board
                cells[row][col] = new Cell(row, col);

            }
        }
    }


    // Initialiseer de inhoud van de  game bord
    public void init() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                // clear the cell content
                cells[row][col].clear();
            }
        }
    }


    // Return true bij gelijkspel (geen EMPTY cell meer)
    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (cells[row][col].getContent() == Mark.EMPTY) {
                    return false;// stop de lus bij een gevonden leeg cell
                }
            }
        }
        return true;
    }


    // Return true als de speler 3x dezelfde markering (x of 0) heeft geplaatst
    // op de huidige cell (currentRow, currentCol) : vertical, horizontal of diagonal
    public boolean hasWon(Mark mark) {
        return (// 3 marks horizontal
                cells[currentRow][0].getContent() == mark
                && cells[currentRow][1].getContent() == mark
                && cells[currentRow][2].getContent() == mark
                ||
                // 3 marks vertical
                cells[0][currentCol].getContent() == mark
                && cells[1][currentCol].getContent() == mark
                && cells[2][currentCol].getContent() == mark
                ||
                // 3 marks diagonal
                cells[0][0].getContent() == mark
                && cells[1][1].getContent() == mark
                && cells[2][2].getContent() == mark
                ||
                // 3 marks ander diagonal
                cells[0][2].getContent() == mark
                && cells[1][1].getContent() == mark
                && cells[2][0].getContent() == mark);
    }


    // print deze bord
    public void paint() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                cells[row][col].paint();
                if (col < COLS - 1) System.out.print("|");
            }
            System.out.println();
            if (row < ROWS - 1) {
                System.out.println("-----------");
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }
}