package models;

import core.GearType;

public class Truck extends Vehicle {
    private double freeWeight;
    private double fullWeight;


    public Truck(String manufactureCompany, String manufactureDate, String model, Engine engine, int plateNum, GearType gearType, int bodySerialNum, int length, int width, String color, double freeWeight, double fullWeight) {
        super(manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum, length, width, color);
        this.freeWeight = freeWeight;
        this.fullWeight = fullWeight;
    }

    // Getters and setters
    public double getFreeWeight() {
        return freeWeight;
    }

    public void setFreeWeight(double freeWeight) {
        this.freeWeight = freeWeight;
    }

    public double getFullWeight() {
        return fullWeight;
    }

    public void setFullWeight(double fullWeight) {
        this.fullWeight = fullWeight;
    }
}
