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
public class Point {
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
    
    public boolean equals(Point other)
    {
        try {
            return (this.x == other.x && this.y == other.y);
        } catch (NullPointerException e) {
            System.out.println("Point was null!");
            return false;
        }
    }
}
