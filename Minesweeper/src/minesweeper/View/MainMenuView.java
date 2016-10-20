/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.View;

import java.util.Arrays;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.*;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import static javafx.application.Application.launch;
import javafx.scene.control.ComboBox;
import minesweeper.Model.Difficulty;

/**
 *
 * @author Viggo
 */
public class MainMenuView extends GameViewSuper {
    public VBox root;
    public final Button resumeButton;
    public final Button settingsButton;
    public final Button rulesButton;
    private ComboBox<String> settings;
    public MainMenuView()
    {
        super();
        resumeButton = new Button();
        settingsButton = new Button();
        rulesButton = new Button();
        this.buttonPaneWidth = 70.0;
        settings = new ComboBox<>();
        
        
        settings.getItems().setAll(Arrays.toString(Difficulty.values()).split(","));
    }
    
    public void update(Stage primaryStage)
    {
        
        Label title = new Label("Minesweeper");
        title.setFont(new Font("Helvetica", 30));
        title.setPadding(new Insets(5,5,20,5));
        
        resumeButton.setAlignment(Pos.CENTER);
        resumeButton.setText("Play");
        settingsButton.setText("Settings");
        rulesButton.setText("Rules");
        
        resumeButton.setMaxWidth(buttonWidth);
        settingsButton.setMaxWidth(buttonWidth);
        rulesButton.setMaxWidth(buttonWidth);
        
        buttonPane.setMaxWidth(buttonPaneWidth);
        buttonPane.getChildren().addAll(resumeButton,settings,rulesButton);
        
        root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(title, buttonPane);
        
        scene = new Scene(root, 200, 200);
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
