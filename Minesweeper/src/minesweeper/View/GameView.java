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
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>, Viggo Lundén <vlunden@kth.se>
 */
public class GameView extends GameViewSuper implements Observer{
    
    public Minesweeper game;
    private double gridTileSize;
    public Button pauseButton;
    public BorderPane gameFrame;
    public GridPane grid;
    public MenuBar menuBar;
    public final Menu menu1, menu2, menu3;    // from javadoc example: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuBar.html
    
    private Stage stage;
    public GameView(){
        super();
        gridTileSize = 5.0;
        buttonPaneWidth = 20.0;
        buttonWidth = 20;
        //resumebutton = new GameButton(20, GameButton.ButtonEnum.PLAY).getButton();
        pauseButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PAUSE).getButton();
        rulesButton = new GameButton(buttonWidth, GameButton.ButtonEnum.HELP).getButton();
        resumeButton = new GameButton(buttonWidth, GameButton.ButtonEnum.PLAY).getButton();
        
        
        menu1 = new Menu("File");
        menu2 = new Menu("Settings");
        menu3 = new Menu("Help");
        menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2, menu3);
        
        gameFrame = new BorderPane();
        grid = new GridPane();
        buttonPane = new VBox();
        
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setGridLinesVisible(false);
        //BorderStroke[] BorderStroke = null;
        //grid.setBorder(new Border(BorderStroke));
        
        buttonPane.setPadding(new Insets(5));
        buttonPane.setAlignment(Pos.BASELINE_LEFT);
        buttonPane.getChildren().addAll(pauseButton, rulesButton);
        
        gameFrame.setLeft(buttonPane);
        gameFrame.setTop(menuBar);
        gameFrame.setCenter(grid);
        
        Box tempV = new Box();
        tempV.setWidth(buttonPaneWidth);
        gameFrame.setRight(tempV);
        
        tempV = new Box();
        tempV.setHeight(buttonPaneWidth);
        gameFrame.setBottom(tempV);
        
        stage = new Stage();
        
    }

    @Override
    public void update(Observable o,Object primaryStage) {
        this.game = (Minesweeper) o;
        
        // http://www.java2s.com/Code/Java/JavaFX/UsingImageViewtodisplayimage.htm

        
        for(Tile t: game.getBoardTiles()) {
            Button b = new Button(Integer.toString(game.getBoardTiles().indexOf(t)));
            b.setMinSize(buttonWidth*1.75, buttonWidth*1.75);
            grid.add(b, t.getX(), t.getY());
        }
        
        Scene scene = new Scene(gameFrame);
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.show();
        stage.centerOnScreen();
    }
    
}
