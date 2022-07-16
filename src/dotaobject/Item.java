package dotaobject;

import main.DotaJsonParser;

import java.awt.image.BufferedImage;


public class Item {
    public Item(String name, String localizedName, int id, int cost, String imageURL) {
        this.name = name;
        this.localizedName = localizedName;
        this.id = id;
        this.cost = cost;
        this.imageURL = imageURL;
    }

    private String name;
    private String localizedName;
    private int id;
    private int cost;
    private String imageURL;
    private BufferedImage img = null;

    public boolean imageLoaded() {
        return (this.img != null);
    }

    public BufferedImage loadImage() {
        if (this.img != null) return this.img;
        return this.img = DotaJsonParser.findImage("src/item_images", this.id + "img.png", this.imageURL);
    }


    @Override
    public String toString() {
        return this.name;
    }
}
