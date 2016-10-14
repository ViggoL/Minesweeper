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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import minesweeper.View.MainMenuView;

/**
 *
 * @author Viggo
 */
public class MainMenuController {
    private MainMenuView view;
    public MainMenuController(MainMenuView view)
    {
        this.view = view;
        
        view.playButton.setOnMouseClicked(new EventHandler()
        {
            @Override
            public void handle(Event event) {
                MouseEvent in;
                if (event instanceof MouseEvent) {
                    in = (MouseEvent)event;
                    if (in.getButton() == MouseButton.SECONDARY) playButtonClickedRight();
                }
                
                
              
            }
        });
        
        view.settingsButton.setOnMouseClicked(new EventHandler(){
           @Override
           public void handle(Event event) {
               settingsButtonClicked();
           }
        });
        
        view.rulesButton.setOnMouseClicked(new EventHandler()
        {
            @Override
            public void handle(Event event) {
                MouseEvent mouse;
                KeyEvent enter;
                if (event instanceof MouseEvent){
                    
                }
                    
            }
        });
    }
    public void playButtonClickedRight()
    {
        view.playButton.setText("Don't play!");
    }
    public void settingsButtonClicked(){
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Settings dialog");
        dialog.setContentText("Settings view will show here");
        dialog.showAndWait();
    }
    public void rulesButtonClicked()
    {
        
    }
    
}
