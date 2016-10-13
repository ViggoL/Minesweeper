/*
 * Minesweeper license
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

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public class GameButton {
    
    private final Button button;
    private DropShadow shadow;

    public GameButton(double diameter, ButtonEnum type) {
        button = new Button();
        shadow = new DropShadow();
        double offset = 3.0;
        
        shadow.setOffsetX(offset);
        shadow.setOffsetY(offset);
        shadow.setColor(Color.GREY);
        
        button.setShape(new Circle(diameter));
        button.setMinSize(diameter,diameter);
        
        double x1 = diameter/Math.PI + Math.exp(Math.E);
        double y2 = diameter*2/(Math.PI*Math.PI);
        
        if(type == ButtonEnum.PLAY){
            Polygon shape = new Polygon();
            shape.getPoints().addAll(new Double[]{x1, y2,diameter + Math.E*5,diameter/2,x1,diameter-y2});
            shape.setFill(Color.GREEN);
            shape.setEffect(shadow);
            button.setGraphic(shape); 
        }
            
    }
    
    public Button getButton(){
        return button;
    }

    enum ButtonEnum {
        PLAY,PAUSE,HELP;
    }
    
}
