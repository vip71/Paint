package com.example.lista5;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

import static java.lang.Math.*;

/**
 * Shape that is drawn on Cloth
 */
public class MyPolygon extends Polygon{
    /** numbers that determine type of figure and number of its angels*/
    int figureTypeIndex,angles;
    /** parameters determining weather the shape is pointed/active */
    boolean isPointed,isActive;
    /** save of coordinates of points witch being dragged*/
    double[] draggableArmsCords;
    /** grip for shape */
    DraggablePoint draggableCenter;
    /** grip for shapes angles*/
    DraggablePoint[] draggableArm;

    /**
     * Method that creates grips for Polygon
     * @see DraggablePoint
     */
    private void AddDraggableArms(double x,double y){
        draggableArm=new DraggablePoint[angles];
        draggableCenter = new DraggablePoint(x, y);
        draggableCenter.setFill(Color.GREEN);
        draggableArmsCords =new double[angles*2];

        for(int i=0;i<angles;i++) {
            double d=(2 * i+1) * PI / angles;
            getPoints().add(x + 100 * cos(d));
            getPoints().add(y - 100 * sin(d));
            draggableArm[i]=new DraggablePoint(x + 100 * cos(d),y - 100 * sin(d));
            setDraggableArmsCords(i);
        }
    }

    /**
     * Method that saves coordinates of angles of shape
     * @param i i
     */
    public void setDraggableArmsCords(int i){
        draggableArmsCords[2*i]=-(draggableCenter.getCenterX()-draggableArm[i].getCenterX());
        draggableArmsCords[2*i+1]=-(draggableCenter.getCenterY()-draggableArm[i].getCenterY());
    }

    /**
     * Method that sets position od angle as saved
     * @param x x
     * @param y y
     */
    public void setPosition(double x,double y){
        for(int i=0;i<angles;i++) {
            getPoints().set(2*i,x+ draggableArmsCords[2*i]);
            getPoints().set(2*i+1,y+ draggableArmsCords[2*i+1]);
        }
    }

    /** Method that checks weather any draggableArm is pointed */
    private boolean anyArmPointed(){
        for(int i=0;i<angles;i++) if(draggableArm[i].isPointed) return true;
        return false;
    }

    /**
     * Method that sets Position of angels as saved depending on type of figure
     * @see DraggablePoint
     */
    private void setPointsPosition(){
        if(figureTypeIndex==1){
                int a = 1,b;
                for (int i = 0; i < angles; i++) {
                    if (i > 1) a = -1;
                    if (i % 3 == 0) b = 1; else b = -1;
                    draggableArm[i].setCenterX((draggableCenter.getCenterX() + draggableArmsCords[0] * a));
                    draggableArm[i].setCenterY((draggableCenter.getCenterY() + draggableArmsCords[1] * b));
                }
            }
        else if(figureTypeIndex==2){
                for(int i=0;i<angles;i++){
                    draggableArm[i].setCenterX(draggableCenter.getCenterX() + sqrt(draggableArmsCords[0] * draggableArmsCords[0] + draggableArmsCords[1] * draggableArmsCords[1]) * Math.cos((2 * i) * PI / angles - PI / angles));
                    draggableArm[i].setCenterY(draggableCenter.getCenterY() + sqrt(draggableArmsCords[0] * draggableArmsCords[0] + draggableArmsCords[1] * draggableArmsCords[1]) * Math.sin((2 * i) * PI / angles - PI / angles));
                }
            }
        else if(figureTypeIndex==3){
                for (int i = 0; i < angles; i++) {
                    draggableArm[i].setCenterX(draggableCenter.getCenterX() + draggableArmsCords[2 * i]);
                    draggableArm[i].setCenterY(draggableCenter.getCenterY() + draggableArmsCords[2 * i + 1]);
                }
            }
        }

    /**
     * Method that enables figure to be resized
     * @see DraggablePoint
     */
    public void AddResize(){
            if(draggableCenter.isDragged){
                setPosition(draggableCenter.getCenterX(),draggableCenter.getCenterY());
                setPointsPosition();
            }
                for(int i=0;i< angles;i++) setDraggableArmsCords(i);
                setPosition(draggableCenter.getCenterX(),draggableCenter.getCenterY());
                setPointsPosition();
    }

    /**
     * Method that rotates figure on scroll
     * @param scrollEvent scrollEvent
     */
    private void doRotate(ScrollEvent scrollEvent){
        if(isActive&&isPointed) {
            Rotate rotate=new Rotate(draggableCenter.getRotate()+2, draggableArmsCords[0], draggableArmsCords[1]);
            if(scrollEvent.getDeltaY()<0) rotate=new Rotate(getRotate()-2, draggableArmsCords[0], draggableArmsCords[1]);
            rotate.setPivotX(draggableCenter.getCenterX());
            rotate.setPivotY(draggableCenter.getCenterY());

            getTransforms().add(rotate);
            draggableCenter.getTransforms().add(rotate);
            for(int i=0;i<angles;i++) {
                draggableArm[i].getTransforms().add(rotate);
                setDraggableArmsCords(i);
            }

            setPointsPosition();
        }
    }

    /** Method that deactivates MyPolygon instance */
    void Deactivate(){
        if(!draggableCenter.isPointed&&!anyArmPointed()){
            isActive=false;
            draggableCenter.setVisible(false);
            for(int i=0;i<angles;i++) draggableArm[i].setVisible(false);
        }
    }

    /**
     * Method that activates MyPolygon instance
     * @param mouseEvent mouseEvent
     */
    private void doActivate(MouseEvent mouseEvent){
            MouseButton button = mouseEvent.getButton();
            if (button == MouseButton.PRIMARY) {
                isActive=true;
                draggableCenter.setVisible(true);
                for(int i=0;i<angles;i++) draggableArm[i].setVisible(true);
            }
    }

    /** Method used to hide unused DraggablePoints */
    private void hideArms(){
        if(figureTypeIndex==2) draggableArm[0].canMoveY=false;
        if(figureTypeIndex!=3)for(int i=1;i<angles;i++) {
            draggableArm[i].draggable=false;
            draggableArm[i].setRadius(0);
        }
    }

    /**
     * Constructor for my Polygon
     * @param angles number of angles
     * @param x x coordinate of centre
     * @param y y coordinate of centre
     * @param figureTypeIndex indexThat determines type of figure
     * @see MyScene
     * @see DraggablePoint
     */
    MyPolygon(int angles, double x, double y,int figureTypeIndex){
        this.figureTypeIndex=figureTypeIndex;
        this.angles=angles;

        AddDraggableArms(x,y);

        Deactivate();

        setOnMouseExited(event -> isPointed=false);

        setOnMouseEntered(event -> isPointed=true);

        setOnScroll(this::doRotate);

        setOnMouseClicked(this::doActivate);

        hideArms();

    }
}
