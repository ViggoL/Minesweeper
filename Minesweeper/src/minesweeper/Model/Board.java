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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class Board extends Observable{
    private List<Tile> tiles;
    private boolean win;
    
    public Board(Difficulty diff){
        win = false;
        tiles = new ArrayList<>();
        int x = 0;
        int y = 0;
        
        switch(diff){
            case EASY: while(tiles.size() < 100) {
                while(tiles.size()/10 <= 9){
                    x = tiles.size()%10;
                    y = tiles.size()/10;
                    tiles.add(new Tile(new Point(x,y),TileType.EMPTY));
                }
            }
        }
    }
    
    public List<Tile> getTiles()
    {
        return this.tiles;
    }
    
    /**
     * Returns the tile at the given point at the board.
     * @param point
     * @return null if the tile can't be found
     */
    public Tile getTile(Point point)
    {
        for (Tile t : tiles)
                if (t.getPoint().equals(point)) return t;
        return null;
    }
    
    /**
     * Get information on Tile Point(x and y coordinates) and TileType
     * @return ArrayList<Object[]> where Object[0] is Point and Object[1] is TileType
     */
    public ArrayList<Object[]> getTileInfo(){
        ArrayList<Object[]> infoList = new ArrayList();
        for(Tile t: (ArrayList<Tile>) tiles){
            Object [] o = {t.getPoint(),t.getType()}; 
            infoList.add(o);
        }
        
        return infoList;
    }
    
    @Override
    public String toString(){
        StringBuilder string = new StringBuilder();
        for(Object[] ss: getTileInfo()){
                string.append(Arrays.toString(ss));
            string.append("\n");
        }
        return string.toString();
    }
}
