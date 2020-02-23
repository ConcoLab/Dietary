/*
FoodMeal is a relation between Food, Meal and Location and specifies how many servings of a certain Food
was or will be consumed during a certain Meal at a certain Location.
 */

package models;

public class FoodMeal {
    private long id;
    private long mealId;
    private long foodId;
    private long locationId;
    private long amount;

    public FoodMeal(){
        this.id = id;
        this.mealId = mealId;
        this.foodId = foodId;
        this.locationId = locationId;
        this.amount = amount;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getMealId(){
        return mealId;
    }

    public void setMealId(long mealId){
        this.mealId = mealId;
    }

    public long getFoodId(){
        return foodId;
    }

    public void setFoodId(long foodId){
        this.foodId = foodId;
    }

    public long getLocationId(){
        return locationId;
    }

    public void setLocationId(long locationId){
        this.locationId = locationId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

}
