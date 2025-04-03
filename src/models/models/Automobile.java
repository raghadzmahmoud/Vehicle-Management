package models;

import core.GearType;

public class Automobile {
    private String manufactureCompany;
    private String manufactureDate;
    private String model;
    private Engine engine;
    private int plateNum;
    private GearType gearType;
    private int bodySerialNum;

    public Automobile() {}

    public Automobile(String manufactureCompany, String manufactureDate, String model, Engine engine, int plateNum, GearType gearType, int bodySerialNum) {
        this.manufactureCompany = manufactureCompany;
        this.manufactureDate = manufactureDate;
        this.model = model;
        this.engine = engine;
        this.plateNum = plateNum;
        this.gearType = gearType;
        this.bodySerialNum = bodySerialNum;
    }

    // Getters and setters
    public String getManufactureCompany() {
        return manufactureCompany;
    }

    public void setManufactureCompany(String manufactureCompany) {
        this.manufactureCompany = manufactureCompany;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(int plateNum) {
        this.plateNum = plateNum;
    }

    public GearType getGearType() {
        return gearType;
    }

    public void setGearType(GearType gearType) {
        this.gearType = gearType;
    }

    public int getBodySerialNum() {
        return bodySerialNum;
    }

    public void setBodySerialNum(int bodySerialNum) {
        this.bodySerialNum = bodySerialNum;
    }
}
