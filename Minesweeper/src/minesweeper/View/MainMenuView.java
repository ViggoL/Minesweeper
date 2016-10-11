/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.View;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.*;
import javafx.scene.text.Font;
import minesweeper.Controller.MainMenuController;

/**
 *
 * @author Viggo
 */
public class MainMenuView {

    public Button playButton;
    public Button settingsButton;
    public Button rulesButton;
    
    public MainMenuView()
    {
        playButton = new Button();
        settingsButton = new Button();
        rulesButton = new Button();
    }
    
    public void update(Stage primaryStage)
    {
        Label title = new Label("Minesweeper");
        title.setFont(new Font("Helvetica", 30));
        
        
        playButton.setText("Play");
        playButton.setAlignment(Pos.CENTER);
        settingsButton.setText("Settings");
        rulesButton.setText("Rules");
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(title, playButton, settingsButton, rulesButton);
        
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

}
