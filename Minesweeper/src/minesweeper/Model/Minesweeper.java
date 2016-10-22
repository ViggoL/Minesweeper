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
 *
 * @author Viggo
 */
public class Minesweeper extends Observable{
    private boolean paused;
    public Board board;
    public GameTimer timer;
    private Settings settings;
    private boolean gameOver;
    
    public Minesweeper() {
        super();
        this.paused = false;
        board = new Board(Difficulty.EASY);
        settings = new Settings();
        timer = new GameTimer(); 
        gameOver = false;
        
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
        timer.resumeTimer();
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

    public void startNewGame(Difficulty difficulty) {
        board = new Board(difficulty);
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }
    
    public Difficulty getDifficultySetting(){
        return settings.getDifficulty();
    }
    
    public void setDifficulty(Difficulty diff){
        settings.setDifficulty(diff);
    }

    public void unCoverThemAll() {
        for(Tile t: board.getTiles()) board.uncover(t);
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
    }

    public void setGameOver() {
        gameOver = true;
        timer.stopTimer();
        
        // The model has changed, notify observers!
        this.setChanged();
        this.notifyObservers();
        
    }

    public boolean isGameOver() {
        return gameOver;
    }

}