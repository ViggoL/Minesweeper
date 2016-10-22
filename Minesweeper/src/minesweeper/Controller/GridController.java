/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template menu1, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper.Controller;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

import java.util.Arrays;
import minesweeper.Model.TileEventException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;
import minesweeper.Model.TileType;
import static java.lang.Thread.sleep;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;

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
        for (Tile t : game.getBoardTiles()) {
            ID = game.getBoardTiles().indexOf(t);

            image.isSmooth();
            imageViewButton = new ImageView(image);
            imageViewButton.setEffect(shadow);
            imageViewButton.setFitHeight(gridTileSize);
            imageViewButton.setFitWidth(gridTileSize);
            imageViewButton.setOnMouseClicked(this::TileClicked);
            this.add(imageViewButton, t.getX(), t.getY());

            //since the button handlers alter the tiles, 
            //action events trigger observers
            t.addObserver(this);

        }

    }

    public void TileClicked(Event event) throws TileEventException {

        if (timerStarted == false) {
            game.timer.startTimer();
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
            i = this.getChildren().indexOf(im);//;
        } else {
            i = -1;
        }
        try {
            Tile t = game.getBoardTiles().get(i);
            t.uncover();

            System.out.println("Tile number: " + i);

            TileType type = t.getType();
            switch (type) {
                case FLAG:
                    System.out.println("It's flagged!");
                    break;
                case BOMB:

                    this.game.setGameOver();
                    //game .unCoverThemAll();
                    //this.tellTheUserItsOver(); 
                    //wouldYouLikeToPlayAgainPrompt();
                    System.out.println("BOMB!!!!!!");
                    //perhapsWePlaySomeClipWithAnExplosionHere();
                    break;
                default:
                    //game.viggo_sUncoverAdjacentNumbers();
                    break;
            }

        } catch (IndexOutOfBoundsException index) {
            for (String s : Arrays.toString(index.getStackTrace()).split(",")) {
                System.err.print(s);
            }
            throw new TileEventException("Event source not supported");
        }
    }

    private void perhapsWePlaySomeClipWithAnExplosionHere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void tellTheUserItsOver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Tile) {

            synchronized (o) {
                //System.out.println(((Tile) o).getX());

                if (explosionCount == 0) {
                    stopExplosion = false;
                }
                Tile tile = (Tile) o;
                Object iv = this.getChildren().get(game.getBoardTiles().indexOf(tile));
                if (!(iv instanceof Group)) {
                    updateTileSwitch(tile);

                } 
                else if (iv instanceof Group) {
                    ObservableList<Node> tiles = ((Group) iv).getChildrenUnmodifiable();
                    System.out.println(tiles.toArray().toString());
                    
                    
                }
            }    
        }
        else if (o instanceof Minesweeper) {
            System.out.println("Game updating GridController");
        }
    }

    private void sleeper(int sleep) {
        try {
            sleep(sleep);
        } catch (InterruptedException ex) {
            Logger.getLogger(GridController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updateTileSwitch(Tile tile) {
        ImageView img = (ImageView) this.getChildren().get(game.getBoardTiles().indexOf(tile));
        TileType type = tile.getType();

        switch (type) {
            case BOMB:
                explosionCount++;
                img.setImage(new Image("mine" + explosionCount + ".png", buttonWidth, buttonWidth, false, true));
                if (explosionCount == 4) {
                    System.out.println("stop: " + explosionCount);
                    stopExplosion = true;
                    explosionCount = 0;
                }
                break;
            case FLAG:
                flagCount++;
                img.setImage(new Image("flag" + flagCount + ".png", buttonWidth, buttonWidth, false, true));
                if (flagCount == 4) {
                    flagCount = 0;
                }
                break;
            default:
                //the update is fired by a change in Tile.isCovered()
                if (!tile.isCovered() && tile.getType() != TileType.BOMB) {
                    img.setImage(new Image("uncovered.png", buttonWidth, buttonWidth, false, true));
                    for (int i = 1; i <= 8; i++) {
                        if (Tile.bombCount(tile.getSurroundingTiles(game.board)) == i) {
                            img.setImage(new Image(i + ".png", buttonWidth, buttonWidth, false, true));
                        }
                    }
                }
        }
        if (!stopExplosion && type == TileType.BOMB) {
            //MouseEvent.fireEvent(img, new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
        }

    }

}
