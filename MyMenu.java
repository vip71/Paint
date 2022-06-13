package com.example.lista5;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * Pane containing displayed objects
 */
public class MyMenu extends AnchorPane {

    /**
     * Buttons that set type of shape that is going to be drawn
     */
    private static class MyButton extends Button {
        /** Constructor for buttons that change type of shape to draw*/
        MyButton(String s,int i){
            super(s);
            setFont(Font.font("Verdana", 15));
            setOnAction(event -> Cloth.usedFigureIndex = i);
        }
        /** Constructor for buttons that adds DialoguePanel*/
        MyButton(String s,MyDialoguePane dialoguePane){
            super(s);
            setFont(Font.font("Verdana", 15));
            setOnAction(event->dialoguePane.showAndWait());
        }
    }

    /**
     * TextField that sets number of angles for drawn polygons
     */
    private static class MyTextField extends TextField {
        public MyTextField() {
            setFont(Font.font("Verdana", 15));
            setOnAction(event -> Cloth.SetAngles(getText()));
        }
    }

    /**
     * Class of dialogue pane that contains information and shows when "info" is clicked
     */
    private static class MyDialoguePane extends Dialog{
        MyDialoguePane(){
            super();
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            getDialogPane().getButtonTypes().add(type);
            setContentText(
                    """
                            Witaj w Paintcie\s
                            Wybierz figure do rysowania przyciskami
                            Jesli zmienic liczbe katow w n-kacie to wpisz ja do pola na gorze
                            Figury rysuje sie poprzez naciscniecie pustej przestrzeni na tle
                            Figure mozna oznaczyc jako aktywna porzez nacisniecie jej myszka
                            Pozycje aktywnej figury mozna zmienic przenoszac zielona kropke
                            Rozmiar/krztalt aktywnej figury mozna zmienic przenoszac niebieskie kropki
                            Aktywna figure mozna obrocic scrollem gdy mysz na nia najezdza
                            Kolor aktywnej figury mozna zmienic naciskajac prawym przyciskiem na nia
                            
                            Program powstal na zajecia, slozy do rysowania
                            Autor Bartosz Tatys"""
            );
        }
    }

    /**
     * Constructor for MyMenu
     */
    MyMenu() {
        MyColorPicker colorPicker = new MyColorPicker();
        getChildren().add(colorPicker);

        MyScene scene = new MyScene(this,640,480,colorPicker);

        Cloth cloth = new Cloth(this);
        getChildren().add(cloth);

        MyDialoguePane myDialoguePane=new MyDialoguePane();
        Button howToUseButton = new MyButton("Info",myDialoguePane);
        Button circleButton = new MyButton("Kolo",0);
        Button rectangleButton = new MyButton("Prostokat",1);
        Button regularButton = new MyButton("N-kat Foremny",2);
        Button triangleButton = new MyButton("N-kat",3);
        TextField textField = new MyTextField();

        HBox hBox=new HBox(howToUseButton,circleButton,rectangleButton,regularButton,triangleButton,textField);
        hBox.setSpacing(5);
        getChildren().add(hBox);

    }
}
