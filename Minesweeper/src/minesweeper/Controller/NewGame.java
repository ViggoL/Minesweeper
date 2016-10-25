/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * Minesweeper license
 * 
 * Copyright © 2016 Johan Lipecki & Viggo Lundén
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
package minesweeper.Controller;

import javafx.scene.Node;
import javafx.stage.Stage;
import minesweeper.Model.Difficulty;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;
import minesweeper.View.GameView;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class NewGame {

    private final Stage primaryStage;
    private final Minesweeper game;
    
    public NewGame(Stage oldstage, Difficulty diff){
        oldstage.close();
        
        primaryStage = new Stage();
        
        // The observable-observer initialization
        game = new Minesweeper(diff);
        
        //The GUI is initialized
        GameView viewer = new GameView(game);
        viewer.gameStage = primaryStage;
        
        //Observers are added
        game.timer.addObserver(viewer);
        game.board.addObserver(viewer);
        game.addObserver(viewer);
        //for(Tile t: game.board.getTiles()) t.addObserver(viewer);
        
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(viewer.scene);
        primaryStage.setResizable(false);

        primaryStage.show();
        primaryStage.centerOnScreen();
    }
    
    public NewGame(Difficulty diff){
        this(new Stage(),diff);
    }
    
    public void hideStage(){
        primaryStage.hide();
    }
    
    public void showStage(){
        primaryStage.show();
    }
    
    public Minesweeper getGame(){
        return game;
    }
    
}
