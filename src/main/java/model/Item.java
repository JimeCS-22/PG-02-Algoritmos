package model;

public class Item {

    private final String name;
    private final int weight;
    private final int value;

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public double getRatio(){ return (double) value / weight; }

    @Override
    public String toString() {
        return String.format("%s: (v = %d, w = %d , r=%.2f%)", name, weight, value, value / weight);
    }
}
