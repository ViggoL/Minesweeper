/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import minesweeper.View.GameView;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import minesweeper.Model.Minesweeper;
import minesweeper.View.MainMenuView;

/**
 *
 * @author Viggo
 */
public class MainMenuController {
    private final MainMenuView view;
    private Stage stage, primaryStage;
    private Menu menu1, menu2, menu3;
    private MenuBar menuBar;
    private BorderPane gameFrame;
    public MainMenuController(MainMenuView view, Stage stage)
    {
        this.view = view;
        this.stage = stage;
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
        
        view.settingsButton.setOnMouseClicked((Event event) -> {
            settingsButtonClicked();
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
        stage.close();
        
        // The observable-observer initialization
        // the observers are added in methods
        Minesweeper model = new Minesweeper();
        GameView viewer = new GameView(model);
        model.timer.addObserver(viewer);

        // force a call to all observers
        model.pause();

        // control for user input
        //GameControllers buttonPane = new GameControllers(model);
        
        /* GUI initialization 
        
        double buttonPaneWidth = 20.0;
        
        menu1 = new Menu("File");
        menu2 = new Menu("Settings");
        menu3 = new Menu("Help");
        menuBar = new MenuBar();
        
        gameFrame = new BorderPane();
        GridController grid = new GridController(model);
        
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        
        gameFrame.setLeft(buttonPane);
        gameFrame.setTop(menuBar);
        gameFrame.setCenter(grid);
        
        Box tempV = new Box();
        tempV.setWidth(buttonPaneWidth);
        gameFrame.setRight(tempV);
        
        tempV = new Box();
        tempV.setHeight(buttonPaneWidth);
        gameFrame.setBottom(tempV);*/
        
        primaryStage = new Stage();
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(viewer.scene);
        primaryStage.setResizable(false);

        primaryStage.show();
        primaryStage.centerOnScreen();
        
    }
    public void playButtonClickedRight()
    {
        view.resumeButton.setText("Don't play!");
    }
    public void settingsButtonClicked(){
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Settings dialog");
        dialog.setContentText("Settings view will show here");
        dialog.showAndWait();
    }
    public void rulesButtonClicked()
    {
        Alert dialog = new Alert(AlertType.INFORMATION);
        dialog.setTitle("Rules dialogue");
        dialog.setContentText("Rules will show in a non-alert window");
        dialog.showAndWait();
    }
    
}
