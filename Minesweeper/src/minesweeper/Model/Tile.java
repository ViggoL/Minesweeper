/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void setNumber(Board board)
    {
        setNumber(getAdjacentMines(board));
        
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
    public void uncover()
    {
        this.covered = false;
        
    // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }
    
    public int getAdjacentMines(Board board)
    {
        List<Tile> surroundingTiles = getSurroundingTiles(board);
        int count = 0;
        for (Tile t : surroundingTiles)
            if (t.getType() == TileType.BOMB) count++;
        return count;
    }
    
    public List<Tile> getSurroundingTiles(Board board)
    {
        Tile tiles[] = new Tile[8];
        tiles[0] = board.getTile(new Point(point.x - 1, point.y));
        tiles[1] = board.getTile(new Point(point.x - 1, point.y - 1));
        tiles[2] = board.getTile(new Point(point.x, point.y - 1));
        tiles[3] = board.getTile(new Point(point.x + 1, point.y - 1));
        tiles[4] = board.getTile(new Point(point.x + 1, point.y));
        tiles[5] = board.getTile(new Point(point.x + 1, point.y + 1));
        tiles[6] = board.getTile(new Point(point.x, point.y + 1));
        tiles[7] = board.getTile(new Point(point.x - 1, point.y + 1));
        return Arrays.asList(tiles);
    }
}

