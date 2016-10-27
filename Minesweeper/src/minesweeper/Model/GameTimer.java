/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.util.Observable;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Represents an observable game timer during playing the game
 * @author Viggo
 */
public class GameTimer extends Observable {
    
    private boolean ticking = true;
    private Timer timer;
    private int interval = 1000;
    private int seconds = 0;
    
    /**
     * Creates a new timer without starting it
     */
    public GameTimer() {
        timer = new Timer();
        
    }
    
    /**
     * Starts the timers
     */
    public void startTimer()
    {
        ticking = true;
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                if (!ticking) return;
                tick();
            }
            
        }, 0, interval);
        
    }
    
    /**
     * Increase seconds by 1
     */
    private void tick()
    {
        seconds++;
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers(seconds);
    }
    
    public void stopTimer()
    {
        ticking = false;
        
        this.setChanged();
        this.notifyObservers(seconds);
    }
    
    public void resumeTimer(){
        ticking = true;
        
        this.setChanged();
        this.notifyObservers(seconds);
    }

    /**
     * Get the amount of seconds that has passed
     * @return seconds
     */
    public int getSeconds()
    {
        return seconds;
    }
    
    /**
     * Get whether the timer is ticking or not
     * @return boolean
     */
    public boolean isTicking(){
        return ticking;
    }

    /**
     * Returns the seconds in string form
     * @return String
     */
    public String toString(){
        return Integer.toString(seconds);
    }

}
