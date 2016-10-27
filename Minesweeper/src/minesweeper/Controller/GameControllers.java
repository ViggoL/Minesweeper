/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;


import javafx.application.Platform;
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
 * The Game controllers
 * @author Viggo
 */
public class GameControllers extends VBox {
    private final Minesweeper game;
    private final Button rulesButton, resumeButton, pauseButton;
    private final Insets inset;
    private final Button timeButton;
    private final GameView gameView;
    
    /**
     * Creates a new class containing the Game controllers
     * @param game The game to handle
     * @param gameView The view to handle
     */
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
    
    
    /**
     * Called whenever the user pressed the resume button
     * @param event 
     */
    public void ResumeButtonClicked(Event event)
    {
        this.getChildren().set(0, pauseButton);
        if(!game.isGameOver()) {
            game.resume();
        }
        else new NewGame(gameView.gameStage,game.getDifficultySetting());
    }
    /**
     * Called whenever the user pressed the pause button
     * @param event 
     */
    public void PauseButtonClicked(Event event)
    {
        this.getChildren().set(0, resumeButton);
        game.pause();
        
    }
    /**
     * Called whenever the user pressed the rules button
     * @param event 
     */
    public void RulesButtonClicked(Event event)
    {
        Platform.runLater(new RulesView());
    }
    /**
     * Called whenever the user clicks the Time button
     * @param event 
     */
    public void TimePaneClicked(Event event){
        gameView.getClockView().showClock();
    }
}
