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

import java.awt.Window;
import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import static javafx.scene.control.DialogEvent.DIALOG_CLOSE_REQUEST;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Box;
import javax.swing.event.HyperlinkEvent;
import minesweeper.Controller.GameControllers;
import minesweeper.Controller.GridController;
import minesweeper.Model.GameTimer;
import minesweeper.Model.Minesweeper;


/**
 *
 * @author Johan Lipecki <lipecki@kth.se>, Viggo Lundén <vlunden@kth.se>
 */
public class GameView extends GameViewSuper implements Observer{
    
    public Minesweeper game;
    private double gridTileSize;
    public Button pauseButton, rulesButton,resumeButton;
    public BorderPane gameFrame;
    public GridPane grid;
    public MenuBar menuBar;
    public Label timeLabel;
    public final Menu menu1, menu2, menu3;    // from javadoc example: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuBar.html
    private GameControllers controller;
    private final MenuItem menu4;
    public GameView(Minesweeper game) {
        super();
        this.game = game;
        
        buttonPaneWidth = 20.0;
        buttonWidth = 20;
        gridTileSize = buttonWidth * 1.75;

        controller = new GameControllers(game);
        grid = new GridController(game);
        
        menu1 = new Menu("File");
        menu2 = new Menu("Settings");
        menu3 = new Menu("Help");
        menu4 = new MenuItem("Quit");
        menuBar = new MenuBar();
        
        menu4.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                System.exit(0);
                }
        });
        
        menu1.getItems().add(menu4);
        
        gameFrame = new BorderPane();
        
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        
        gameFrame.setLeft(controller);
        gameFrame.setTop(menuBar);
        gameFrame.setCenter(grid);
        
        scene = new Scene(gameFrame);
    }
    
    public GameView(){
        this(new Minesweeper());
        
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof GameTimer){
            GameTimer time = (GameTimer) o; 
            if(time.getSeconds() > 0){
                if(time.isTicking()){
                    grid.setVisible(true);
                    gameFrame.setCenter(grid);
                }
                else {
                    grid.setVisible(false);
                    gameFrame.setCenter(new TimeLabel("Time: " + game.getTime() + " seconds"));
                }
            }
            else if (time.isTicking()) gameFrame.setCenter(grid);
            else {
                Alert theTimeIsNow;
                theTimeIsNow = new Alert(Alert.AlertType.INFORMATION,"Click a tile to start playing!", ButtonType.OK);
                theTimeIsNow.onCloseRequestProperty().set(new TheTimerIsNotRunning_AlertEventHandler(new DialogEvent(theTimeIsNow,DialogEvent.DIALOG_CLOSE_REQUEST)));
                theTimeIsNow.show();        
            }
        }
        else if(o instanceof Minesweeper){
            System.out.println("game update");
        }
        
    }

    private class TheTimerIsNotRunning_AlertEventHandler implements EventHandler<DialogEvent> {
        private Event event;

        private TheTimerIsNotRunning_AlertEventHandler(DialogEvent event) {
            this.event = event;
            handle((DialogEvent) event);
        }

        private TheTimerIsNotRunning_AlertEventHandler(EventType<DialogEvent> DIALOG_CLOSE_REQUEST) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void handle(DialogEvent event) {
            System.out.println("Yes!");
            controller.ResumeButtonClicked(event);
        }
    }
}
