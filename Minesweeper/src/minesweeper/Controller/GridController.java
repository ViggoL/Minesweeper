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
import javafx.scene.layout.GridPane;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;

/**
 *
 * @author Viggo
 */
public class GridController extends GridPane{
    private Minesweeper game;
    
    public GridController(Minesweeper game)
    {
        this.game = game;
        double buttonWidth = 20;
        double gridTileSize = buttonWidth * 1.75;
        int ID;
        
        this.setAlignment(Pos.CENTER_LEFT);
        this.setGridLinesVisible(false);

        for(Tile t: game.getBoardTiles()) {
            ID = game.getBoardTiles().indexOf(t);
            
            Button b = new Button();
            b.setId(Integer.toString(ID));
            b.setMinSize(gridTileSize, gridTileSize);
            b.setOnMouseClicked(this::TileClicked);
            this.add(b, t.getX(), t.getY());
            
        }
            
    }
    
    public void TileClicked(Event event){

        game.timer.startTimer();
        
        Button b = (Button) event.getSource();
        int i = this.getChildren().indexOf(b);
        Tile t = game.getBoardTiles().get(i);
        t.uncover();
        System.out.println("Tile number: " + i);
    }
}
