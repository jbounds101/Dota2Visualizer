package dotaobject;

import java.awt.image.BufferedImage;

public class Hero {

    public Hero(int id, String localizedName, String primaryAttribute, String attackType, String[] roles,
                BufferedImage img, BufferedImage icon, int baseHealth, double baseHealthRegen, int baseMana,
                double baseManaRegen, int baseArmor, int baseStr, int baseAgi, int baseInt, double strGain,
                double agiGain, double intGain, int attackRange, double attackRate, int moveSpeed,
                boolean captainsMode) {
        this.id = id;
        this.localizedName = localizedName;
        this.primaryAttribute = primaryAttribute;
        this.attackType = attackType;
        this.roles = roles;
        this.img = img;
        this.icon = icon;
        this.baseHealth = baseHealth;
        this.baseHealthRegen = baseHealthRegen;
        this.baseMana = baseMana;
        this.baseManaRegen = baseManaRegen;
        this.baseArmor = baseArmor;
        this.baseStr = baseStr;
        this.baseAgi = baseAgi;
        this.baseInt = baseInt;
        this.strGain = strGain;
        this.agiGain = agiGain;
        this.intGain = intGain;
        this.attackRange = attackRange;
        this.attackRate = attackRate;
        this.moveSpeed = moveSpeed;
        this.captainsMode = captainsMode;
    }

    private final int id;
    private final String localizedName;
    private final String primaryAttribute;
    private final String attackType;
    private final String[] roles;
    private final BufferedImage img;
    private final BufferedImage icon;
    private final int baseHealth;
    private final double baseHealthRegen;
    private final int baseMana;
    private final double baseManaRegen;
    private final int baseArmor;
    private final int baseStr;
    private final int baseAgi;
    private final int baseInt;
    private final double strGain;
    private final double agiGain;
    private final double intGain;
    private final int attackRange;
    private final double attackRate;
    private final int moveSpeed;
    private final boolean captainsMode;

    @Override
    public String toString() {
        return this.localizedName;
    }

}
