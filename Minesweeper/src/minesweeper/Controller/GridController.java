/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import java.util.Arrays;
import java.util.List;
import minesweeper.Model.TileEventException;
import java.util.Observable;
import java.util.Observer;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;
import minesweeper.Model.TileType;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

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
    private static int explosionCount, flagCount;
    private boolean stopExplosion;

    public GridController(Minesweeper game) throws IllegalArgumentException {

        this.game = game;
        timerStarted = false;
        buttonWidth = 20;
        gridTileSize = buttonWidth * 1.75;
        explosionCount = flagCount = 0;

        int ID;
        inset = new Insets(20);

        this.setAlignment(Pos.CENTER_LEFT);
        this.setGridLinesVisible(true);
        this.setPadding(inset);

        //get image for buttons
        Image image = new Image("water.png", buttonWidth, buttonWidth, false, true);
        DropShadow shadow = new DropShadow(2.0, Color.WHITE);

        //map tiles to a grid and set grid x and y for button 
        //using Tile Point X and Y
        this.getChildren().clear();
        for (Tile t : game.getBoardTiles()) {
            ID = game.getBoardTiles().indexOf(t);

            image.isSmooth();
            imageViewButton = new ImageView(image);
            imageViewButton.setEffect(shadow);
            imageViewButton.setFitHeight(gridTileSize);
            imageViewButton.setFitWidth(gridTileSize);
            imageViewButton.setOnMouseClicked(this::TileClicked);
            this.add(imageViewButton, t.getX(), t.getY());
            System.out.println("Tile ID: " + ID + t.getPoint().toString());

            //since the button handlers alter the tiles, 
            //action events trigger observers
            t.addObserver(this);

        }

    }

    public void TileClicked(MouseEvent event) throws TileEventException {

        if (timerStarted == false) {
            game.startTime();
            timerStarted = true;
        }
        Object o = event.getSource();
        Button b;
        ImageView im;
        int i;
        if (o instanceof Button) {
            b = (Button) event.getSource();
            i = this.getChildren().indexOf(b);;
        } else if (o instanceof ImageView) {
            
            im = (ImageView) o;
            System.out.println(event.getEventType().toString());
            ObservableList<Node> l = this.getChildren();
            i = this.getChildren().indexOf(im);
        } else {
            i = -1;
        }
        try {
            
            Tile t = game.getBoardTiles().get(i);
            
            if (event.getButton() == MouseButton.SECONDARY || event.isControlDown()) {
                t.setFlagged(!t.isFlagged());
                return;
            }
            else game.board.uncover(t);

            System.out.println("Tile number: " + i);

            TileType type = t.getType();
            switch (type) {
                case BOMB:
                    
                    this.game.setGameOver();
                    System.out.println("BOMB!!!!!!");
                    
                    break;
                default:
                    break;
            }

        } catch (IndexOutOfBoundsException index) {
            for (String s : Arrays.toString(index.getStackTrace()).split(",")) {
                System.err.print(s);
            }
            throw new TileEventException("Event source not supported");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Tile) {

            synchronized (o) {
                //System.out.println(((Tile) o).getX());
                Tile tile = (Tile) o;
                Object iv = this.getChildren().get(game.getBoardTiles().indexOf(tile));
                
                //ignore updates by the Group Node of a list
                if (!(iv instanceof Group)) {
                    Integer count = null;
                    if(arg != null) count = (Integer) arg;
                    else count = 0;
                    updateTileSwitch(tile);

                } 
            }    
        }
        else if (o instanceof Minesweeper) {
            System.out.println("Game updating GridController");
        }
        
    }

    private void updateTileSwitch(Tile tile) {
        ImageView img = (ImageView) this.getChildren().get(game.getBoardTiles().indexOf(tile));
        TileType type = tile.getType();
        if (tile.isFlagged()) {
            img.setImage(new Image("flag4.png", buttonWidth, buttonWidth, false, true));
            return;
        }
        else {
            img.setImage(new Image("water.png", buttonWidth, buttonWidth, false, true));
        }
        
        synchronized(game.board){
        switch (type) {
            case BOMB:
                if (!tile.isFlagged() && !tile.isCovered()) img.setImage(new Image("mine3.png", buttonWidth, buttonWidth, false, true));
                break;
            default:
                //the update is fired by a change in Tile.isCovered()
                if (!tile.isCovered() && tile.getType() != TileType.BOMB) {
                    img.setImage(new Image("uncovered.png", buttonWidth, buttonWidth, false, true));
                    int bombCount = game.board.bombCount(tile);
                    if (bombCount != 0) 
                        img.setImage(new Image(bombCount + ".png", buttonWidth, buttonWidth, false, true));
                }
        }
        if (!stopExplosion && type == TileType.BOMB) {
            //MouseEvent.fireEvent(img, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
        }

        }
    }

}
