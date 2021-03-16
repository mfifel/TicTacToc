package com.mhamed.fifel.model.entities;

import com.mhamed.fifel.model.enums.Mark;

/**
 *  deze class modeleert elke individuele cel/vierkant van de game bord
 */
public class Cell {
    // inhoud van deze cell van het type 'Mark'.
    private Mark content;


    private int row, col;

    // Constructor voor de initialisatie van deze cell
     public Cell(int row, int col) {
     this.row = row;
     this.col = col;
     // clear inhoud
     clear();
     }


    // Clear de cellinhoud naar 'EMPTY'
     public void clear() {
     content = Mark.EMPTY;
     }

    //print de cell inhoud afhankelijk van de Mark type
    public void paint() {
        switch (content) {
            case CROSS:
                System.out.print(" X ");
                break;
            case NOUGHT:
                System.out.print(" O ");
                break;
            case EMPTY:
                System.out.print("   ");
                break;
        }
    }

    public Mark getContent() {
        return content;
    }

    public void setContent(Mark content) {
        this.content = content;
    }
}
