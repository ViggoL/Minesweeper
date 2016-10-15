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
package minesweeper.Model;

import javafx.scene.paint.Color;
import static minesweeper.Model.Difficulty.*;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class Settings {
    private Color tileColor;
    Difficulty difficulty;
    
    public Settings(){
        tileColor = Color.GRAY;
        difficulty = EASY;
    }
    
    public Color getTileColor(){
        return tileColor;
    }
    public void setTileColor(Color color){
        this.tileColor = color;
    }
    
    public void setDifficulty(Difficulty diff){
        this.difficulty = diff;
    }

    public Difficulty getDifficulty() {
        return this.difficulty;
    }
    
    
}
