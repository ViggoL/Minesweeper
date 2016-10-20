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
import javafx.scene.layout.StackPane;
import minesweeper.Model.Difficulty;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.scene.control.Menu;
import javafx.scene.layout.HBox;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import static javafx.application.Application.launch;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Viggo
 */
public class MainMenuView extends GameViewSuper {
    public VBox root;
    public final Button resumeButton;
    public final Button settingsButton;
    public final Button rulesButton;
    public final Menu settingsMenu;
    public final MenuBar menuBar;
    private Label title;
    public MainMenuView()
    {
        super();
        resumeButton = new Button();
        settingsButton = new Button();
        rulesButton = new Button();
        this.buttonPaneWidth = 90.0;
        
        settingsMenu = new Menu(" Settings");
        
        menuBar = new MenuBar();

        for(Difficulty d: Difficulty.values()){
            MenuItem item = new MenuItem(d.toString());
            settingsMenu.getItems().add(item);
        }
        
        menuBar.getMenus().add(settingsMenu);
        menuBar.useSystemMenuBarProperty();
    }
    
    public void update(Stage primaryStage)
    {
        
        title = new Label("Minesweeper");
        title.setFont(new Font("Helvetica", 30));
        title.setPadding(new Insets(5,5,20,5));
        
        resumeButton.setAlignment(Pos.CENTER);
        settingsButton.setAlignment(Pos.CENTER);
        rulesButton.setAlignment(Pos.CENTER);
        
        resumeButton.setText("Play");
        settingsButton.setText("Settings");
        rulesButton.setText("Rules");
        
        resumeButton.setMaxWidth(buttonWidth);
        settingsButton.setMaxWidth(buttonWidth);
        rulesButton.setMaxWidth(buttonWidth);

        
        buttonPane.setMaxWidth(buttonPaneWidth);
        buttonPane.getChildren().addAll(resumeButton,rulesButton,menuBar);
        
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
