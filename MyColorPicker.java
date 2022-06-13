package com.example.lista5;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

/** Custom class for ColorPicker */
public class MyColorPicker extends ColorPicker {
    /** colored polygon */
    MyPolygon colored;

    /**
     * Method of repositioning and showing
     * @param x x coordinate
     * @param y y coordinate
     */
    public void ShowColorPicker(double x,double y){
        setLayoutX(x);
        setLayoutY(y);
        setViewOrder(-1);
        setVisible(true);
    }

    /** Constructor for MyColorPicker*/
    MyColorPicker(){
        setOnAction(event -> {
            Color myColor = getValue();
                colored.setFill(myColor);
        });
        setVisible(false);
    }
}
