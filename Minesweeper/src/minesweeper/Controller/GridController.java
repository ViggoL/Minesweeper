/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.Observable;
import java.util.Observer;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import minesweeper.Model.Difficulty;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;
import minesweeper.Model.TileType;

/**
 *
 * @author Viggo
 */
public class GridController extends GridPane implements Observer {
    private Minesweeper game;
    private boolean timerStarted;
    private Insets inset;
    public static ImageView imageViewButton;
    private double buttonWidth;
    private double gridTileSize;

    public GridController(Minesweeper game) throws IllegalArgumentException
    {
        
        this.game = game;
        timerStarted = false;
        buttonWidth = 20;
        gridTileSize = buttonWidth * 1.75;

        int ID;
        inset = new Insets(20);
        
        this.setAlignment(Pos.CENTER_LEFT);
        this.setGridLinesVisible(true);
        this.setPadding(inset);
        
        
        
        //get image for buttons
        Image image = new Image("water.png", buttonWidth, buttonWidth, false, true);
        DropShadow shadow = new DropShadow(2.0,Color.WHITE);
        
        //map tiles to a grid and set grid x and y for button 
        //using Tile Point X and Y
        for(Tile t: game.getBoardTiles()) {
            ID = game.getBoardTiles().indexOf(t);
            
            image.isSmooth();
            imageViewButton = new ImageView(image);
            imageViewButton.setEffect(shadow);
            imageViewButton.setFitHeight(gridTileSize);
            imageViewButton.setFitWidth(gridTileSize);
            imageViewButton.setOnMouseClicked(this::TileClicked);
            this.add(imageViewButton, t.getX(), t.getY()); 
            t.addObserver(this);
            
            /*
            Button b = new Button();
            b.setId(Integer.toString(ID));
            b.setMinSize(gridTileSize, gridTileSize);
            b.setOnMouseClicked(this::TileClicked);
            this.add(b, t.getX(), t.getY());
            */
            
        }
      
    }
    
    public void TileClicked(Event event) throws TileEventException{
        
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
            i = this.getChildren().indexOf(b) - 1;
        }
        
        else if(o instanceof ImageView) {
            im = (ImageView) o;
            i = this.getChildren().indexOf(im) - 1;
        }
        
        else i = -1;
        try{
            Tile t = game.getBoardTiles().get(i);
            t.uncover();
            
            System.out.println("Tile number: " + i);
            
            TileType type = t.getType();
            switch(type){
                case FLAG: break;
                case BOMB: 
                    game.unCoverThemAll();
                    game.tellTheUserItsOver(); 
                    game.wouldYouLikeToPlayAgainPrompt();
                    System.out.println("BOMB!!!!!!");
                    //perhapsWePlaySomeClipWithAnExplosionHere();
                    break;
                default: 
                    game.viggoUncoverAdjacentNumbers();
                    break;
            }

        }
        catch (IndexOutOfBoundsException index) {
            for(String s: Arrays.toString(index.getStackTrace()).split(","))
                System.err.print(s);         
            throw new TileEventException("Event source not supported");
        }
    }

    private void perhapsWePlaySomeClipWithAnExplosionHere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * code snippet from https://examples.javacodegeeks.com
     */
    public class TileEventException extends RuntimeException{
        
        public TileEventException(){
            
        }
        
        public TileEventException(String message){
            super(message);
            Alert a;
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Serious Runtime Error");
            a.setContentText("A serious error has interrupted your game. \n"
                    + "Should the problems persist,\n"
                    + "please e-mail the Minesweeper team at support@minesweep.com");
            a.setOnCloseRequest(this::TerminateGame);
            a.setGraphic(GridController.imageViewButton);
            a.show();
        }
        
        public TileEventException(Throwable cause){
            super(cause);
        }
        
        public TileEventException(String message, Throwable cause){
            super(message,cause);
            
        }
        
        public TileEventException(String message, Throwable cause, 
                boolean enableSuppression, boolean writeableStacktrace){
            super( message, cause, enableSuppression, writeableStacktrace);
        }
        
        public void TerminateGame(Event event){
                System.exit(666);
        }
        
    }

    @Override
    public void update(Observable o, Object arg) {
        Tile tile = (Tile)o;
        ImageView img = (ImageView)this.getChildren().get(game.getBoardTiles().indexOf(tile));
        if (!tile.isCovered()) {
            img.setImage(new Image("uncovered.png", buttonWidth, buttonWidth, false, true));
        }

    }
}
