package com.mhamed.fifel;

import com.mhamed.fifel.model.entities.Board;
import com.mhamed.fifel.model.entities.Cell;
import com.mhamed.fifel.model.enums.GameState;
import com.mhamed.fifel.model.enums.Mark;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.configuration.injection.scanner.MockScanner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MainTest {

    private Main game;

    @BeforeEach
    void setUp() {
        game = new Main();
        Board board= new Board();
        game.setBoard(board);
    }

    //testen van user input

     //case 1
     @Test
    public void XgreatherThen3Test(){
        int x = 4;
        int  y = 1;

        boolean validInput = game.isValidInput(Mark.CROSS,y,x);

        Assertions.assertFalse(validInput);

    }
    //case 2
    @Test
    public void YgreatherThen3Test(){
        int x = 2;
        int  y = 5;

        boolean validInput = game.isValidInput(Mark.CROSS,y,x);

        Assertions.assertFalse(validInput);

    }

    //case 3
    @Test
    public void PlaceAlraedyTakenTest(){
        int x = 2;
        int  y = 2;

        //no error expected
        boolean validInput = game.isValidInput(Mark.CROSS,y,x);
        Assertions.assertTrue(validInput);

        //other player tries to take occupied place
        validInput = game.isValidInput(Mark.NOUGHT,y,x);
        Assertions.assertFalse(validInput);

    }

    //case 4
    @Test
    public void YandXareCorrectTest(){
        int x = 3;
        int  y = 3;

        //no error expected
        boolean validInput = game.isValidInput(Mark.NOUGHT,y,x);
        Assertions.assertTrue(validInput);

    }

    //testen van initialisatie
    //case 1
    @Test
    public void checkEmptyBoardTest(){

        game.initGame();
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                Assertions.assertTrue(game.getBoard().getCells()[row][col].getContent() == Mark.EMPTY);
            }
        }
    }

    //case 2
    @Test
    public void checkFirstPlayerTest(){
        game.initGame();
        Assertions.assertTrue(game.getCurrentPlayer() == Mark.CROSS);

    }

    //case 2
    @Test
    public void checkGameStateTest(){
        game.initGame();
        Assertions.assertTrue(game.getCurrentState() == GameState.PLAYING);

    }


    //testen van game state
    //case 1
    @Test
    public void checkXwinsTest(){
        int x = 1;
        int  y = 1;
        game.isValidInput(Mark.CROSS,y,x);
         x = 2;
         y = 1;
        game.isValidInput(Mark.NOUGHT,y,x);
        x = 2;
        y = 2;
        game.isValidInput(Mark.CROSS,y,x);
        x = 3;
        y = 1;
        game.isValidInput(Mark.NOUGHT,y,x);
        x = 3;
        y = 3;
        game.isValidInput(Mark.CROSS,y,x);
        game.updateGame(Mark.CROSS);
        Assertions.assertTrue(game.getCurrentState() == GameState.CROSS_WON);

    }

    //case 1
    @Test
    public void checkDrawTest(){
        int x = 1;
        int  y = 1;
        game.isValidInput(Mark.CROSS,y,x);
        x = 2;
        y = 1;
        game.isValidInput(Mark.NOUGHT,y,x);
        x = 2;
        y = 2;
        game.isValidInput(Mark.CROSS,y,x);
        x = 3;
        y = 1;
        game.isValidInput(Mark.NOUGHT,y,x);
        x = 3;
        y = 2;
        game.isValidInput(Mark.CROSS,y,x);
        x = 3;
        y = 3;
        game.isValidInput(Mark.NOUGHT,y,x);
        x = 2;
        y = 3;
        game.isValidInput(Mark.CROSS,y,x);
        x = 1;
        y = 2;
        game.isValidInput(Mark.NOUGHT,y,x);
        x = 1;
        y = 3;
        game.isValidInput(Mark.CROSS,y,x);
        game.updateGame(Mark.CROSS);
        Assertions.assertTrue(game.getCurrentState() == GameState.DRAW);

    }

}