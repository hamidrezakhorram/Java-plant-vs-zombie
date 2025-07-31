package Controller;

import Model.plants.*;

public class PlantInitialize {
    public static WarriorPlant peashooter(){
        WarriorPlant plant = new WarriorPlant();
        plant.setGifUrl("/assesst/Peashooter.gif");
        plant.setHealth(100);
        plant.setBuildCost(100);
        plant.setAttackPower(5);
        plant.setAttackSpeed(10);
        plant.setName("peashooter");
        plant.setBulletType(BulletType.NORMAL);
        return plant;
    }

    public static WarriorPlant snowpea(){
        WarriorPlant plant = new WarriorPlant();
        plant.setGifUrl("/assesst/SnowPea.gif");
        plant.setHealth(100);
        plant.setBuildCost(175);
        plant.setAttackPower(5);
        plant.setAttackSpeed(5);
        plant.setName("snowpea");
        plant.setBulletType(BulletType.SNOWY);
        return plant;
    }

    public static WarriorPlant repeater(){
        WarriorPlant plant = new WarriorPlant();
        plant.setGifUrl("/assesst/Repeater.gif");
        plant.setHealth(100);
        plant.setBuildCost(200);
        plant.setAttackPower(5);
        plant.setAttackSpeed(10);
        plant.setName("repeater");
        plant.setBulletType(BulletType.NORMAL);
        return plant;
    }
    public static WarriorPlant puffShroom(){
        WarriorPlant plant = new WarriorPlant();
        plant.setGifUrl("/assesst/PuffShroom.gif");
        plant.setHealth(30);
        plant.setBuildCost(0);
        plant.setAttackPower(2);
        plant.setAttackSpeed(15);
        plant.setName("puffShroom");
        plant.setBulletType(BulletType.SMOKY);
        return plant;
    }

    public static WarriorPlant fumeShroom(){
        WarriorPlant plant = new WarriorPlant();
        plant.setGifUrl("/assesst/FumeShroom.gif");
        plant.setHealth(75);
        plant.setBuildCost(75);
        plant.setAttackPower(4);
        plant.setAttackSpeed(15);
        plant.setName("fumeShroom");
        plant.setBulletType(BulletType.SMOKY);
        return plant;
    }

    public static WarriorPlant scaredyShroom(){
        WarriorPlant plant = new WarriorPlant();
        plant.setGifUrl("/assesst/ScaredyShroom.gif");
        plant.setHealth(75);
        plant.setBuildCost(125);
        plant.setAttackPower(10);
        plant.setAttackSpeed(15);
        plant.setName("scaredyShroom");
        plant.setBulletType(BulletType.SMOKY);
        return plant;
    }

    public static ProducerPlant sunflower(){
        ProducerPlant plant = new ProducerPlant();
        plant.setGifUrl("/assesst/Sunflower.gif");
        plant.setHealth(100);
        plant.setBuildCost(50);
        plant.setProduceAmount(25);
        plant.setName("sunflower");
        plant.setProduceTime(5);
        return plant;
    }
    public static ProducerPlant sunShroom(){
        ProducerPlant plant = new ProducerPlant();
        plant.setGifUrl("/assesst/SunShroom.gif");
        plant.setHealth(75);
        plant.setBuildCost(25);
        plant.setProduceAmount(15);
        plant.setName("sunShroom");
        plant.setProduceTime(5);
        return plant;
    }

    public static ExplosivePlant cherryBomb(){
        ExplosivePlant plant = new ExplosivePlant();
        plant.setHealth(100);
        plant.setName("cherrybomb");
        plant.setGifUrl("/assesst/CherryBomb.gif");
        plant.setBuildCost(150);
        plant.setDestructionPower(3);
        plant.setDestructionArea(3);
        return plant;
    }

    public static ExplosivePlant iceShroom(){
        ExplosivePlant plant = new ExplosivePlant();
        plant.setHealth(100);
        plant.setName("iceshroom");
        plant.setGifUrl("/assesst/IceShroom.gif");
        plant.setBuildCost(75);
        plant.setDestructionPower(1);
        plant.setDestructionArea(1);
        return plant;
    }
    public static ExplosivePlant doomShroom(){
        ExplosivePlant plant = new ExplosivePlant();
        plant.setHealth(100);
        plant.setName("doomshroom");
        plant.setGifUrl("/assesst/DoomShroom.gif");
        plant.setBuildCost(125);
        plant.setDestructionPower(10);
        plant.setDestructionArea(4);
        return plant;
    }

    public static Plant wallNut(){
        Plant plant = new Plant();
        plant.setGifUrl("/assesst/WallNut.gif");
        plant.setHealth(500);
        plant.setBuildCost(50);
        plant.setName("wallNut");
        return plant;
    }


}
