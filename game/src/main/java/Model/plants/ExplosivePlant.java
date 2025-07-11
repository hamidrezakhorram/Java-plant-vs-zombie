package Model.plants;

public class ExplosivePlant extends Plant {
    private int destructionPower;
    private int destructionArea;

    public int getDestructionPower() {
        return destructionPower;
    }

    public void setDestructionPower(int destructionPower) {
        this.destructionPower = destructionPower;
    }

    public int getDestructionArea() {
        return destructionArea;
    }

    public void setDestructionArea(int destructionArea) {
        this.destructionArea = destructionArea;
    }
}
