/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.io.Serializable;
import java.util.Observable;

/**
 * Represents a Point with X and Y coordinates
 * @author Viggo
 */
public class Point extends Observable implements Serializable {
    public final int x, y;
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    /**
     * 
     * @return X and Y value of the point 
     */
    @Override
    public String toString()
    {
        return "[" + x + ", " + y + "]";
    }
    
    /**
     * Compares the X and Y value of another point
     * @param other
     * @return True if the coordinates of the two points are equal
     * @throws ClassCastException 
     */
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
