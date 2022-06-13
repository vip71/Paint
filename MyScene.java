package com.example.lista5;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Custom class for scene
 */
public class MyScene extends Scene {
    /**
     * ColorPicker used in scene
     * @see MyColorPicker
     */
    static MyColorPicker colorPicker;

    /**
     * Method that moves ColorPicker
     * @param parent parent of scene
     * @param mouseEvent mouseEvent
     */
    void MoveColorPicker(Parent parent,MouseEvent mouseEvent){
        MouseButton button = mouseEvent.getButton();
        for (Node node:parent.getChildrenUnmodifiable()) {
            if(node instanceof MyPolygon myPolygon){
                if(myPolygon.isActive&&!myPolygon.isPointed) {
                    myPolygon.Deactivate();
                    colorPicker.setVisible(false);
                }
                else if(button == MouseButton.SECONDARY && myPolygon.isActive){
                    colorPicker.ShowColorPicker(mouseEvent.getX(),mouseEvent.getY());
                    colorPicker.colored=myPolygon;
                }
            }
        }
    }

    /**
     * Method that corrects position of polygon when resized
     * @param parent parent
     */
    void ResizePolygons(Parent parent){
        for (Node node:parent.getChildrenUnmodifiable()) {
            if(node instanceof MyPolygon myPolygon){
                myPolygon.AddResize();
            }
        }
    }


    /**
     * Constructor for custom scene
     * @param parent parent
     * @param v1 v1
     * @param v2 v1
     * @param colorPicker colorPicker
     * @see MyColorPicker
     */
    public MyScene(Parent parent, double v1, double v2, MyColorPicker colorPicker) {
        super(parent,v1,v2);
        MyScene.colorPicker =colorPicker;

        addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> MoveColorPicker(parent,mouseEvent));
        addEventFilter(Event.ANY,event -> ResizePolygons(parent));
    }
}
