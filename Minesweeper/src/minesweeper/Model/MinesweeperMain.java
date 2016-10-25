package minesweeper.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import minesweeper.View.ClockView;
import javafx.application.Application;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Box;
import javafx.stage.Stage;
import minesweeper.Controller.GameControllers;
import minesweeper.Controller.GridController;
import minesweeper.Controller.MainMenuController;
import minesweeper.View.GameView;
import minesweeper.View.MainMenuView;
import minesweeper.View.TimeLabel;

/**
 *
 * @author Viggo
 */
public class MinesweeperMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        MainMenuView view = new MainMenuView();
        new MainMenuController(view, primaryStage);
        view.update(primaryStage);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}