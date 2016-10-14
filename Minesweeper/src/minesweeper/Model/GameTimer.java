/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Model;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Viggo
 */
public class GameTimer extends Observable implements Runnable {
    
    Timer timer;
    int interval = 1000;
    int seconds = 0;
    
    public GameTimer() {

    }
    
    public void startTimer()
    {
                timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                seconds++;
            }
            
        }, interval);
    }
    
    public void stopTimer()
    {
        seconds = 0;
    }

    public int getSeconds()
    {
        return seconds;
    }
    
    @Override
    public void run() {
        for (;;)
        {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
            seconds++;
        }
    }
    
    
    
    public void start() {
        run();
    }
}
