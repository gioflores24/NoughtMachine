/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nought;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.GUIBegin;

/**
 *
 * @author giovanniflores
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        new GUIBegin(stage);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
