package Model.plants;

public class ProducerPlant extends Plant{
    private int produceTime;
    private int produceAmount;

    public int getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(int produceTime) {
        this.produceTime = produceTime;
    }

    public int getProduceAmount() {
        return produceAmount;
    }

    public void setProduceAmount(int produceAmount) {
        this.produceAmount = produceAmount;
    }
}
