package models;

import java.util.ArrayList;

public class Group {
    private static long ITERATOR = 0;
    private long id;
    private String name;
    private ArrayList<Food> foods;

    public Group(long id, String name, ArrayList<Food> foods) {
        this.id = id;
        this.name = name;
        this.foods = foods;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

}
