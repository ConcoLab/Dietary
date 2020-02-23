package daos.interfaces;

import models.Meal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MealDao {
    Meal insert(Meal meal);
    ArrayList<Meal> all();
    int deleteAll();
    int delete(Meal meal);
    Meal findById(long id);
    Meal findByDate(LocalDateTime date);
    ArrayList<Meal> findInRange(LocalDateTime start, LocalDateTime end);
}
