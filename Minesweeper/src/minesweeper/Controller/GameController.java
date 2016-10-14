/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
        
        gameView.playButton.setOnMouseClicked(new EventHandler(){
            @Override
            public void handle(Event event) {
                PlayButtonClicked(event);
            } 
        });
        gameView.pauseButton.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                PauseButtonClicked(event);
            }
            
        });
        gameView.rulesButton.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                RulesButtonClicked(event)
            }
            
        }
        );
    }
    public void PlayButtonClicked(Event event)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
