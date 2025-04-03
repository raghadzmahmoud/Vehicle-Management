package models;

import core.GearType;

public class Car extends Vehicle {
    private int chairNum;
    private boolean isFurnitureLeather;

    public Car() {}
    public Car(String manufactureCompany, String manufactureDate, String model, Engine engine, int plateNum, GearType gearType, int bodySerialNum, int length, int width, String color, int chairNum, boolean isFurnitureLeather) {
        super(manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum, length, width, color);
        this.chairNum = chairNum;
        this.isFurnitureLeather = isFurnitureLeather;
    }

    // Getters and setters
    public int getChairNum() {
        return chairNum;
    }

    public void setChairNum(int chairNum) {
        this.chairNum = chairNum;
    }

    public boolean isFurnitureLeather() {
        return isFurnitureLeather;
    }

    public void setFurnitureLeather(boolean isFurnitureLeather) {
        this.isFurnitureLeather = isFurnitureLeather;
    }
}
