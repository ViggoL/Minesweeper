/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * [Project] license
 * 
 * Copyright © 2016 Johan Lipecki
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package minesweeper.View;

import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import minesweeper.Model.GameTimer;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>, Viggo Lundén <vlunden@kth.se>
 */
public class GameView extends GameViewSuper implements Observer{
    
    public Button pauseButton;
    public BorderPane gameFrame;
    public GridPane grid;
    public MenuBar menuBar;
    public Label timeLabel;
    public GameTimer gameTimer;
    public final Menu menu1, menu2, menu3;    // from javadoc example: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuBar.html
    
    private Stage stage;
    public GameView() {
        super();
        gameTimer = new GameTimer();
        gameTimer.startTimer();
        buttonPaneWidth = 10.0;
        buttonWidth = 5;
        playButton = new GameButton(20, GameButton.ButtonEnum.PLAY).getButton();
        pauseButton = new GameButton(20, GameButton.ButtonEnum.PAUSE).getButton();
        rulesButton = new GameButton(20, GameButton.ButtonEnum.HELP).getButton();
        resumeButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PLAY).getButton();
        timeLabel = new Label();
        
        menu1 = new Menu("File");
        menu2 = new Menu("Settings");
        menu3 = new Menu("Help");
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        
        gameFrame = new BorderPane();
        grid = new GridPane();
        buttonPane = new VBox();
        
        stage = new Stage();
        
    }


    public void update(Stage primaryStage) {
        buttonPane.setPadding(new Insets(5));
        buttonPane.setAlignment(Pos.BASELINE_LEFT);
        buttonPane.getChildren().addAll(pauseButton,playButton,timeLabel);
        
        gameFrame.setLeft(buttonPane);
        gameFrame.setTop(menuBar);
        gameFrame.setCenter(grid);
        Scene scene = new Scene(gameFrame, 300, 200);
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        //stage.setResizable(false);
        
        
        stage.show();
        stage.centerOnScreen();
    }

    @Override
    public void update(Observable o, Object arg) {
        timeLabel.setText(String.valueOf(((GameTimer)o).getSeconds()));
    }
    
}
