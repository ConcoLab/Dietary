/*
The class represents abstract a Food servings that can be added to a certain Meal.
 */

package models;


import java.util.ArrayList;

public class Food {
    private static long ITERATOR = 0;

    private long id;
    private String name;
    private long calories;
    private long fat;
    private long carbohydrate;
    private long salt;
    private long protein;
    private long unitId;
    private long quantity;
    private ArrayList<Group> groups;

    public Food(long id, String name, long calories, long fat, long carbohydrate, long salt, long protein, long unitId, long quantity, ArrayList<Group> groups) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.salt = salt;
        this.protein = protein;
        this.unitId = unitId;
        this.quantity = quantity;
        this.groups = groups;
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

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Group> getGroups(){ return groups; }

    public void setGroups(ArrayList<Group> groups){ this.groups = groups; }


    public long getFat() {
        return fat;
    }

    public void setFat(long fat) {
        this.fat = fat;
    }

    public long getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(long carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public long getSalt() {
        return salt;
    }

    public void setSalt(long salt) {
        this.salt = salt;
    }

    public long getProtein() {
        return protein;
    }

    public void setProtein(long protein) {
        this.protein = protein;
    }
}

