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
package minesweeper.Model;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class PlayButton {
    
    private final Button playButton;
    private final Polygon triangle;
    private DropShadow shadow;
    
    public PlayButton(){
        playButton = new Button();
        triangle = new Polygon();
        shadow = new DropShadow();
    }
    
    public Button setButton(double diameter){
        double x1 = diameter/Math.PI + Math.exp(Math.E);
        double y2 = diameter*2/(Math.PI*Math.PI);
        double offset = 3.0;
        
        shadow.setOffsetX(offset);
        shadow.setOffsetY(offset);
        shadow.setColor(Color.GREY);
        
        triangle.getPoints().addAll(new Double[]{x1, y2,diameter,diameter/2,x1,diameter-y2});
        triangle.setFill(Color.GREEN);
        triangle.setEffect(shadow);
                
        playButton.setShape(new Circle(diameter));
        playButton.setMinSize(diameter,diameter);
        playButton.setGraphic(triangle);
        
        return playButton;
    }
}
