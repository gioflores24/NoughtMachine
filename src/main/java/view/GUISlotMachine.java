/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.nought.Launcher;
import controller.Controller;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.GameState;
import model.ReelEntry;
import model.Reel;

/**
 *
 * @author giovanniflores
 */
public class GUISlotMachine {

    private final GridPane layout;
    private boolean guessed;
    public ImageView slot1, slot2, slot3;
    private final Controller controller;
    private Button spinButton, flipButton, clearButton;
    private int coinValue = -1;
    private static int total;
    Scene sc;
    Stage stage;

    private GameState gameState;

    private Label slot1Label, slot2Label, slot3Label, resultLabel, totalLabel;

    public static void setTotal(int t) {

        if (t < 300) {
            total = 300;
        } else {
            total = t;
        }

    }

    public GUISlotMachine(Stage stage) {
        gameState = GameState.NORMAL;
//        this.total = 0;

        this.stage = stage;
        layout = new GridPane();
        controller = new Controller();
        layout.paddingProperty().set(new Insets(5, 5, 5, 5));
        layout.styleProperty().set("-fx-background-image : url('casinoBackground2.jpg');");

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

        setImages();
        setLabels();
        setButtons();
        setClearButton();
        initializeActions();
        this.layout.getChildren().addAll(slot1Label, slot2Label, spinButton, resultLabel, this.totalLabel, this.clearButton);

        stage.titleProperty().set("Nought Machine");
        sc = new Scene(this.layout, 800, 600);
        sc.getStylesheets().add(getClass().getClassLoader().getResource("stylesheet.css").toExternalForm());
        stage.setScene(sc);
        stage.show();

    }

    private void setClearButton() {
        clearButton = new Button("Reset");
        this.clearButton.idProperty().set("resetButton");
        GridPane.setConstraints(clearButton, 1, 10, 4, 4);

        this.clearButton.setOnMouseClicked(event -> {
            stage.close();
            stage = new Stage();
            new GUIBegin(stage);

        });

    }

    public Scene getSc() {
        return sc;
    }

    private void setImages() {

        slot1 = new ImageView(controller.getReel1().getStartingNumber().getImage());
        slot1.setFitWidth(150);
        slot1.setFitHeight(150);

        slot2 = new ImageView(controller.getReel2().getStartingNumber().getImage());
        slot2.setFitWidth(150);
        slot2.setFitHeight(150);

        slot3 = new ImageView(controller.getReel3().getStartingNumber().getImage());
        slot3.setFitWidth(150);
        slot3.setFitHeight(150);

    }

    private void setLabels() {
        this.slot1Label = new Label();
        slot1Label.idProperty().set("reel");
        GridPane.setConstraints(slot1Label, 1, 4, 4, 4);
        slot1Label.graphicProperty().set(slot1);

        this.slot2Label = new Label();
        slot2Label.idProperty().set("reel");
        GridPane.setConstraints(slot2Label, 6, 4, 4, 4);
        slot2Label.graphicProperty().set(slot2);

        this.slot3Label = new Label();
        slot3Label.idProperty().set("reel");
        GridPane.setConstraints(slot3Label, 11, 4, 4, 4);
        slot3Label.graphicProperty().set(slot3);

        this.resultLabel = new Label();
        resultLabel.idProperty().set("labelResult");
        GridPane.setConstraints(resultLabel, 6, 7, 4, 4);

        this.totalLabel = new Label("Total: " + this.total);
        totalLabel.idProperty().set("labelTotal");
        GridPane.setConstraints(totalLabel, 1, 1, 4, 4);

    }

    private void setButtons() {

        createSpinButton();
        createFlipButton();

    }

    private void createSpinButton() {
        this.spinButton = new Button("SPIN");
        this.spinButton.idProperty().set("buttons");
        GridPane.setConstraints(spinButton, 5, 10, 6, 2);
    }

    private void createFlipButton() {
        this.flipButton = new Button("FLIP");
        this.flipButton.idProperty().set("buttons");
        GridPane.setConstraints(flipButton, 5, 10, 6, 2);
    }

    private void initializeActions() {
        spinButton.setOnMouseClicked(click -> {
            spinButton.disableProperty().set(true);
            controller.setAlreadySpinning(true);

            controller.setReel1(new Reel(false) {
                @Override
                public void run() {
                    spinReels(controller.getReel1(), slot1, 1);
                }
            });
            controller.setReel2(new Reel(true) {
                @Override
                public void run() {
                    spinReels(controller.getReel2(), slot2, 2);
                }
            });
//            controller.setReel3(new Reel(false, true) {
//                @Override
//                public void run() {
//                    spinReels(controller.getReel3(), slot3, 3);
//                }
//            });

            controller.getReel1().setDaemon(true);
            controller.getReel2().setDaemon(true);
            // controller.getReel3().setDaemon(true);

            controller.getReel1().start();
            controller.getReel2().start();
            // controller.getReel3().start();

        });

        slot1Label.setOnMouseClicked(click -> {
            if (gameState != GameState.FLIPPING) {
                stopSpin();
            }

        });
        slot2Label.setOnMouseClicked(click -> {
            if (gameState != GameState.FLIPPING) {
                stopSpin();
            }

        });

    }

    private void spinReels(Reel reel, ImageView iv, int x) {
        reel.setReel(reel.spin());

        while (controller.isAlreadySpinning()) {
            for (ReelEntry num : reel.getReel()) {
                Platform.runLater(() -> {
                    if (controller.isAlreadySpinning()) {
                        iv.imageProperty().set(new Image(num.getImage()));
                        switch (x) {
                            case 1:
                                controller.setSymbolReel1(num);
                                break;
                            case 2:
                                controller.setSymbolReel2(num);
                                break;
                            case 3:
                                controller.setSymbolReel3(num);
                                break;

                        }
                    }
                });
                try {
                    Reel.sleep(80);

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }

        }
    }

    private void stopSpin() {
        if (controller.isAlreadySpinning()) {
            controller.setAlreadySpinning(false);
            int slotValue = getSlotValue();
            if (slotValue > 0) {
                resultLabel.textProperty().set("You won " + String.valueOf(slotValue));

            } else if (slotValue < 0) {
                resultLabel.textProperty().set("You lost " + String.valueOf(slotValue));
            } else {
                resultLabel.textProperty().set("Zero time!");
            }
            this.total += slotValue;
            totalLabel.textProperty().set("Total: " + this.total);

            spinButton.disableProperty().set(false);
        }
    }

    private void stopFlip() {
        if (controller.isAlreadySpinning()) {
            controller.setAlreadySpinning(false);
            spinButton.disableProperty().set(false);

        }
    }

    private int getSlotValue() {
        int val = 0;
        return controller.update(this, val, slot1, slot2);
    }

    public void zeroCase(int val, String slot2Icon) {

        this.guessed = false;
        Label pickLabel = new Label("Pick one");
        pickLabel.idProperty().set("labelPrompt");

        Button heads = new Button("Heads");
        heads.idProperty().set("coinButtons");
        Button tails = new Button("Tails");
        tails.idProperty().set("coinButtons");
        heads.setOnAction(click -> {
            this.coinValue = 0;
            gameState = GameState.COIN_SIDE_CHOSEN;
            heads.disableProperty().set(true);
            tails.disableProperty().set(true);

        });
        tails.setOnAction(click -> {
            this.coinValue = 1;
            gameState = GameState.COIN_SIDE_CHOSEN;
            heads.disableProperty().set(true);
            tails.disableProperty().set(true);

        });
        GridPane.setConstraints(heads, 12, 1, 4, 4);
        GridPane.setConstraints(tails, 14, 1, 4, 4);

        GridPane.setConstraints(pickLabel, 11, 0, 4, 4);

        this.layout.getChildren().add(pickLabel);
        this.layout.getChildren().add(heads);
        this.layout.getChildren().add(tails);

        this.layout.getChildren().add(slot3Label);
        createFlipButton();
        this.layout.getChildren().add(flipButton);

        flipButton.setOnMouseClicked(click -> {
            if (gameState == GameState.COIN_SIDE_CHOSEN) {
                flipButton.disableProperty().set(true);
                spinButton.disableProperty().set(true);
                controller.setAlreadySpinning(true);

                controller.setReel3(new Reel(false, true) {
                    @Override
                    public void run() {
                        spinReels(controller.getReel3(), slot3, 3);
                    }
                });

                controller.getReel3().setDaemon(true);

                controller.getReel3().start();
                gameState = GameState.FLIPPING;
            }

        });

        slot3Label.setOnMouseClicked(click -> {
            if (gameState == GameState.FLIPPING) {
                stopFlip();
                String url = slot3.getImage().getUrl();
                String sub = url.substring(url.lastIndexOf("/") + 1);
                switch (sub) {
                    case "heads.jpeg":
                        if (coinValue == 0) {
                            guessed = true;
                        }
                        break;
                    case "tails.jpeg":
                        if (coinValue == 1) {
                            guessed = true;
                        }
                        break;
                    default:
                        guessed = false;

                }
                if (guessed) {
                    resultLabel.textProperty().set("You broke even");
                } else {

                    int res = controller.multiplier(val, slot2Icon);

                    resultLabel.textProperty().set("You lost " + res);
                    this.total += res;
                    this.totalLabel.textProperty().set("Total: " + this.total);

                }
                this.layout.getChildren().remove(pickLabel);
                this.layout.getChildren().remove(flipButton);
                this.layout.getChildren().remove(slot3Label);
                this.layout.getChildren().remove(heads);
                this.layout.getChildren().remove(tails);
                coinValue = -1;
                gameState = GameState.NORMAL;
            }

        });

    }

}
