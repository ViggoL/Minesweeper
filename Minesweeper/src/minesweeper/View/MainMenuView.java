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
public class MainMenuView {

    public Button playButton;
    public Button settingsButton;
    public Button rulesButton;
    public VBox buttonPane;
    double buttonWidth;
    double spacing;

    public MainMenuView()
    {
        playButton = new Button();
        settingsButton = new Button();
        rulesButton = new Button();
        buttonPane = new VBox();
        buttonWidth = Double.MAX_VALUE;
        spacing = 10.0;
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
        
        buttonPane.setSpacing(spacing);
        buttonPane.setMaxWidth(70);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(new GameButton(70, ButtonEnum.PLAY).getButton(),playButton,settingsButton,rulesButton);
        
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
