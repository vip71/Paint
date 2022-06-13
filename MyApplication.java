package com.example.lista5;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class extending application
 * @author Bartosz Tatys
 * @version 1.0
 */
public class MyApplication extends Application{

    /**
     * Method that adds panel
     * @param stage stage
     * @see MyMenu
     */
    @Override
    public void start(Stage stage) {

        MyMenu root=new MyMenu();
        stage.setTitle("Zadanie lista 5");
        stage.setScene(root.getScene());
        stage.show();
    }

    /**
     * Method that lunches program
     * @param args main function arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}