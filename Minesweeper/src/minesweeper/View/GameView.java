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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Box;
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
    private final GameControllers controller;
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
        menuBar = new MenuBar();
        
        gameFrame = new BorderPane();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        
        gameFrame.setLeft(controller);
        gameFrame.setTop(menuBar);
        gameFrame.setCenter(grid);
        
        Box tempV = new Box();
        tempV.setWidth(buttonPaneWidth);
        gameFrame.setRight(tempV);
        
        tempV = new Box();
        tempV.setHeight(buttonPaneWidth);
        gameFrame.setBottom(tempV);
        
        //stage = new Stage();
        scene = new Scene(gameFrame);
        
    }
    
    public GameView(){
        this(new Minesweeper());
        
    }

    @Override
    public void update(Observable o, Object arg) {
        //gameFrame.setLeft(controller);
        //gameFrame.getCenter().setVisible(false);
        if(o instanceof GameTimer){
            System.out.println("YO my man!");
            GameTimer time = (GameTimer) o; 
            if(time.getSeconds() > 0){
                if(time.isTicking()){
                    grid.setVisible(true);
                    gameFrame.setCenter(grid);
                }
                else {
                    grid.setVisible(false);
                    gameFrame.setCenter(new TimeLabel("Time:" + game.getTime() + " seconds"));
                }
            }
            else if (time.isTicking()) gameFrame.setCenter(grid);
            else {
                Alert theTimeIsNow;
                theTimeIsNow = new Alert(Alert.AlertType.INFORMATION,"Click a tile to start playing!", ButtonType.OK);
                theTimeIsNow.show();
                    }
        }
        else if(o instanceof Minesweeper){
            System.out.println("game update");
        }
        //this.controller
        //grid.setVisible(false);
        
        System.out.println("Caller: " + o.getClass().toString());
        
    }
}
