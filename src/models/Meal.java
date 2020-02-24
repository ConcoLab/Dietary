package models;

import java.time.LocalDateTime;

public class Meal {
    private static long ITERATOR = 0;
    private long id;
    private long mealTypeId;
    private LocalDateTime dateTime;

    // taken from FoodMeal
    private long mealId;
    private long foodId;
    private long locationId;
    private long amount;

    public Meal(long mealTypeId, LocalDateTime dateTime) {
        this.id = ITERATOR;
        this.mealTypeId = mealTypeId;
        this.dateTime = dateTime;

        ITERATOR++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
