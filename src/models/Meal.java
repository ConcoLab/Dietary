package models;

import java.time.LocalDateTime;

public class Meal {
    private static long ITERATOR = 0;
    private long id;
    private long mealTypeId;
    private LocalDateTime dateTime;

    public Meal( long mealTypeId, LocalDateTime dateTime){
        this.id = ITERATOR;
        this.mealTypeId = mealTypeId;
        this.dateTime = dateTime;
        ITERATOR++;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getMealTypeId(){
        return mealTypeId;
    }

    public void setMealTypeId(long mealTypeId){
        this.mealTypeId = mealTypeId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
