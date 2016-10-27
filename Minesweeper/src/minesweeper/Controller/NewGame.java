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
import minesweeper.View.ClockView;
import minesweeper.View.GameView;

/**
 * Used for creating a new game
 * @author Johan Lipecki <b>lipecki@kth.se</b>
 */
public class NewGame {

    private final Stage primaryStage;
    private final Minesweeper game;
    private ClockView clock;
    
    /**
     * Create a new game
     * @param oldstage the old stage to close
     * @param diff the difficulty of the new game
     */
    public NewGame(Stage oldstage, Difficulty diff){
        oldstage.close();
        
        primaryStage = new Stage();
        
        // The observable-observer initialization
        game = new Minesweeper(diff);
        clock = new ClockView(game);
        
        //The GUI is initialized
        GameView viewer = new GameView(game, clock);
        viewer.gameStage = primaryStage;
        
        //Observers are added
        game.getTimer().addObserver(viewer);
        game.board.addObserver(viewer);
        game.addObserver(viewer);
        //for(Tile t: game.board.getTiles()) t.addObserver(viewer);
        
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(viewer.scene);
        primaryStage.setResizable(false);

        primaryStage.show();
        primaryStage.centerOnScreen();
    }
    
    /**
     * Create a new game without closing the old stage
     * @param diff the difficulty of the new game
     */
    public NewGame(Difficulty diff){
        this(new Stage(),diff);
    }
    /**
     * Creates and loads an old game from a file
     * @param oldstage the old stage to close
     * @param diff the difficulty of the new game
     * @param filename the filename of the save file to load
     */
    public NewGame(Stage oldstage, Difficulty diff, String filename)
    {
        oldstage.close();
        
        primaryStage = new Stage();
        
        // The observable-observer initialization
        game = new Minesweeper(diff, filename);
        clock = new ClockView(game);
        
        //The GUI is initialized
        GameView viewer = new GameView(game, clock);
        viewer.gameStage = primaryStage;
        
        //Observers are added
        game.getTimer().addObserver(viewer);
        game.board.addObserver(viewer);
        game.addObserver(viewer);
        //for(Tile t: game.board.getTiles()) t.addObserver(viewer);
        
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(viewer.scene);
        primaryStage.setResizable(false);

        primaryStage.show();
        primaryStage.centerOnScreen();
        
    }
    /**
     * Hides the game
     */
    public void hideStage(){
        primaryStage.hide();
    }
    /**
     * Shows the game
     */
    public void showStage(){
        primaryStage.show();
    }
    /**
     * 
     * @return the inner game object
     */
    public Minesweeper getGame(){
        return game;
    }
    
}
