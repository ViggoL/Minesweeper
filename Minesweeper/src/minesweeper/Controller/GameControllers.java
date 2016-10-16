/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;
import minesweeper.View.ClockView;
import minesweeper.View.GameView;
import minesweeper.View.TimeLabel;

/**
 *
 * @author Viggo
 */
public class GameControllers extends VBox {
    private Minesweeper game;
    private final Button rulesButton;
    private final Button resumeButton;
    private final Button pauseButton;
    private HBox timeBox;
    private TimeLabel time;
    
    public GameControllers(Minesweeper game)
    {
        this.game = game;
        double buttonWidth = 20;

        pauseButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PAUSE).getButton();
        rulesButton = new GameButton(buttonWidth, GameButton.ButtonEnum.HELP).getButton();
        resumeButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PLAY).getButton();
        
        time = new TimeLabel("Time: \n" + Integer.toString(game.getTime()) + " seconds");
        timeBox.getChildren().add(time);
        timeBox.setAlignment(Pos.BASELINE_CENTER);
         
        resumeButton.setOnMouseClicked(this::ResumeButtonClicked);
        pauseButton.setOnMouseClicked(this::PauseButtonClicked);
        rulesButton.setOnMouseClicked(this::RulesButtonClicked);
        timeBox.setOnMouseClicked(this::TimePaneClicked);
        
        this.getChildren().addAll(resumeButton,rulesButton,timeBox);
    }
    
    

    public void ResumeButtonClicked(Event event)
    {
        game.resume();
    }
    public void PauseButtonClicked(Event event)
    {
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
        Thread thread = new Thread(new ClockView(game.timer).start());
    }
    public void TileClicked(Event event){
        System.out.println("I'm in!!");
        if(!game.isPaused()) {
            game.resume();
        }
        Object n = event.getSource();
        Button b = (Button) event.getSource();
        System.out.println(n.getClass());
    }
}
