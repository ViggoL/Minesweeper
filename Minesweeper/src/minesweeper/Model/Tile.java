/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Viggo
 */
public class Tile {
    private Point point;
    private TileType type;
    private int number;
    private boolean covered;
    public Tile(Point point, TileType type)
    {
        this.covered = false;
        this.type = type;
        this.point = point;
    }
    public Point getPoint()
    {
        return this.point;
    }
    public TileType getType()
    {
        return this.type;
    }
    public void setType(TileType type)
    {
        this.type = type;
    }
    public void setNumber(int number)
    {
        this.type = TileType.NUMBER;
        this.number = number;
    }
    
    public int getNumber()
    {
        return this.number;
    }
    
    public boolean isCovered()
    {
        return covered;
    }
    public void setCovered(boolean covered)
    {
        this.covered = covered;
    }

    public void setSurroundingTiles(Board board)
    {
        if (type != TileType.BOMB) return;
        List<List<Tile>> tiles = board.getTiles();
        
    }

    
    public int getAdjacentMines(Board board)
    {
        List<Tile> surroundingTiles = getSurroundingTiles(board);
        int count = 0;
        for (Tile t : surroundingTiles)
        {
            if (t.getType() == TileType.BOMB) count++;
        }
        return count;
    }
    
    public List<Tile> getSurroundingTiles(Board board)
    {
        Tile tiles[] = new Tile[8];
        List<Tile> tileList = new ArrayList<Tile>();
        tiles[0] = board.getTile(new Point(point.x - 1, point.y));
        tiles[1] = board.getTile(new Point(point.x - 1, point.y - 1));
        tiles[2] = board.getTile(new Point(point.x, point.y - 1));
        tiles[3] = board.getTile(new Point(point.x + 1, point.y - 1));
        tiles[4] = board.getTile(new Point(point.x + 1, point.y));
        tiles[5] = board.getTile(new Point(point.x + 1, point.y + 1));
        tiles[6] = board.getTile(new Point(point.x, point.y + 1));
        tiles[7] = board.getTile(new Point(point.x - 1, point.y + 1));
        for (Tile t : tiles)
        {
            tileList.add(t);
        }
        return tileList;
    }
}

enum TileType
{
    BOMB, FLAG, NUMBER, EMPTY
}
