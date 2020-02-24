/*
The class represents abstract a Food servings that can be added to a certain Meal.
 */

package models;

public class Food extends FoodGroup {
    private static long ITERATOR = 0;

    private long id;
    private String name;
    private long calories;
    private long unit_id;
    private long quantity;
    private boolean eaten;

    // constructor with no FoodGroup variables (same as before)
    public Food(String name, long calories, long unit_id, long quantity) {

        this.id = ITERATOR;
        this.name = name;
        this.calories = calories;
        this.unit_id = unit_id;
        this.quantity = quantity;

        ITERATOR++;
    }

    // OVERLOADED constructor with FoodGroup variables
    public Food(String name, long calories, long unit_id, long quantity, boolean dairy, boolean meat, boolean fruit, boolean vegetables, boolean sweets) {

        // determining FoodGroup properties for a Food (e.g. dairy = True, vegetables = False, etc.)
        super.setDairy(dairy);
        super.setMeat(meat);
        super.setFruit(fruit);
        super.setVegetables(vegetables);
        super.setSweets(sweets);

        this.id = ITERATOR;
        this.name = name;
        this.calories = calories;
        this.unit_id = unit_id;
        this.quantity = quantity;

        ITERATOR++;
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

    public long getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(long unit_id) {
        this.unit_id = unit_id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }


}

