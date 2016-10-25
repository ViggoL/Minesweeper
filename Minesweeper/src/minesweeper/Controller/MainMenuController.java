/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;


import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import minesweeper.View.MainMenuView;
import minesweeper.View.RulesView;

/**
 *
 * @author Viggo
 */
public class MainMenuController {
    private final MainMenuView view;
    private Stage stage, primaryStage;
    
    public MainMenuController(MainMenuView view, Stage stage)
    {
        this.view = view;
        this.stage = stage;
        view.update(stage);
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
        NewGame newGame = new NewGame(stage,view.getDifficulty()); 
    }
    public void playButtonClickedRight()
    {
        view.resumeButton.setText("Don't play!");
    }
    
    public void rulesButtonClicked()
    {
        RulesView.showRules();
    }
}
