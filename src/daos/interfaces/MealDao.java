package daos.interfaces;

import javafx.collections.ObservableList;
import models.Food;
import models.Location;
import models.Meal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MealDao {
    Meal insert(Meal meal);
    ObservableList<Meal> all();
    int deleteAll();
    int delete(Meal meal);
    Meal findById(long id);
    ObservableList<Meal> findMealsByDate(LocalDateTime date);
//    Food getFood(Meal meal);
//    Location getLocation(Meal meal);
    ObservableList<Meal> findInRange(LocalDateTime start, LocalDateTime end);

}
