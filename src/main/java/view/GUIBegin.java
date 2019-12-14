/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 *
 * @author giovanniflores
 */
public class GUIBegin {

    GridPane layout;
    Button begin;
    Label prompt;
    Label min;
    TextField betAmountField;
    Stage stage;

    public GUIBegin(Stage st) {
        this.stage = st;
        layout = new GridPane();
        layout.paddingProperty().set(new Insets(5, 5, 5, 5));

        for (int column = 0; column < 16; column++) {
            ColumnConstraints c = new ColumnConstraints();
            c.setHgrow(Priority.SOMETIMES);
            c.setMinWidth(10.00);
            c.setPrefWidth(100.00);
            layout.getColumnConstraints().add(c);
        }
        for (int row = 0; row < 14; row++) {
            RowConstraints c = new RowConstraints();
            c.setVgrow(Priority.SOMETIMES);
            c.setMinHeight(10.00);
            c.setPrefHeight(30.00);
            layout.getRowConstraints().add(c);
        }
        setLabel();
        setField();
        setButton();

        begin.setOnAction(e -> {

            GUISlotMachine.setTotal(Integer.parseInt(betAmountField.getText()));
            GUISlotMachine gsm = new GUISlotMachine(stage);
            stage.setScene(gsm.getSc());

            stage.show();
        });
        layout.styleProperty().set("-fx-background-image : url('casinoBackground2.jpg');");

        layout.getChildren().addAll(prompt, min, betAmountField, begin);

        stage.titleProperty().set("Nought Machine");
        Scene sc = new Scene(this.layout, 800, 600);
        sc.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet.css").toExternalForm());
        stage.setScene(sc);
        stage.show();
    }

    private void setLabel() {

        this.prompt = new Label("Enter $ Amount:");
        prompt.idProperty().set("labelResult");
        GridPane.setConstraints(prompt, 6, 6, 4, 4);

        this.min = new Label("(minimum 300)");
        min.idProperty().set("labelResult");
        GridPane.setConstraints(min, 6, 7, 4, 4);

    }

    private void setField() {
        this.betAmountField = new TextField();
        betAmountField.idProperty().set("field");
        GridPane.setConstraints(betAmountField, 6, 8, 4, 4);
    }

    private void setButton() {

        this.begin = new Button("Begin");
        this.begin.idProperty().set("buttons");
        GridPane.setConstraints(begin, 6, 10, 4, 4);

    }
}
