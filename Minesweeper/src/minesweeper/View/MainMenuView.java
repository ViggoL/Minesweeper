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
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
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
    public VBox buttonPane;
    public Pane emptyPane1, emptyPane2;
    
    public MainMenuView()
    {
        playButton = new Button();
        settingsButton = new Button();
        rulesButton = new Button();
        buttonPane = new VBox();
        emptyPane1 = new Pane();
        emptyPane2 = new Pane();
    }
    
    public void update(Stage primaryStage)
    {
        Insets buttonInsets = new Insets(5);
        
        Label title = new Label("Minesweeper");
        title.setFont(new Font("Helvetica", 30));
        title.setPadding(new Insets(5,5,20,5));
        
        playButton.setText("Play");
        playButton.setAlignment(Pos.CENTER);
        
        settingsButton.setText("Settings");
        
        rulesButton.setText("Rules");
        
        emptyPane1.setPadding(buttonInsets);
        emptyPane2.setPadding(buttonInsets);
        
        buttonPane.getChildren().addAll(playButton, emptyPane1, settingsButton, emptyPane2 , rulesButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(buttonInsets);
        
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(title, buttonPane);
        
        Scene scene = new Scene(root, 200, 200);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    

}
