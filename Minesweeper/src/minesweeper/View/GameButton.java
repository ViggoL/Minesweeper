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

/**
 *
 * @author Johan Lipecki <lipecki@kth.se>
 */
public abstract class GameButton {
    
    protected DropShadow shadow;

    public GameButton() {
        shadow = new DropShadow();
        double offset = 3.0;
        
        shadow.setOffsetX(offset);
        shadow.setOffsetY(offset);
        shadow.setColor(Color.GREY);
    }

    public abstract Button setButton(double diameter);
    
}
