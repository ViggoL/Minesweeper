
import minesweeper.Model.Board;
import minesweeper.Model.Difficulty;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.MinesweeperMain;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * [Project] license
 * 
 * Copyright © 2016 Johan Lipecki
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class TestBoard {

    public Board board;
    
    public TestBoard(){
        MinesweeperMain game = new MinesweeperMain();
        board = new Board(Difficulty.EASY);
        
        TestMinesweeper();
    }
    
    
    /**
     * @param args the command line arguments
     */
  
    public static void main(String[] args) {
        // TODO code application logic here
        TestBoard test1 = new TestBoard();
        System.out.println(test1.board.toString());
    }

    private void TestMinesweeper() {
        Minesweeper game = new Minesweeper();
        game.Minesweeper();
        System.out.println(game.getBoard().toString());
    }
    
    
}
