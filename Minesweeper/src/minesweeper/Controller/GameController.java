/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import minesweeper.View.GameView;

/**
 *
 * @author Viggo
 */
public class GameController {
    private GameView gameView;
    private Button pause,resume,rules;
    private BorderPane frame;
    private VBox side;
    private Node center;
    private Label time;
    private HBox timeBox;
    
    public GameController(GameView gameView)
    {
        this.gameView = gameView;
        resume = gameView.resumeButton;
        pause = gameView.pauseButton;
        rules = gameView.rulesButton;
        frame = gameView.gameFrame;
        side = gameView.buttonPane;
        center = gameView.grid;
           
        resume.setOnMouseClicked(this::ResumeButtonClicked);
        pause.setOnMouseClicked(this::PauseButtonClicked);
        rules.setOnMouseClicked(this::RulesButtonClicked);
        center.setOnMouseClicked(this::CentrePaneClicked);
    }
    public void ResumeButtonClicked(Event event)
    {
        side.getChildren().clear();
        side.getChildren().addAll(pause,rules);
        frame.setCenter(center);
        center.setVisible(true);
        gameView.game.resume();
    }
    public void PauseButtonClicked(Event event)
    {
        time = new Label("Time: " + Integer.toString(gameView.game.getTime()));
        timeBox = new HBox(time);
        timeBox.setAlignment(Pos.BASELINE_CENTER);
        timeBox.setPadding(new Insets(20));
        timeBox.setOnMouseClicked(this::CentrePaneClicked);
        
        
        center.setVisible(false);
        
        side.setSpacing(10);
        side.getChildren().clear();
        side.getChildren().addAll(resume,rules);
        gameView.gameFrame.setCenter(timeBox);
    }
    public void RulesButtonClicked(Event event)
    {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Rules dialogue");
        dialog.setContentText("Rules will show in a non-alert window");
        dialog.showAndWait();
    }
    public void CentrePaneClicked(Event event){
        gameView.gameFrame.setCenter(new Label("This is where the action Happens"));
    }
}
