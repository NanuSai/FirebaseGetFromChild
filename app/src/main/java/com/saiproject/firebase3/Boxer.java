package com.saiproject.firebase3;

public class Boxer {

    private String boxerName;
    private int punchPower;
    private int punchSpeed;



    public Boxer(){}

    public Boxer(String boxerName, int punchPower, int punchSpeed) {
        this.boxerName = boxerName;
        this.punchPower = punchPower;
        this.punchSpeed = punchSpeed;
    }


    public String getBoxerName() {
        return boxerName;
    }

    public int getPunchPower() {
        return punchPower;
    }

    public int getPunchSpeed() {
        return punchSpeed;
    }




}
