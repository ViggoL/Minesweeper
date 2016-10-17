/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;
import minesweeper.View.ClockView;


/**
 *
 * @author Viggo
 */
public class GameTimer extends Observable {
    
    boolean ticking = true;
    Timer timer;
    int interval = 1000;
    int seconds = 0;
    
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
        this.notifyObservers();
    }
    
    public void stopTimer()
    {
        //seconds = 0;
        ticking = false;
        
        this.setChanged();
        this.notifyObservers();
    }

    public int getSeconds()
    {
        return seconds;
    }
    
    public boolean isTicking(){
        return ticking;
    }
}
