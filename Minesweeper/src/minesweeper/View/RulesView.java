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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class RulesView implements Runnable {

    private static Label title,body;
    private static VBox root;
    private static Scene scene;

    public static void showRules() {
        Stage stage = new Stage();
        
        title = new Label("Minesweeper Rules");
        title.setFont(new Font("Helvetica", 30));
        title.setPadding(new Insets(5, 5, 20, 5));
        
        body = new Label("The goal of the game is to uncover all the squares "
                + "without clicking on a mine and being \"blown up\". \n"
                + "\tThe locations of the mines are discovered by a process of logic: "
                + "clicking on a square on the game board "
                + "will reveal what is hidden underneath. "
                + "Some squares are blank but some contain numbers (1 to 8), "
                + "each number being the number of mines adjacent to the uncovered square. "
                + "\n"
                + "\tTo help avoid hitting a mine, "
                + "the location of a suspected mine can be marked "
                + "by flagging it with the right mouse button. "
                + "The game is won once all blank squares have been uncovered "
                + "without hitting a mine. "
                + "Any remaining mines not identified by flags are automatically flagged by the computer.\n\n"
                + "The game board comes in three set sizes: EASY, MEDIUM, and HARD");
        body.setPadding(new Insets(5));
        
        root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(title,body);
        
        scene = new Scene(root,500,500);
        
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }

    @Override
    public void run() {
        showRules();
    }

}
