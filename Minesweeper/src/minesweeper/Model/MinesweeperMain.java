package minesweeper.Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import minesweeper.View.ClockView;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import minesweeper.Controller.GameController;
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
        
        // The observable-observer initialization
        //Board board = new Board();
        Minesweeper game = new Minesweeper();
        GameView gameView = new GameView();
        ClockView clockView = new ClockView(game.timer);
        
        game.addObserver(gameView);
        game.addObserver(gameView.timeLabel);
        for(Tile t: game.board.getTiles()){
            
            
            t.addObserver(clockView); 
            t.addObserver(gameView);
        }
        MainMenuView view = new MainMenuView();
        new MainMenuController(view, primaryStage);
        view.update(primaryStage);
        
        //Example insert:
                // The observable-observer initialization
        Minesweeper model = new Minesweeper();
        GameView viewer = new GameView();
        model.addObserver(viewer);
        TimeLabel label = new TimeLabel();
        model.addObserver(label);

        // force a call to all observers
        model.pause();

        // control for user input
        GameController control = new GameController(model);

        
        
        /* GUI initialization (less important in this example)
        VBox root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        Insets insets = new Insets(10,10,10,10);
        label.setPadding(insets);
        StackPane thermPane = new StackPane();
        thermPane.setPadding(insets);
        thermPane.getChildren().add(viewer);
        control.setPadding(insets);
        root.getChildren().addAll(thermPane, label, control);
        Scene scene = new Scene(root);

        primaryStage.setTitle("Subject-Observer");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(gameView.scene);
        primaryStage.setResizable(false);

        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}