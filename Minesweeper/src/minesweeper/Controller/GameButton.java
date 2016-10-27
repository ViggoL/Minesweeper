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
package minesweeper.Controller;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import minesweeper.Model.GameTimer;
import minesweeper.Model.Minesweeper;

/**
 * An observable Game button. Used for pausing, playing, etc.
 * @author Johan Lipecki <b>lipecki@kth.se</b>
 */
public class GameButton implements Observer{
    
    private final Button button;
    private DropShadow shadow;
    private double shadowOffset;
    private double sideLength;
    private Text text;
    public final ButtonEnum type;

    /**
     * Creates a new GameButton
     * @param diameter The diameter of the game button
     * @param type The type of game button
     */
    public GameButton(double diameter, ButtonEnum type) {
        button = new Button();
        shadow = new DropShadow();
        shadowOffset = diameter / 22;
        sideLength = Math.sqrt(Math.pow(diameter, 2)*2);
        
        //shadow.setOffsetX(shadowOffset);
        shadow.setOffsetY(shadowOffset);
        shadow.setColor(Color.GREY);
        
        
        button.setShape(new Circle(diameter));
        
        button.setMinSize(sideLength, sideLength);
        button.setMaxSize(sideLength, Double.MAX_VALUE);
        button.setAlignment(Pos.CENTER);
        
        double hypotenuse = Math.sqrt(Math.pow(diameter, 2.0)*2);
        double x1 = (hypotenuse - diameter)/Math.PI;
        double y1 = (hypotenuse - diameter)/(Math.PI*Math.E);
        this.type = type;
        
        switch(type){
            case PLAY:
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(new Double[]{x1, y1,diameter,diameter/2,x1,diameter-y1});
                triangle.setFill(Color.GREEN);
                triangle.setEffect(shadow);
                button.setGraphic(triangle);
                break;
            case PAUSE:
                Group root = new Group();
                Line  line1, line2;
                double strokeWidth = diameter / 5;//Math.pow(Math.PI,Math.E);
                double x2 = Math.PI*diameter/3;
                
                line1 = new Line(diameter/2,0.0,diameter/2,diameter/2);
                line2 = new Line(x2,0.0,x2,diameter/2);
                
                root.getChildren().addAll(line1, line2);
                System.out.println("Lines: " + Arrays.toString(root.getChildren().toArray()));
                for(Object l: root.getChildren().toArray()) {
                    Line line = (Line) l;
                    line.setStroke(Color.BLUE);
                    line.setStrokeLineCap(StrokeLineCap.ROUND);
                    line.setStrokeWidth(strokeWidth);
                    line.setEffect(shadow);
                    l = line;
                }
                button.setGraphic(root);
                break;
            case HELP:
                Font myFont = new Font("Garamond",diameter);
                
                text = new Text("?");
                text.setEffect(shadow);
                text.setFont(myFont);
                
                button.setGraphic(text);
                break;
            default: 
                myFont = new Font("Garamond",14);
                
                text = new Text("Time");
                text.setEffect(shadow);
                text.setFont(myFont);
                
                button.setShape(new Rectangle(10.0,20.0));
                button.setGraphic(text);
                break;
                     
        }
            
    }
    
    /**
     * 
     * @return The inner button of the Game button 
     */
    public Button getButton(){
        return button;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Minesweeper){
            Minesweeper model = (Minesweeper) o;
            this.text = new Text(Integer.toString(model.getTime()));
        }
        else if (o instanceof GameTimer){
            GameTimer model = (GameTimer) o;
            this.text = new Text(Integer.toString(model.getSeconds()));
        }
    }
    
    
    public enum ButtonEnum {
        PLAY,PAUSE,HELP,TIME;
    }
    
}
