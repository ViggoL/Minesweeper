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

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class GameView extends GameViewSuper{
    
    public Button pauseButton;
    public BorderPane gameFrame;
    
    public GameView(){
        super();
        buttonPaneWidth = 35.0;
        playButton = new GameButton(buttonPaneWidth, GameButton.ButtonEnum.PLAY).getButton();
        pauseButton = new GameButton(buttonPaneWidth, GameButton.ButtonEnum.PAUSE).getButton();
        rulesButton = new GameButton(buttonPaneWidth, GameButton.ButtonEnum.HELP).getButton();
    }


    public void update(Stage primaryStage) {
        playButton.setAlignment(Pos.CENTER);
        playButton.setMaxWidth(buttonWidth);
        settingsButton.setMaxWidth(buttonWidth);
        rulesButton.setMaxWidth(buttonWidth);
    }
    
}
