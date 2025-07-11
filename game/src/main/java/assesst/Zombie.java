package assesst;

public class Zombie {
    private String name;
    private int health;
    private String gifUrl;

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
}
