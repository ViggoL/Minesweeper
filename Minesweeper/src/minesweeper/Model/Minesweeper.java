package minesweeper.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import minesweeper.Controller.MainMenuController;
import minesweeper.Controller.NewGame;
import minesweeper.View.MainMenuView;


/**
 * Represents an observable Minesweeper game
 * @author Viggo
 */
public class Minesweeper extends Observable{
    public Board board;
    private GameTimer timer;
    private Settings settings;
    private boolean gameOver;
    
    /**
     * Creates a new game with difficulty EASY
     */
    public Minesweeper() {
        super();
        board = new Board(Difficulty.EASY);
        settings = new Settings();
        timer = new GameTimer(); 
        gameOver = false;
        
    }
    
    /**
     * Creates a new game with desired difficulty
     * @param diff Difficulty
     */
    public Minesweeper(Difficulty diff){
        this();
        board = new Board(diff);
    }
    
    /**
     * Creates a new game with desired difficulty and save file to load
     * @param diff Difficulty
     * @param filename source path
     */
    public Minesweeper(Difficulty diff, String filename)
    {
        this(diff);
        board.setTiles(FileHelper.read(filename));
    }
    
    /**
     * Returns all the tiles on the board
     * @return List of Tiles
     */
    public List<Tile> getBoardTiles(){
        return board.getTiles();
    }
    
    /**
     * Pause the game
     */
    public void pause(){
        timer.stopTimer();
        
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Resume the game
     */
    public void resume(){
        timer.resumeTimer();
        
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Get the seconds that has passed since the game started, excluding paused periods
     * @return time in seconds
     */
    public int getTime(){
        return timer.getSeconds();
    }
    /**
     * Starts the game timer
     */
    public void startTime(){
        timer.startTimer();
    }
    /**
     * Starts a new game with already set difficulty
     */
    public void startNewGame(){
        board = new Board(settings.getDifficulty());
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }
    /**
     * Get whether the game timer is paused
     * @return boolean
     */
    public boolean isPaused(){
        return timer.isTicking();
    }
    /**
     * Starts a new game with desired difficulty
     * @param difficulty Difficulty
     */
    public void startNewGame(Difficulty difficulty) {
        board = new Board(difficulty);
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Gets the difficulty setting of the game
     * @return Difficulty
     */
    public Difficulty getDifficultySetting(){
        return settings.getDifficulty();
    }
    /**
     * Sets the difficulty of the game
     * @param diff Difficulty
     */
    public void setDifficulty(Difficulty diff){
        settings.setDifficulty(diff);
    }

    /**
     * Uncovers all tiles on the board
     */
    public void unCoverThemAll() {
        for(Tile t: board.getTiles()) 
            board.uncover(t);
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }
    /**
     * Sets the game to game over stage
     */
    public void setGameOver() {
        synchronized (timer){
        gameOver = true;
        timer.stopTimer();
        }    
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
        
    }
    /**
     * Returns whether the game is over or not
     * @return boolean
     */
    public boolean isGameOver() {
        return gameOver;
    }
    /**
     * Returns the timer of the game
     * @return timer reference
     */
    public GameTimer getTimer(){
        return timer;
    }
    
    

}