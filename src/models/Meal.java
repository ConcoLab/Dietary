package models;

import java.time.LocalDateTime;

public class Meal {
    private long id;
    private long foodId;
    private long mealTypeId;
    private long locationId;
    private long amount;
    private long calories;
    private long fat;
    private long carbohydrate;
    private long salt;
    private long protein;
    private LocalDateTime dateTime;

    public Meal(long id, long foodId, long mealTypeId, long locationId, long amount, long calories, long fat, long carbohydrate, long salt, long protein, LocalDateTime dateTime){
        this.id = id;
        this.foodId = foodId;
        this.mealTypeId = mealTypeId;
        this.locationId = locationId;
        this.amount = amount;
        this.dateTime = dateTime;
        this.calories = calories;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
        this.salt = salt;
        this.protein = protein;
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
