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

import javafx.stage.Stage;
import minesweeper.Model.Difficulty;
import minesweeper.Model.Minesweeper;
import minesweeper.View.GameView;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class NewGame {

    private final Stage primaryStage;
    
    public NewGame(Stage stage, Difficulty diff){
        stage.close();
        
        // The observable-observer initialization
        Minesweeper model = new Minesweeper(diff);
        
        //The GUI is initialized
        GameView viewer = new GameView(model);
        
        //Observers are added
        model.timer.addObserver(viewer);

        
        primaryStage = new Stage();
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(viewer.scene);
        primaryStage.setResizable(false);

        primaryStage.show();
        primaryStage.centerOnScreen();
    }
    
    public NewGame(Difficulty diff){
        this(new Stage(),diff);
    }
    
    public NewGame(){
        this(Difficulty.EASY);
    }
    
}
