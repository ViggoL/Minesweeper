package minesweeper.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import minesweeper.Controller.MainMenuController;
import minesweeper.View.GameView;
import minesweeper.View.MainMenuView;

/**
 *
 * @author Viggo
 */
public class MinesweeperMain extends Application {
    private boolean paused;
    private Board board;
    
    @Override
    public void start(Stage primaryStage) {
        paused = false;
        
        // The observable-observer initialization
        Board board = new Board();
        Minesweeper game = new Minesweeper();
        GameView gameView = new GameView();
        
        game.addObserver(gameView);
        
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