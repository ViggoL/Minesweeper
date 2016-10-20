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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import minesweeper.Model.Difficulty;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public abstract class GameViewSuper {
    
    public VBox buttonPane;
    public Scene scene;
    protected double buttonSpacing;
    protected double buttonWidth;
    protected double buttonPaneWidth;
    protected final Menu settingsMenu;
    protected final MenuBar menuBar;
    
    

    public GameViewSuper() {
        buttonPane = new VBox();
        buttonSpacing = 10.0;
        buttonWidth = Double.MAX_VALUE;
        
        buttonPane.setSpacing(buttonSpacing);
        buttonPane.setAlignment(Pos.TOP_CENTER);
        
        settingsMenu = new Menu(" Settings");
        
        menuBar = new MenuBar();

        for(Difficulty d: Difficulty.values()){
            MenuItem item = new MenuItem(d.toString());
            settingsMenu.getItems().add(item);
        }
        
        menuBar.getMenus().add(settingsMenu);
        menuBar.useSystemMenuBarProperty();
    }
    
}
