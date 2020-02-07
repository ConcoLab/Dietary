package models;

public class Dish {
    private long id;
    private long amount;

    public Dish(long id, long amount){
        this.id = id;
        this.amount = amount;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

}
