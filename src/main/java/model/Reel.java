/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author giovanniflores
 */
public class Reel extends Thread {

    private Random rn;

    private volatile ReelEntry[] reel;
    private boolean isMultiplier;
    private boolean isCoin;

    public Reel(boolean isMultiplier) {
        this(isMultiplier, false);
    }

    /**
     *
     * @param isMultiplier
     * @param isCoin
     */
    public Reel(boolean isMultiplier, boolean isCoin) {
        this.isMultiplier = isMultiplier;
        this.isCoin = isCoin;
        rn = new Random();
        if (isCoin) {
            assert isMultiplier == false;
            createCoinReel();
        } else {
            createReel();
        }
    }

    /**
     * Used to separate between this and the regular 6-number reel
     */
    private void createCoinReel() {
        reel = new ReelEntry[]{
            new ReelEntry("heads.jpeg", 0),
            new ReelEntry("tails.jpeg", 1)
        };
    }

    /**
     *
     */
    private void createReel() {
        if (this.isMultiplier) {
            reel = new ReelEntry[]{
                new ReelEntry("tenIcon.jpeg", 10),
                new ReelEntry("oneIcon.png", 1),
                new ReelEntry("hundredIcon.gif", 100)

            };
        } else {
            reel = new ReelEntry[]{
                new ReelEntry("oneIcon.png", 1),
                new ReelEntry("negativeTwoIcon.png", -2),
                new ReelEntry("zeroIcon.png", 0),
                new ReelEntry("negativeOneIcon.png", -1),
                new ReelEntry("threeIcon.png", 3),
                new ReelEntry("negativeThreeIcon.png", -3),
                new ReelEntry("twoIcon.png", 2),};

        }
    }

    /**
     *
     * @return
     */
    public ReelEntry[] spin() {
        Collections.shuffle(Arrays.asList(this.reel));
        return this.reel;
    }

    /**
     *
     * @return
     */
    public ReelEntry getStartingNumber() {
        return reel[rn.nextInt(reel.length)];
    }

    public ReelEntry[] getReel() {
        return reel;
    }

    public void setReel(ReelEntry[] reel) {
        this.reel = reel;
    }

}
