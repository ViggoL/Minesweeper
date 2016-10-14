/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.*;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import minesweeper.View.GameButton.ButtonEnum;

/**
 *
 * @author Viggo
 */
public class MainMenuView extends GameViewButtons {

    double buttonWidth;

    public MainMenuView()
    {
        super();
        playButton = new Button();
        settingsButton = new Button();
        rulesButton = new Button();
        buttonPaneWidth = 200.0;
    }
    
    public void update(Stage primaryStage)
    {
        
        Label title = new Label("Minesweeper");
        title.setFont(new Font("Helvetica", 30));
        title.setPadding(new Insets(5,5,20,5));
        
        playButton.setAlignment(Pos.CENTER);
        playButton.setText("Play");
        settingsButton.setText("Settings");
        rulesButton.setText("Rules");
        
        playButton.setMaxWidth(buttonWidth);
        settingsButton.setMaxWidth(buttonWidth);
        rulesButton.setMaxWidth(buttonWidth);
        
        buttonPane.setMaxWidth(Double.MAX_VALUE);
        buttonPane.getChildren().addAll(playButton,settingsButton,rulesButton);
        
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(title, buttonPane);
        
        Scene scene = new Scene(root, 200, 260);

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    

}
