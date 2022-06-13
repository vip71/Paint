package com.example.lista5;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Class of circles that can be moved by dragging
 */
public class DraggablePoint extends Circle {
    /**
     * Attributes that determine ability to be repositioned or weather the circle is pointed/dragged
     */
    boolean canMoveY,draggable,isPointed,isDragged;

    /**
     * Method that changes position of DraggablePoint when dragged
     * @param mouseEvent mouseEvent
     */
    void Move(MouseEvent mouseEvent){
        if(draggable&&isDragged) {
            setCenterX(mouseEvent.getX());
            if (canMoveY) setCenterY(mouseEvent.getY());
        }
    }

    /**
     * Constructor for DraggablePoint
     * @param x x coordinate of created point
     * @param y y coordinate of created point
     */
    DraggablePoint(double x,double y){
        super(x,y,10,Color.BLUE);
        canMoveY=true;
        draggable=true;

        addEventFilter(MouseEvent.MOUSE_PRESSED,mouseEvent ->  isDragged=true);

        addEventHandler(MouseEvent.MOUSE_DRAGGED, this::Move);

        addEventHandler(MouseEvent.MOUSE_RELEASED,mouseEvent -> isDragged=false);

        setOnMouseExited(event -> isPointed=false);

        setOnMouseEntered(event -> isPointed=true);
    }
}
