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
        
        this.setChanged();
        // The model has changed, notify observers!
        if (type == TileType.FLAG){
            
            //four calls to observers lets the four different flag images be displayed
            try{
                this.notifyObservers(1);
                Thread.sleep(10);
                this.notifyObservers(2);
                Thread.sleep(10);
                this.notifyObservers(3);
                Thread.sleep(10);
                this.notifyObservers(4);
                Thread.sleep(10);
        
            } catch (InterruptedException interrupted){
                Thread.currentThread().interrupt();
                System.err.println(interrupted.toString());
                for(StackTraceElement s: Thread.currentThread().getStackTrace()) System.err.println(s.toString());
            }
        }
        
        else this.notifyObservers();
        
        
        
        
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

    void setCovered(boolean covered) {
        this.covered = covered;
        
        this.setChanged();
        // The model has changed, notify observers!
        if (type == TileType.BOMB){
            try{
                this.notifyObservers(1);
                Thread.sleep(10);
                this.setChanged();
                notifyObservers(2);
                Thread.sleep(10);
                this.setChanged();
                notifyObservers(3);
                Thread.sleep(10);
                this.setChanged();
                notifyObservers(4);    
            }
            catch (InterruptedException interrupted){
                Thread.currentThread().interrupt();
                System.err.println(interrupted.toString());
                for(StackTraceElement s: Thread.currentThread().getStackTrace()) System.err.println(s.toString());
            }
        }
        else this.notifyObservers();
    }
}

