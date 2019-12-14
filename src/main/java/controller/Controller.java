/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import model.ReelEntry;
import model.Reel;
import view.GUISlotMachine;

/**
 *
 * @author giovanniflores
 */
public class Controller {

    private volatile ReelEntry symbolReel1, symbolReel2, symbolReel3;
    private Reel reel1 = new Reel(false);
    private Reel reel2 = new Reel(true);
    private Reel reel3 = new Reel(false, true);

    private volatile boolean alreadySpinning;

    public boolean isAlreadySpinning() {
        return this.alreadySpinning;
    }

    public int multiplier(int val, String slot2Icon) {
        switch (slot2Icon) {
            case "oneIcon.png":
                val = -1;
                break;
            case "tenIcon.jpeg":
                val = -10;
                break;
            case "hundredIcon.gif":
                val = -100;
                break;

        }
        return val;
    }

    public int update(GUISlotMachine view, int val, ImageView slot1, ImageView slot2) {
        String url = slot1.getImage().getUrl();

        String sub = url.substring(url.lastIndexOf("/") + 1);
        switch (sub) {
            case "negativeOneIcon.png":
                val = -1;
                break;
            case "negativeTwoIcon.png":
                val = -2;
                break;
            case "negativeThreeIcon.png":
                val = -3;
                break;
            case "zeroIcon.png":
                val = 0;
                break;
            case "oneIcon.png":
                val = 1;
                break;
            case "twoIcon.png":
                val = 2;
                break;
            case "threeIcon.png":
                val = 3;
                break;

        }
        url = slot2.getImage().getUrl();
        sub = url.substring(url.lastIndexOf("/") + 1);

        if (val != 0) {
            switch (sub) {
                case "oneIcon.png":
                    val *= 1;
                    break;
                case "tenIcon.jpeg":
                    val *= 10;
                    break;
                case "hundredIcon.gif":
                    val *= 100;
                    break;

            }
        } else {
            view.zeroCase(val, sub);

        }
        return val;
    }

    public ReelEntry getSymbolReel1() {
        return symbolReel1;
    }

    public ReelEntry getSymbolReel2() {
        return symbolReel2;
    }

    public ReelEntry getSymbolReel3() {
        return symbolReel3;
    }

    public Reel getReel1() {
        return reel1;
    }

    public Reel getReel2() {
        return reel2;
    }

    public Reel getReel3() {
        return reel3;
    }

    public void setReel1(Reel reel1) {
        this.reel1 = reel1;
    }

    public void setReel2(Reel reel2) {
        this.reel2 = reel2;
    }

    public void setReel3(Reel reel3) {
        this.reel3 = reel3;
    }

    public void setAlreadySpinning(boolean alreadySpinning) {
        this.alreadySpinning = alreadySpinning;
    }

    public void setSymbolReel3(ReelEntry symbolReel3) {
        this.symbolReel3 = symbolReel3;
    }

    public void setSymbolReel2(ReelEntry symbolReel2) {
        this.symbolReel2 = symbolReel2;
    }

    public void setSymbolReel1(ReelEntry symbolReel1) {
        this.symbolReel1 = symbolReel1;
    }

}
