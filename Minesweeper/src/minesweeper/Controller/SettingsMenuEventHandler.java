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
package minesweeper.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import minesweeper.Model.Difficulty;
import minesweeper.Model.Minesweeper;
import minesweeper.View.GameView;

/**
 * The controller for the settings menu
 * @author Johan Lipecki <lipecki@kth.se>
 */
public final class SettingsMenuEventHandler implements EventHandler<ActionEvent> {
    
    private final GameView settingsHandler;
    private final Minesweeper game;
    
    /**
     * Create a new settings menu event handler
     * @param game the game to handle
     * @param settingsHandler the game view to handle
     */
    public SettingsMenuEventHandler(Minesweeper game,final GameView settingsHandler) {
        this.settingsHandler = settingsHandler;
        this.game = game;
        handle(new ActionEvent());
    }

    @Override
    public void handle(ActionEvent event) {
        Object o = event.getSource();
        if (o instanceof MenuItem) {
            MenuItem m = (MenuItem) o;
            game.setDifficulty(Difficulty.valueOf(m.getText()));
        }
    }
    
}
