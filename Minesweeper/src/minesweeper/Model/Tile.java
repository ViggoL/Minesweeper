/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

/**
 * Represents a tile that can be placed on the board
 * @author Viggo
 */
public class Tile extends Observable implements Serializable {

    private Point point;
    private TileType type;
    private int number;
    private boolean covered;
    private boolean flagged;
    /**
     * Creates a new tile with desired point and type
     * @param point
     * @param type 
     */
    public Tile(Point point, TileType type)
    {
        this.covered = true;
        this.type = type;
        this.point = point;
    }
    /**
     * Returns the tile's Point 
     * @return 
     */
    public Point getPoint()
    {
        return this.point;
    }
    /**
     * Returns the tile's point's X-value
     * @return 
     */
    public int getX(){
        return point.x;
    }
    /**
     * Returns the tile's point's Y-value
     * @return 
     */
    public int getY(){
        return point.y;
    }
    /**
     * Returns the tile's TileType
     * @return 
     */
    public TileType getType()
    {
        return this.type;
    }
    /**
     * Sets the TileType of the tile
     * @param type 
     */
    public void setType(TileType type)
    {
        this.type = type;
        // The model has changed, notify observers!
        this.setChanged();
        
        
        this.notifyObservers();
        
        
        
        
    }
    /**
     * Sets the number of the tile and changes the TileType to NUMBER
     * @param number 
     */
    public void setNumber(int number)
    {
        this.type = TileType.NUMBER;
        this.number = number;
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }

    
    /**
     * Returns the number of the tile
     * @return 
     */
    public int getNumber()
    {
        return this.number;
    }
    /**
     * Returns whether the tile is covered or not
     * @return 
     */
    public boolean isCovered()
    {
        return covered;
    }

    /**
     * Sets the tile to be covered or not
     * @param covered 
     */
    void setCovered(boolean covered) {
        this.covered = covered;
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Sets the tile to be flagged or not
     * @param flagged 
     */
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }
    /**
     * Returns whether the tile is flagged or not
     * @return 
     */
    public boolean isFlagged()
    {
        return this.flagged;
    }
}

