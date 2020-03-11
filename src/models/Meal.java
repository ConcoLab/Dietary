package models;

import java.time.LocalDateTime;

public class Meal {
    private long id;
    private long foodId;
    private long mealTypeId;
    private long locationId;
    private long amount;
    private long calories;
    private LocalDateTime dateTime;

    public Meal(Long id, long foodId, long mealTypeId, long locationId, long amount, long calories, LocalDateTime dateTime){
        this.id = id;
        this.foodId = foodId;
        this.mealTypeId = mealTypeId;
        this.locationId = locationId;
        this.amount = amount;
        this.dateTime = dateTime;
        this.calories = calories;
    }

    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    public void setFoodId(long foodId){
        this.foodId = foodId;
    }
    public long getFoodId(){
        return foodId;
    }
    public void setMealTypeId(long mealTypeId){
        this.mealTypeId = mealTypeId;
    }
    public long getMealTypeId(){
        return mealTypeId;
    }
    public void setLocationId(long locationId){
        this.locationId = locationId;
    }
    public long getLocationId(){
        return locationId;
    }
    public void setAmount(long amount){
        this.amount = amount;
    }
    public long getAmount(){
        return amount;
    }
    public void setCalories(long amount){
        this.calories = calories;
    }
    public long getCalories(){
        return calories;
    }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
