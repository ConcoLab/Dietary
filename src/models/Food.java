/*
The class represents abstract a Food servings that can be added to a certain Meal.
 */

package models;


public class Food {
    private static long ITERATOR = 0;

    private long id;
    private String name;
    private long calories;
    private long unitId;
    private long quantity;

    public Food(Long id, String name, long calories, long unitId, long quantity) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.unitId = unitId;
        this.quantity = quantity;
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


}

