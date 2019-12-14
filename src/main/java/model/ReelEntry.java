/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author giovanniflores
 */
public class ReelEntry implements ISlot {

    private String imagePath;
    private int value;

    public ReelEntry(String imagePath, int value) {
        this.imagePath = imagePath;
        this.value = value;
    }

    

    
    @Override
    public void setImage(String image) {
        this.imagePath = image;
    }

    @Override
    public String getImage() {
        return this.imagePath;
    }

    @Override
    public void setValue(int val) {
        this.value = val;
    }

    @Override
    public int getValue() {
        return this.value;

    }

    public boolean checkEquality(ReelEntry nm) {
        return this.value == nm.value;
    }

}
