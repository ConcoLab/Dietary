package models;

public class Unit {
    private static long ITERATOR = 0;
    private long id;
    private String name;

    public Unit(String name){
        this.id = ITERATOR;
        this.name = name;
        ITERATOR++;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
