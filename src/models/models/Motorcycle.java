package models;

import core.GearType;

public class Motorcycle extends Automobile {
    private double tierDiameter;
    private double length;

    public Motorcycle() {}

    public Motorcycle(String manufactureCompany, String manufactureDate, String model, Engine engine, int plateNum, GearType gearType, int bodySerialNum, double tierDiameter, double length) {
        super(manufactureCompany, manufactureDate, model, engine, plateNum, gearType, bodySerialNum);
        this.tierDiameter = tierDiameter;
        this.length = length;
    }

    // Getters and setters
    public double getTierDiameter() {
        return tierDiameter;
    }

    public void setTierDiameter(double tierDiameter) {
        this.tierDiameter = tierDiameter;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
