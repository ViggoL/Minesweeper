/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import minesweeper.Model.GameTimer;
import minesweeper.Model.Minesweeper;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class ClockView implements Observer, Runnable{
    private Stage stage;
    private TimeLabel timeLabel;
    private final Scene scene;
    private int seconds;
    private GameTimer timer;
    
    public ClockView(GameTimer time){
        timer = time;
        Pane clock = new Pane();
        HBox timeBox = new HBox();
        
        timeBox.setPadding(new Insets(10));
        timeBox.setMinSize(200.0, 50.0);
        timeBox.setAlignment(Pos.CENTER);
        
        stage = new Stage();
        timeLabel = new TimeLabel();
        timeBox.getChildren().add(timeLabel);
        clock.getChildren().add(timeBox);
        scene = new Scene(clock);
    }
    
    public ClockView(Minesweeper game){
        this(game.timer);
    }



    @Override
    public void run() {
            update(timer, new Object());
    
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try{
            
            seconds = timer.getSeconds();
            
            timeLabel.setText("Time: " + seconds + " seconds");
            
            
            stage.setTitle("Minesweeper Time");
            stage.setScene(scene);
            
            stage.sizeToScene();
            stage.setAlwaysOnTop(true);
            stage.toFront();

            stage.show();
            
            
        }
        finally{
            //stage.close();
        }
    }

    public Runnable start() {
        return this;
    }
    
}
