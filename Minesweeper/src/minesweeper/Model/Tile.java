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
import javafx.scene.layout.GridPane;

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
        if(this.covered){
            this.covered = false;
            System.out.println("Uncover tile and search surroundings!");

            // The model has changed, notify observers!
            this.setChanged();
            this.notifyObservers();
        }
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
        int [] XYmax = board.getXYboundary();
        ArrayList<Tile> tiles = new ArrayList();
        if(point.x>0){
            tiles.add(board.getTile(new Point(point.x - 1, point.y)));
            if(point.y>0){
                tiles.add(board.getTile(new Point(point.x - 1, point.y - 1)));
                if(point.y<XYmax[1]) 
                    tiles.add(board.getTile(new Point(point.x - 1, point.y + 1)));
            }
        }
        if(point.y>0){
            tiles.add(board.getTile(new Point(point.x, point.y - 1)));
            if(point.y<XYmax[1]){
                tiles.add(board.getTile(new Point(point.x, point.y + 1)));
                if(point.x<XYmax[0]) 
                    tiles.add(board.getTile(new Point(point.x + 1, point.y - 1)));
            }
        }
        if(point.x<XYmax[0]){
            tiles.add(board.getTile(new Point(point.x + 1, point.y)));
            if(point.y<XYmax[1])
                tiles.add(board.getTile(new Point(point.x + 1, point.y + 1)));
        }
        
        return tiles;
    }
    
    public static int bombCount(List<Tile> tiles)
    {
        int i = 0;
        for (Tile t : tiles)
        {
            if (t == null) continue;
            if (t.getType() == TileType.BOMB) i++;
        }
        return i;
    }
}

