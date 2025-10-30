package org.solvd.company.equipment;

public class Laptop {

    private Long id;
    private String brand;
    private String model;
    private String color;

    public Laptop(Long id, String brand, String model, String color) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", firmName='" + brand + '\'' +
                ", mode='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirmName() {
        return brand;
    }

    public void setFirmName(String firmName) {
        this.brand = firmName;
    }

    public String getMode() {
        return model;
    }

    public void setMode(String mode) {
        this.model = mode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
