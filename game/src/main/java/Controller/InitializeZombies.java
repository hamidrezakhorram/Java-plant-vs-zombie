package Controller;

import Model.zmobies.SpecialZombie;
import Model.zmobies.StrongZombie;
import Model.zmobies.Zombie;

public class InitializeZombies {

    public static Zombie simpleZombie(){
        Zombie zombie = new Zombie();
        zombie.setName("simpleZombie");
        zombie.setGifUrl("/assesst/SimpleZombie.gif");
        zombie.setHealth(50);
        zombie.setMovementSpeed(10);
        zombie.setDestructionPower(5);
        return zombie;
    }

    public static StrongZombie coneheadZombie(){
        StrongZombie zombie = new StrongZombie();
        zombie.setName("coneheadZombie");
        zombie.setGifUrl("/assesst/ConeheadZombie.gif");
        zombie.setHealth(50);
        zombie.setAttackResistance(50);
        zombie.setMovementSpeed(10);
        zombie.setDestructionPower(5);
        return zombie;
    }

    public static   StrongZombie screenDoorZombie(){
        StrongZombie zombie = new StrongZombie();
        zombie.setName("screenDoorZombie");
        zombie.setGifUrl("/assesst/ScreenDoorZombie.gif");
        zombie.setHealth(50);
        zombie.setMovementSpeed(5);
        zombie.setDestructionPower(5);
        zombie.setAttackResistance(100);
        return zombie;
    }

    public static SpecialZombie flagZombie(){
        SpecialZombie zombie = new SpecialZombie();
        zombie.setName("flagZombie");
        zombie.setGifUrl("/assesst/FlagZombie.gif");
        zombie.setHealth(70);
        zombie.setMovementSpeed(10);
        zombie.setDestructionPower(5);
        zombie.setChangeablePower(10);
        return zombie;
    }
    public static SpecialZombie newspaperZombie(){
        SpecialZombie zombie = new SpecialZombie();
        zombie.setName("newspaperZombie");
        zombie.setGifUrl("/assesst/NewspaperZombie.gif");
        zombie.setHealth(50);
        zombie.setMovementSpeed(5);
        zombie.setDestructionPower(5);
        zombie.setChangeablePower(30);
        zombie.setSpecialAbility("withNewspaper");
        return zombie;
    }

}
