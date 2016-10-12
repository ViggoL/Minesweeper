/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * [Project] license
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
package minesweeper.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class Board {
    private List<List<Tile>> tiles;
    private final int [][] dimensions; 
    private boolean win;
    
    Board(){
        dimensions = new int [10][10];
        win = false;
        tiles = new ArrayList<>();
    }
    
    public List<List<Tile>> getTiles()
    {
        return this.tiles;
    }
    public Tile getTile(Point point)
    {
        
    }
}
