package DTO;

import Manager.InputValidation;

import java.util.Objects;

public class Fruit implements Comparable<Fruit> {
    private final String id;
    private final String name;
    private final double price;
    int quantity;
    private final String origin;

    public Fruit(String id, String name, double price, int quantity, String origin) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.origin = origin;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fruit)) return false;
        Fruit fruit = (Fruit) o;
        return getId().equals(fruit.getId()) ||
                getName().equals(fruit.getName()) &&
                getOrigin().equals(fruit.getOrigin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getOrigin());
    }

    @Override
    public int compareTo(Fruit o) {
        return this.getId().compareTo(o.getId());
    }
}
