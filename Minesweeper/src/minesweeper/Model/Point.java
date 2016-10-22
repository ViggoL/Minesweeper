/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.util.Observable;

/**
 *
 * @author Viggo
 */
public class Point extends Observable {
    public final int x, y;
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString()
    {
        return "[" + x + ", " + y + "]";
    }
    
    @Override 
    public boolean equals(Object other) throws ClassCastException
    {
        try {
            return (this.x == ((Point)other).x && this.y == ((Point)other).y);
        } catch (NullPointerException e) {
            System.out.println("Point was null!");
            return false;
        }
    }
}
