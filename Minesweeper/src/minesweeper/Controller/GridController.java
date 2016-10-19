/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import java.util.ArrayList;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;

/**
 *
 * @author Viggo
 */
public class GridController extends GridPane {
    private Minesweeper game;
    private boolean timerStarted;
    
    public GridController(Minesweeper game) throws IllegalArgumentException
    {
        this.game = game;
        timerStarted = false;
        double buttonWidth = 20;
        double gridTileSize = buttonWidth * 1.75;
        int ID;
        
        this.setAlignment(Pos.CENTER_LEFT);
        this.setGridLinesVisible(true);
        
        
        
        //get image for buttons
        
        
        //map tiles to a grid and set grid x and y for button 
        //using Tile Point X and Y
        for(Tile t: game.getBoardTiles()) {
            ID = game.getBoardTiles().indexOf(t);
            
            Image image = new Image("water.png", buttonWidth, buttonWidth, false, true);
            ImageView imageViewButton = new ImageView(image);
            
            /*
            Button b = new Button();
            b.setId(Integer.toString(ID));
            b.setMinSize(gridTileSize, gridTileSize);
            b.setOnMouseClicked(this::TileClicked);
            this.add(b, t.getX(), t.getY());
            */
            imageViewButton.setFitHeight(buttonWidth);
            imageViewButton.setFitWidth(buttonWidth);
            imageViewButton.setOnMouseClicked(this::TileClicked);
            this.add(imageViewButton, t.getX(), t.getY());
            
        }
            
    }
    
    public void TileClicked(Event event){

        if(timerStarted == false){
            game.timer.startTimer();
            timerStarted = true;
        }
        Object o = event.getSource();
        Button b;
        ImageView im;
        int i;
        if(o instanceof Button) {
            b = (Button) event.getSource();
            i = this.getChildren().indexOf(b);
        }
        else if(o instanceof ImageView) {
            im = (ImageView) o;
            i = this.getChildren().indexOf(im);
        }
        else i = -1;
        
        Tile t = game.getBoardTiles().get(i);
        t.uncover();
        System.out.println("Tile number: " + i);
    }
}
