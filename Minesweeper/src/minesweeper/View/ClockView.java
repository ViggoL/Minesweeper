/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * [Project] license
 * 
 * Copyright © 2016 Johan Lipecki & Viggo Lundén
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
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import minesweeper.Model.Minesweeper;

/**
 * 
 * @author Johan Lipecki <lipecki@kth.se>
 * @author Johan Lipecki <vlunden@kth.se>
 */
public class ClockView implements Observer {

    Minesweeper game;
    private Stage stage;
    private TimeLabel timeLabel;
    private final Scene scene;
    private int seconds;
    private static ClockView theClock;
    private HBox timeBox;
    private double xSize, ySize;
    private double screenPosition;
    private int howManyTimes;
    private Pane clock;

    public ClockView(Minesweeper game) {
        this.game = game;

        clock = new Pane();
        timeBox = new HBox();

        timeBox.setPadding(new Insets(10));

        setBoxSize(200.0);

        timeBox.setMinSize(xSize, ySize);
        timeBox.setAlignment(Pos.CENTER);

        timeLabel = new TimeLabel();
        seconds = game.getTime();
        timeLabel.setText("Time: " + seconds + " seconds");

        timeBox.getChildren().add(timeLabel);
        clock.getChildren().add(timeBox);
        scene = new Scene(clock);

        screenPosition = 2.0;
        howManyTimes = 1;

        addSceneToStage(scene);
        update();

        //Platform thread must control timer (http://stackoverflow.com/a/18654916)
        Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        timeLabel.update(game.getTimer(), game.getTime());
                    }
                });
            }
        }, 1000, 1000);
    }

    private void update() {
        update(game, game.getTime());
    }

    @Override
    public void update(Observable o, Object arg) throws NullPointerException {
        if (o instanceof Minesweeper) {
            Minesweeper game = (Minesweeper) o;

            seconds = (int) arg;
            timeLabel.setText("Time: " + seconds + " seconds");
            if(game.isGameOver()) stage.close();
        }
    }

    public void showClock() {
        if (theClock != null) {
            if (theClock.stage.isShowing()) {
                theClock.grow();
            }

            stage.show();
        } else {
            game.pause();
            theClock = new ClockView(game);
            stage.show();
        }
    }

    private void grow() {
        howManyTimes++;
        if (getBoxSize()[0] >= scene.getRoot().minWidth(ySize)) {
            setBoxSize(xSize * 1.1);
        }
        timeBox.setMinSize(xSize, ySize);
        clock = new Pane();
        clock.getChildren().add(timeBox);
        stage.close();
        addSceneToStage(new Scene(clock));
    }

    private void setBoxSize(double x) {
        xSize = x;
        ySize = xSize / 2;
    }

    //returns x and y values for Label HBox
    public Double[] getBoxSize() {

        return new Double[]{xSize, ySize};
    }

    private void positionStage() {
        screenPosition += screenPosition * 2;
        stage.setX(screenPosition);
        stage.setY((screenPosition) / 1.5);

    }

    private void addSceneToStage(Scene scene) {
        stage = new Stage();
        stage.setTitle("Minesweeper Time");
        stage.setScene(scene);
        positionStage();
    }

}
