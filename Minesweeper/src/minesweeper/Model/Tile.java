/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.util.List;
import java.util.Observable;

/**
 *
 * @author Viggo
 */
public class Tile extends Observable{

    private Point point;
    private TileType type;
    private int number;
    private boolean covered;
    private boolean flagged;
    public Tile(Point point, TileType type)
    {
        this.covered = true;
        this.type = type;
        this.point = point;
    }
    public Point getPoint()
    {
        return this.point;
    }
    public int getX(){
        return point.x;
    }
    public int getY(){
        return point.y;
    }
    public TileType getType()
    {
        return this.type;
    }
    public void setType(TileType type)
    {
        this.type = type;
        // The model has changed, notify observers!
        this.setChanged();
        
        
        this.notifyObservers();
        
        
        
        
    }
    public void setNumber(int number)
    {
        this.type = TileType.NUMBER;
        this.number = number;
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }

    
    
    public int getNumber()
    {
        return this.number;
    }
    
    public boolean isCovered()
    {
        return covered;
    }

    void setCovered(boolean covered) {
        this.covered = covered;
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }
    public boolean isFlagged()
    {
        return this.flagged;
    }
}

