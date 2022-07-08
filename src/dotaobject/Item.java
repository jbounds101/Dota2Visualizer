package dotaobject;

import java.awt.image.BufferedImage;

public class Item {
    public Item(String name, String localizedName, int id, int cost, BufferedImage img) {
        this.name = name;
        this.localizedName = localizedName;
        this.id = id;
        this.cost = cost;
        this.img = img;
    }

    private String name;
    private String localizedName;
    private int id;
    private int cost;
    private BufferedImage img;

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
