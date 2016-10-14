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
package minesweeper.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>, Viggo Lundén <vlunden@kth.se>
 */
public class GameView extends GameViewSuper{
    
    public Button pauseButton;
    public BorderPane gameFrame;
    private Stage stage;
    public GameView(){
        super();
        buttonPaneWidth = 10.0;
        buttonWidth = 5;
        playButton = new GameButton(20, GameButton.ButtonEnum.PLAY).getButton();
        pauseButton = new GameButton(20, GameButton.ButtonEnum.PAUSE).getButton();
        rulesButton = new GameButton(20, GameButton.ButtonEnum.HELP).getButton();
        resumeButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PLAY).getButton();
        buttonPane = new VBox();
        gameFrame = new BorderPane();
        stage = new Stage();
        
    }


    public void update(Stage primaryStage) {
        buttonPane.setPadding(new Insets(5));
        buttonPane.setAlignment(Pos.BASELINE_LEFT);
        buttonPane.getChildren().add(pauseButton);
        buttonPane.getChildren().add(playButton);
        gameFrame.setLeft(buttonPane);
        Scene scene = new Scene(gameFrame, 300, 200);
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        //stage.setResizable(false);
        
        
        stage.show();
        stage.centerOnScreen();
    }
    
}
