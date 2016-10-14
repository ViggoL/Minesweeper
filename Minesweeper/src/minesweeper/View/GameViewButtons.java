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
package minesweeper.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public abstract class GameViewButtons {
    
    public Button playButton;
    public Button settingsButton;
    public Button rulesButton;
    protected VBox buttonPane;
    double buttonSpacing;
    double buttonWidth;
    double buttonPaneWidth;

    public GameViewButtons() {
        buttonPane = new VBox();
        buttonSpacing = 10.0;
        buttonWidth = Double.MAX_VALUE;
        
        buttonPane.setSpacing(buttonSpacing);
        buttonPane.setAlignment(Pos.CENTER);
    }
    
}
