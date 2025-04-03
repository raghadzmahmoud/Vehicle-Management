package models;

import core.GearType;

public class Vehicle extends Automobile {
    private int length;
    private int width;
    private String color;

    public Vehicle() {}

    public Vehicle(String manufactureCompany, String manufactureDate, String model, Engine engine, int plateNum, GearType gearType, int bodySerialNum, int length, int width, String color) {
        super(manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum);
        this.length = length;
        this.width = width;
        this.color = color;
    }

    // Getters and setters
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
