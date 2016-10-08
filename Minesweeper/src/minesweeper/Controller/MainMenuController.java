/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import minesweeper.View.MainMenuView;

/**
 *
 * @author Viggo
 */
public class MainMenuController {
    MainMenuView view;
    public MainMenuController(MainMenuView view)
    {
        this.view = view;
    }
    public void playButtonClicked()
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
