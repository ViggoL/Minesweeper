/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

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
    public boolean isCovered()
    {
        return covered;
    }
    public void setCovered(boolean covered)
    {
        this.covered = covered;
    }
}

enum TileType
{
    BOMB, FLAG, NUMBER, EMPTY
}
