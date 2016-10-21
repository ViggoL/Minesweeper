/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.View;

import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import minesweeper.Model.Difficulty;
import minesweeper.Model.Minesweeper;


/**
 *
 * @author Viggo
 */
public class MainMenuView extends GameViewSuper {
    public VBox root;
    public final Button resumeButton;
    public final Button settingsButton;
    public final Button rulesButton;
    private Label title;
    public MainMenuView()
    {
        super(new Minesweeper());
        game = null;
        resumeButton = new Button();
        settingsButton = new Button();
        rulesButton = new Button();
        this.buttonPaneWidth = 90.0;
        
        for(Difficulty d: Difficulty.values()){
            MenuItem item = new MenuItem(d.toString());
            item.setOnAction(new settingsMenuActionEvent());
            settingsMenu.getItems().add(item);                 
        }
        
        menuBar.getMenus().add(settingsMenu);
        
        
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
    
    private void setDifficulty(Difficulty valueOf) {
        difficulty = valueOf;
    }
    
    public Difficulty getDifficulty(){
        return this.difficulty;
    }
    
    private final class settingsMenuActionEvent implements EventHandler<ActionEvent> {
        
        private settingsMenuActionEvent(){
            handle(new ActionEvent());
        }
        
        @Override
        public void handle(ActionEvent event) {
            Object o = event.getSource();
            if(o instanceof MenuItem){
                MenuItem m = (MenuItem) o;
                setDifficulty(Difficulty.valueOf(m.getText()));    
            }
        };
    }

}
