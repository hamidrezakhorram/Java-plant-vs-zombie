package Model.zmobies;

public class SpecialZombie extends Zombie {
    private String specialAbility;
    private int changeablePower;
    private int changeableSpeed;

    public String getSpecialAbility() {
        return specialAbility;
    }

    public void setSpecialAbility(String specialAbility) {
        this.specialAbility = specialAbility;
    }

    public int getChangeablePower() {
        return changeablePower;
    }

    public void setChangeablePower(int changeablePower) {
        this.changeablePower = changeablePower;
    }

    public int getChangeableSpeed() {
        return changeableSpeed;
    }

    public void setChangeableSpeed(int changeableSpeed) {
        this.changeableSpeed = changeableSpeed;
    }
}
