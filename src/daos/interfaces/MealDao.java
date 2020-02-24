package daos.interfaces;

import models.Food;
import models.Location;
import models.Meal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MealDao {
    Meal insert(Meal meal);
    ArrayList<Meal> all();
    int deleteAll();
    int delete(Meal meal);
    Meal findById(long id);
    ArrayList<Meal> findMealsByDate(LocalDateTime date);
//    Food getFood(Meal meal);
//    Location getLocation(Meal meal);
    ArrayList<Meal> findInRange(LocalDateTime start, LocalDateTime end);

}
