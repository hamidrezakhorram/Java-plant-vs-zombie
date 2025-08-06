package Model.zmobies;

import javafx.scene.image.ImageView;

public class Zombie implements Cloneable {
    private String name;
    private int health;
    private String gifUrl;
    private int movementSpeed;
    private int destructionPower;

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getDestructionPower() {
        return destructionPower;
    }

    public void setDestructionPower(int destructionPower) {
        this.destructionPower = destructionPower;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    @Override
    public Zombie clone() throws CloneNotSupportedException {
        Zombie cloned = (Zombie) super.clone();
        return cloned;
    }
}
