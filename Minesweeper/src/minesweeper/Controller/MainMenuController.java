/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import minesweeper.View.GameView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import minesweeper.Model.Board;
import minesweeper.Model.Minesweeper;
import minesweeper.View.MainMenuView;

/**
 *
 * @author Viggo
 */
public class MainMenuController {
    private MainMenuView view;
    private Stage stage;
    public MainMenuController(MainMenuView view, Stage stage)
    {
        this.view = view;
        this.stage = stage;
        view.resumeButton.setOnMouseClicked((Event event) -> {
            MouseEvent in;
            if (event instanceof MouseEvent) {
                in = (MouseEvent)event;
                if (in.getButton() == MouseButton.PRIMARY) playButtonSelected();
                else if (in.getButton() == MouseButton.SECONDARY) playButtonClickedRight();
            }
        });
        view.resumeButton.setOnKeyPressed((Event event) -> {
            KeyEvent key;
            if (event instanceof KeyEvent){
                key = (KeyEvent) event;
                if (key.getCode().equals(KeyCode.ENTER))
                    playButtonSelected();
            }
        });
        
        view.settingsButton.setOnMouseClicked((Event event) -> {
            settingsButtonClicked();
        });
        
        view.rulesButton.setOnMouseClicked((Event event) -> {
            MouseEvent mouse;
            if (event instanceof MouseEvent){
                mouse = (MouseEvent) event;
                if(mouse.getButton() == MouseButton.PRIMARY)
                    rulesButtonClicked();
            }
        });
    }
    
    public void playButtonSelected(){
        stage.close();
        view.root.setVisible(false);
        GameView gameView = new GameView();
        //GameController gameController = new GameController(gameView);
        Minesweeper game = new Minesweeper();
        game.addObserver(gameView);
        game.timer.addObserver(gameView);
        gameView.update(game, new Stage());
        
    }
    public void playButtonClickedRight()
    {
        view.resumeButton.setText("Don't play!");
    }
    public void settingsButtonClicked(){
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Settings dialog");
        dialog.setContentText("Settings view will show here");
        dialog.showAndWait();
    }
    public void rulesButtonClicked()
    {
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Rules dialogue");
        dialog.setContentText("Rules will show in a non-alert window");
        dialog.showAndWait();
    }
    
}
