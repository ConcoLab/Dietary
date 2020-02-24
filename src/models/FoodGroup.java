/*
Made changes to FoodGroup.

The regular constructor is the same as before (just a default), and so none of the code is
affected unless the constructor with parameters is used in the code.

The parameterized constructor will accept some extra booleans for the actual food types to be
'extended' by whatever Food is being created. So, if you create a new Food called cheeseburger,
then you can have it as, say:

dairy = True
meat = True
carbohydrates = True
fruits = False
vegetables = False
sweets = False

Or, alternatively, this code could be changed to accept longs instead of booleans and instead represent
'fractions' of a daily serving. All of this can be input in the Food class, it just keeps it nicely separated.

Although this may not work for other reasons.

I will change this soon. NO NEED TO MERGE! It is just so I can keep track and experiment with Git.

 */

package models;

public class FoodGroup {

    // ID numbers for food and group
    private long foodId;
    private long groupId;

    // different food 'groups'
    private boolean dairy;
    private boolean meat;
    private boolean carbohydrates;
    private boolean vegetables;
    private boolean fruit;
    private boolean sweets;

    // default constructor
    public FoodGroup() {
    }

    // constructor update from Parham
    public FoodGroup(long foodId, long groupId){
        this.setFoodId(foodId);
        this.setGroupId(groupId);
    }

    // constructor to set group kinds
    public FoodGroup(boolean dairy, boolean meat, boolean carbohydrates, boolean vegetables, boolean fruit, boolean sweets) {
        this.dairy = dairy;
        this.meat = meat;
        this.carbohydrates = carbohydrates;
        this.vegetables = vegetables;
        this.fruit = fruit;
        this.sweets = sweets;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public boolean getDairy() {
        return dairy;
    }

    public void setDairy(boolean dairy) {
        this.dairy = dairy;
    }

    public boolean getMeat() {
        return meat;
    }

    public void setMeat(boolean meat) {
        this.meat = meat;
    }

    public boolean getCarbs() {
        return carbohydrates;
    }

    public void setCarbs(boolean carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public boolean getVegetables() {
        return vegetables;
    }

    public void setVegetables(boolean vegetables) {
        this.vegetables = vegetables;
    }

    public boolean getFruit() {
        return fruit;
    }

    public void setFruit(boolean fruit) {
        this.fruit = fruit;
    }

    public boolean getSweets() {
        return sweets;
    }

    public void setSweets(boolean sweets) {
        this.sweets = sweets;
    }
}
