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
public class Timer extends Observable implements Runnable {
    
    private int seconds;
    
    public Timer() {
        
    }
    
    public void startTimer()
    {
        
    }
    
    public void stopTimer()
    {
        seconds = 0;
    }

    
    
    @Override
    public void run() {
        for (;;)
        {
            seconds++;
        }
    }
    
    public void start() {
        
    }
}
