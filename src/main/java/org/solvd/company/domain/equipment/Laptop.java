package org.solvd.company.domain.equipment;

public class Laptop {

    private Long id;
    private String brand;
    private String color;
    private String name;

    public Laptop(Long id, String name, String brand, String model, String color) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.name = name;
    }

    public Laptop() {

    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }
}
