/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;


import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import minesweeper.Model.Minesweeper;
import minesweeper.View.ClockView;
import minesweeper.View.GameView;
import minesweeper.View.RulesView;

/**
 *
 * @author Viggo
 */
public class GameControllers extends VBox {
    private final Minesweeper game;
    private final Button rulesButton, resumeButton, pauseButton;
    private final Insets inset;
    private final Button timeButton;
    private final GameView gameView;
    
    public GameControllers(Minesweeper game, GameView gameView)
    {
        this.game = game;
        this.gameView = gameView;
        double buttonWidth = 20;

        pauseButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PAUSE).getButton();
        rulesButton = new GameButton(buttonWidth, GameButton.ButtonEnum.HELP).getButton();
        resumeButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PLAY).getButton();
        timeButton = new GameButton(buttonWidth, GameButton.ButtonEnum.TIME).getButton();
 
        inset = new Insets(5);
        this.setPadding(inset);
        this.setSpacing(10);
        this.setAlignment(Pos.BASELINE_CENTER);
         
        resumeButton.setOnMouseClicked(this::ResumeButtonClicked);
        pauseButton.setOnMouseClicked(this::PauseButtonClicked);
        rulesButton.setOnMouseClicked(this::RulesButtonClicked);
        timeButton.setOnMouseClicked(this::TimePaneClicked);
        
        this.getChildren().addAll(pauseButton,rulesButton,timeButton);
    }
    
    

    public void ResumeButtonClicked(Event event)
    {
        this.getChildren().set(0, pauseButton);
        game.resume();
    }
    public void PauseButtonClicked(Event event)
    {
        this.getChildren().set(0, resumeButton);
        game.pause();
        
    }
    public void RulesButtonClicked(Event event)
    {
        Runnable rules = new RulesView();
        Thread window = new Thread(rules);
        window.start();
    }
    public void TimePaneClicked(Event event){
        gameView.getClockView().showClock();
    }
}
