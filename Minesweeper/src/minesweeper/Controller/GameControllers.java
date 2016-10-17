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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import minesweeper.Model.Minesweeper;
import minesweeper.View.ClockView;
import minesweeper.View.TimeLabel;

/**
 *
 * @author Viggo
 */
public class GameControllers extends VBox {
    private Minesweeper game;
    private final Button rulesButton, resumeButton, pauseButton;
    private HBox timeBox;
    private final TimeLabel time;
    private Insets inset;
    
    public GameControllers(Minesweeper game)
    {
        this.game = game;
        double buttonWidth = 20;

        pauseButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PAUSE).getButton();
        rulesButton = new GameButton(buttonWidth, GameButton.ButtonEnum.HELP).getButton();
        resumeButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PLAY).getButton();
        
        time = new TimeLabel("Time: \n" + Integer.toString(game.getTime()) + " seconds");
        game.addObserver(time);
        
        timeBox = new HBox(time);
        
        //timeBox.getChildren().add(time);
        timeBox.setAlignment(Pos.BASELINE_CENTER);
        this.setAlignment(Pos.BASELINE_LEFT);
        
        inset = new Insets(5);
        time.setPadding(inset);
        this.setPadding(inset);
        
        this.setSpacing(10);
         
        resumeButton.setOnMouseClicked(this::ResumeButtonClicked);
        pauseButton.setOnMouseClicked(this::PauseButtonClicked);
        rulesButton.setOnMouseClicked(this::RulesButtonClicked);
        timeBox.setOnMouseClicked(this::TimePaneClicked);
        
        this.getChildren().addAll(pauseButton,rulesButton,timeBox);
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
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Rules dialogue");
        dialog.setContentText("Rules will show in a non-alert window");
        dialog.showAndWait();
    }
    public void TimePaneClicked(Event event){
        ClockView clock = new ClockView(game.timer);
        clock.start();
        game.timer.addObserver(clock);
    }
}
