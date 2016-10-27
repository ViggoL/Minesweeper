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
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import minesweeper.Controller.GridController;

/**
 * Represents a minesweeper board
 * @author Johan Lipecki <b>lipecki@kth.se</b>
 */
public class Board extends Observable {

    private List<Tile> tiles;
    private boolean win;
    private int maxX, maxY;

    /**
     * Creates a new board with desired difficulty
     * @param diff Difficulty
     */
    public Board(Difficulty diff) {
        win = false;
        tiles = new ArrayList<>();
        int x = 0;
        int y = 0;

        switch (diff) {
            case EASY:
                while (tiles.size() < 100) {
                    while (tiles.size() / 10 <= 9) {
                        x = tiles.size() % 10;
                        y = tiles.size() / 10;
                        tiles.add(new Tile(new Point(x, y), TileType.EMPTY));
                    }
                }

                break;
            case MEDIUM:
                while (tiles.size() < 175) {
                    while (tiles.size() / 15 <= 14) {
                        x = tiles.size() % 15;
                        y = tiles.size() / 15;
                        tiles.add(new Tile(new Point(x, y), TileType.EMPTY));
                    }
                }
                break;
            case HARD:
                while (tiles.size() < 289) {
                    while (tiles.size() / 17 <= 16) {
                        x = tiles.size() % 17;
                        y = tiles.size() / 17;
                        tiles.add(new Tile(new Point(x, y), TileType.EMPTY));
                    }
                }
                break;
        }
        maxX = x;
        maxY = y;

        //TODO: Tweak for each difficulty
        while (getAllBombs() <= maxX * Math.E) {
            int r = ThreadLocalRandom.current().nextInt(0, tiles.size());
            tiles.get(r).setType(TileType.BOMB);
        }
    }

    /**
     * Creates a new board with desired difficulty and predetermined tiles (for loading games)
     * @param diff Difficulty
     * @param tiles list of tiles
     */
    public Board(Difficulty diff, ArrayList<Tile> tiles)
    {
        this(diff);
        this.tiles = tiles;
    }
    
    /**
     * Creates a new board with EASY difficulty
     */
    public Board() {
        this(Difficulty.EASY);
    }

    /**
     * @return all of the tiles on the board
     */
    public List<Tile> getTiles() {
        return tiles;
    }

    /**
     * @return the number of bombs on the board
     */
    private int getAllBombs()
    {
        int count = 0;
        for (Tile t : tiles) {
            if (t.getType() == TileType.BOMB) count++;
        }
        return count;
    }
    
    /**
     * Returns the number of bombs adjacent to a tile
     * @param tile The Tile to check
     * @return number of surrounding bombs
     */
    public int bombCount(Tile tile) {
        List<Tile> tiles = getSurroundingTiles(tile);
        int i = 0;
        for (Tile t : tiles)
        {
            if (t != null && t.getType() == TileType.BOMB) i++;
        }
        return i;
    }

    /**
     * Retrieves a list of references to surrounding tiles
     * @param tile center tile to examine
     * @return All surrounding tiles of a Tile. Null-values for tiles outside of board.
     */
    public List<Tile> getSurroundingTiles(Tile tile) {
        Point maxP = new Point(maxX, maxY);
        Point tileP = tile.getPoint();
        ArrayList<Tile> tiles = new ArrayList();
        
        tiles.add(getTile(new Point(tileP.x, tileP.y - 1)));
        tiles.add(getTile(new Point(tileP.x + 1, tileP.y - 1)));
        tiles.add(getTile(new Point(tileP.x + 1, tileP.y)));
        tiles.add(getTile(new Point(tileP.x + 1, tileP.y + 1)));
        tiles.add(getTile(new Point(tileP.x - 1, tileP.y + 1)));
        tiles.add(getTile(new Point(tileP.x - 1, tileP.y)));
        tiles.add(getTile(new Point(tileP.x - 1, tileP.y - 1)));
        tiles.add(getTile(new Point(tileP.x, tileP.y + 1)));

        return tiles;
    }

    /**
     * Recursive method to uncover all the tiles of a board from a Tile, 
     * causing the board to expand according to the rules
     * @param tile the Tile to start uncovering from
     */
    public void uncover(Tile tile) {
        if (tile.isCovered()) {
            tile.setCovered(false);

            // The model has changed, notify observers!
            this.setChanged();
            this.notifyObservers();

            System.out.println("Uncover tile and search surroundings!");
            if (tile.getType() == TileType.EMPTY) {
                if (bombCount(tile) == 0) {
                    List<Tile> surrounding = getSurroundingTiles(tile);
                    for (Tile t : surrounding) {
                        if (t != null && t.getType() == TileType.EMPTY) {
                            uncover(t);
                        }

                    }
                }
            }
        }
    }

    /**
     * Returns the tile at the given point at the board.
     *
     * @param point The given points. Does not have to be a reference to the tile's point.
     * @return null if the tile can't be found
     */
    public Tile getTile(Point point) {
        for (Tile t : tiles) {
            if (t.getPoint().equals(point)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Returns the X and Y boundary of the board
     * @return Array of x and y coordinates 
     */
    public int[] getXYboundary() {
        int[] array = {maxX, maxY};
        return array;
    }

    /**
     * Get information on Tile Point(x and y coordinates) and TileType
     *
     * @return ArrayList of Object arrays 
     * where Object[0] is Tile Point and Object[1] is TileType
     */
    public ArrayList<Object[]> getTileInfo() {
        ArrayList<Object[]> infoList = new ArrayList();
        for (Tile t : (ArrayList<Tile>) tiles) {
            Object[] o = {t.getPoint(), t.getType()};
            infoList.add(o);
        }

        return infoList;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Object[] ss : getTileInfo()) {
            string.append(Arrays.toString(ss));
            string.append("\n");
        }
        return string.toString();
    }
    
    /**
     * Set all tiles on the board 
     * @param tiles in a list
     */
    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }
    
    /**
     * Serialize and save the tiles to a .ser file for loading later
     */
    public void saveTiles()
    {
        ArrayList<Tile> list = new ArrayList<>();
        for (Tile t : tiles) list.add(t);
        FileHelper.write(list, "save.ser");
    }
    
}
