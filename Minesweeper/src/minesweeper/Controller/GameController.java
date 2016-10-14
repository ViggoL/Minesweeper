/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import minesweeper.View.GameView;

/**
 *
 * @author Viggo
 */
public class GameController {
    private GameView gameView;
    public GameController(GameView gameView)
    {
        this.gameView = gameView;
        
        gameView.resumeButton.setOnMouseClicked(this::ResumeButtonClicked);
        gameView.pauseButton.setOnMouseClicked(this::PauseButtonClicked);
        gameView.rulesButton.setOnMouseClicked(this::RulesButtonClicked);
    }
    public void ResumeButtonClicked(Event event)
    {
        
    }
    public void PauseButtonClicked(Event event)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }
    public void RulesButtonClicked(Event event)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.    
    }
}
