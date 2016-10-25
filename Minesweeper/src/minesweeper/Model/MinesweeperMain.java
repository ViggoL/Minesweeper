package minesweeper.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.stage.Stage;
import minesweeper.Controller.MainMenuController;
import minesweeper.View.MainMenuView;

/**
 *
 * @author Viggo
 */
public class MinesweeperMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        MainMenuView view = new MainMenuView();
        new MainMenuController(view, primaryStage);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}