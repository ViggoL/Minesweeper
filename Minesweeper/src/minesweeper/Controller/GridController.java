/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import java.util.ArrayList;
import javafx.event.Event;
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
        double buttonWidth = 20;
        double gridTileSize = buttonWidth * 1.75;
        int ID;

        for(Tile t: game.getBoardTiles()) {
            System.out.println(t.toString());
            ID = game.getBoardTiles().indexOf(t);
            
            Button b = new Button();
            b.setId(Integer.toString(ID));
            b.setMinSize(gridTileSize, gridTileSize);
            b.setOnMouseClicked(this::TileClicked);
            this.add(b, t.getX(), t.getY());
            
        }
            
    }
    
    public void TileClicked(Event event){
        System.out.println("I'm in!!");
        if(!game.isPaused()) {
            game.resume();
        }
        Button b = (Button) event.getSource();
        int i = this.getChildren().indexOf(b);
        Tile t = game.getBoardTiles().get(i);
        t.uncover();
    }
}
