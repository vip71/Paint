package com.example.lista5;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class of rectangle in background where objects are painted
 * @see Rectangle
 */
public class Cloth extends Rectangle {
    /** index of chosen shape: 1-circle 2-rectangle 3-regular polygon 4-irregular polygon*/
    public static int usedFigureIndex;
    /** number of angels of shape for polygons*/
    public static int angels;
    /** pane that contains the cloth and drown figures*/
    AnchorPane root;

    /**
     * Method that sets number of angles of drown polygon
     * @param s s
     */
    static void SetAngles(String s){
        try{
            int i=Integer.parseInt(s);
            if(i>2) angels=i;
        } catch (NumberFormatException e){
            angels=3;
        }
    }

    /**
     * Method draws figures on cloth
     * @param mouseEvent mouseEvent
     * @see MyScene
     */
    void Paint(MouseEvent mouseEvent){
        MouseButton button = mouseEvent.getButton();
        if(button== MouseButton.PRIMARY){

            MyPolygon shape = switch (usedFigureIndex) {
                case 0 -> new MyPolygon(100, mouseEvent.getSceneX(), mouseEvent.getSceneY(), 2);
                case 1 -> new MyPolygon(4, mouseEvent.getSceneX(), mouseEvent.getSceneY(), 1);
                case 2 -> new MyPolygon(angels,mouseEvent.getSceneX(),mouseEvent.getSceneY(),2);
                default -> new MyPolygon(angels, mouseEvent.getSceneX(), mouseEvent.getSceneY(), 3);
            };

            root.getChildren().add(shape);
            for (int i = 0; i < shape.angles; i++) root.getChildren().add(shape.draggableArm[i]);
            root.getChildren().add(shape.draggableCenter);
        }
    }

    /**
     * Constructor for Cloth class
     * @param root root
     * @see MyScene
     * @see AnchorPane
     */
    Cloth(AnchorPane root){
        super(1280,1000,Color.LIGHTGOLDENRODYELLOW);
        this.root=root;
        angels=3;

        setOnMouseClicked(this::Paint);
    }
}
