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
 *
 * @author Viggo
 */
public class GameTimer extends Observable {
    
    private boolean ticking = true;
    private Timer timer;
    private int interval = 1000;
    private int seconds = 0;
    
    public GameTimer() {
        timer = new Timer();
        
    }
    
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

    public int getSeconds()
    {
        return seconds;
    }
    
    public boolean isTicking(){
        return ticking;
    }

    public String toString(){
        return Integer.toString(seconds);
    }

}
