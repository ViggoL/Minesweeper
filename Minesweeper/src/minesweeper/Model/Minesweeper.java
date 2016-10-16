package minesweeper.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import java.util.Observable;


/**
 *
 * @author Viggo
 */
public class Minesweeper extends Observable{
    private boolean paused;
    public Board board;
    public GameTimer timer;
    private Settings settings;
    
    public Minesweeper() {
        super();
        this.paused = false;
        board = new Board();
        settings = new Settings();
        timer = new GameTimer(); 
        
    }
    
    public Minesweeper(Difficulty diff){
        this();
        board = new Board(diff);
    }

    public List<Tile> getBoardTiles(){
        return board.getTiles();
    }
    
    public void pause(){
        timer.stopTimer();
        this.paused = true;
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public void resume(){
        timer.startTimer();
        this.paused = false;
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public int getTime(){
        return timer.getSeconds();
    }
    
    public void startNewGame(){

        board = new Board(settings.getDifficulty());
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }
    
    public boolean isPaused(){
        return this.paused;
    }
}