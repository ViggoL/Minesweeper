/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template fileMenu, choose Tools | Templates
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import minesweeper.Controller.GameControllers;
import minesweeper.Controller.GridController;
import minesweeper.Controller.MainMenuController;
import minesweeper.Controller.NewGame;
import minesweeper.Model.Difficulty;
import minesweeper.Model.GameTimer;
import minesweeper.Model.Minesweeper;
import minesweeper.Model.Tile;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>, Viggo Lundén <vlunden@kth.se>
 */
public class GameView extends GameViewSuper implements Observer {

    public Stage gameStage;
    public Button pauseButton, rulesButton, resumeButton;
    public BorderPane gameFrame;
    public GridPane grid;
    public Label timeLabel;
    public final Menu fileMenu, helpMenu;    // from javadoc example: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuBar.html
    private GameControllers controller;
    private final MenuItem menuItemQuit, menuItemNewGame, exitMenuItem;
    private final ClockView clock;
    private Minesweeper game;

    public GameView(Minesweeper game, ClockView clock) {
        super(game);
        this.game = game;
        this.clock = clock;
        gameFrame = new BorderPane();

        buttonPaneWidth = 20.0;
        buttonWidth = 20;

        controller = new GameControllers(game, this);
        grid = new GridController(game);

        fileMenu = new Menu("File");
        helpMenu = new Menu("Help");
        exitMenuItem = new MenuItem("Exit");
        menuItemQuit = new MenuItem("Quit");
        menuItemNewGame = new MenuItem("New Game");

        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                gameFrame.getChildren().clear();
                gameFrame.setPrefSize(0.0, 0.0);
            }
        });
        menuItemQuit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        menuItemNewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {

                new NewGame(gameStage, game.getDifficultySetting());
            }
        });

        for (Difficulty d : Difficulty.values()) {
            MenuItem item = new MenuItem(d.toString());
            item.setOnAction(new SettingsMenuEventHandler(game, this));
            settingsMenu.getItems().add(item);
        }

        fileMenu.getItems().addAll(menuItemNewGame, exitMenuItem, menuItemQuit);

        menuBar.getMenus().add(settingsMenu);
        menuBar.getMenus().add(fileMenu);

        gameFrame.setLeft(controller);
        gameFrame.setTop(menuBar);
        gameFrame.setCenter(grid);

        scene = new Scene(gameFrame);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameTimer) {
            GameTimer time = (GameTimer) o;
            if (time.getSeconds() > 0) {
                if (time.isTicking()) {
                    grid.setVisible(true);
                } else if (!time.isTicking() && !game.isGameOver()) {
                    grid.setVisible(false);
                    gameFrame.setCenter(new TimeLabel("Time: " + time.getSeconds() + " seconds"));
                } else if (game.isGameOver()) grid.setVisible(true);
            } else if (time.getSeconds() == 0 && !time.isTicking()) {
                Alert theTimeIsNow = new Alert(Alert.AlertType.INFORMATION, "Click a tile to start playing!", ButtonType.OK);
                DialogEvent event = new DialogEvent(theTimeIsNow, DialogEvent.DIALOG_CLOSE_REQUEST);

                theTimeIsNow.onCloseRequestProperty().set(new TheTimerIsNotRunning_AlertEventHandler(event));
                theTimeIsNow.show();
            }
        } else if (o instanceof Minesweeper) {
            Minesweeper game = (Minesweeper) o;
            System.out.println("game update");
            synchronized (game.board) {
                if (game.getTime() > 0 && !game.isGameOver()) {
                    if (game.isPaused()) {
                        grid.setVisible(true);
                        gameFrame.setCenter(grid);
                    } else if (!game.isPaused()) {
                        grid.setVisible(false);
                        gameFrame.setCenter(new TimeLabel("Time: " + game.getTime() + " seconds"));
                    } 
                }
                else if (game.isGameOver()) {
                        for (Tile t : game.getBoardTiles()) {
                            game.board.uncover(t);
                        }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
                            Thread.currentThread().interrupt();
                        }
                        tellTheUserItsOver();
                    }
            }if (game.getTime() == 0 && !game.isPaused()) {
                Alert theTimeIsNow = new Alert(Alert.AlertType.INFORMATION, "Click a tile to start playing!", ButtonType.OK);
                DialogEvent event = new DialogEvent(theTimeIsNow, DialogEvent.DIALOG_CLOSE_REQUEST);

                theTimeIsNow.onCloseRequestProperty().set(new TheTimerIsNotRunning_AlertEventHandler(event));
                theTimeIsNow.show();
            }

        }

    }

    public void tellTheUserItsOver() {
        Alert gameOver;
        gameOver = new Alert(AlertType.INFORMATION);
        gameOver.setTitle("Game Over");
        gameOver.setContentText("It's all over mate!\n\n"
                + "Please select New Game.");
        gameOver.showAndWait();

    }

    public ClockView getClockView() {
        return this.clock;
    }

    public final class TheTimerIsNotRunning_AlertEventHandler implements EventHandler<DialogEvent> {

        public TheTimerIsNotRunning_AlertEventHandler(DialogEvent event) {
            handle((DialogEvent) event);
        }

        @Override
        public void handle(DialogEvent event) {
            controller.ResumeButtonClicked(event);
        }
    }

}
