package model;

import java.awt.*;

public class Item {

    private final String name;
    private final int weight;
    private final int value;
    private final Color color;//Color visual para el Canvas

    public Item(String name, int weight, int value, Color color) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.color = color;
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
        return String.format("%s: (v = %d, w = %d , r=%.2f)", name, weight, value, getRatio());
    }

    // ── Predefined example sets for Item class ───────────────────────────────────────────────
    public static Item[] Package1() {
        return new Item[]{
                new Item("Smart TV 65 pulg 4k", 20, 1000, Color.BLUE),
                new Item("PS5", 2, 600, Color.CYAN),
                new Item("Libro Java", 1, 20,Color.green),
                new Item("Samsung Galaxy", 1, 700,Color.ORANGE),
                new Item("Huawei", 1, 400,Color.PINK),
                new Item("Libro C++", 1, 25,Color.magenta),
                new Item("Xbox One", 2, 500,Color.yellow),
                new Item("Drone", 3, 500,Color.lightGray),
                new Item("Proyector", 3, 200,Color.red),
                new Item("LapTop", 3, 800,Color.darkGray),
                new Item("Impresora 3D", 4, 800,Color.pink),
                new Item("iPhone", 1, 800,Color.cyan),
        };
    }

    public static Item[] Package2() {
        return new Item[]{
                new Item("Laptop",    3, 4,Color.cyan),
                new Item("Libro",     1, 3,Color.pink),
                new Item("Cámara",    2, 5,Color.red),
                new Item("Tablet",    2, 4,Color.magenta),
                new Item("Audífonos", 1, 2,Color.yellow),
                new Item("Cargador",  1, 1,Color.green),
                new Item("Mochila",   4, 6,Color.blue),
        };
    }

    public static Item[] Package3() {
        return new Item[]{
                new Item("A", 2, 3,Color.LIGHT_GRAY),
                new Item("B", 3, 4,Color.blue),
                new Item("C", 4, 5,Color.YELLOW),
                new Item("D", 5, 6,Color.PINK),
        };
    }

    public static Item[] Package4() {
        return new Item[]{
                new Item("Item-1", 1, 1,Color.RED),
                new Item("Item-2", 2, 6,Color.BLUE),
                new Item("Item-3", 3, 10,Color.WHITE),
                new Item("Item-4", 5, 16,Color.YELLOW),
        };
    }
}
