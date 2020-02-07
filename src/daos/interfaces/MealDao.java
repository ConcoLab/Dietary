package daos.interfaces;

import models.Meal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MealDao {
    Meal insert(Meal meal);
    ArrayList<Meal> all();
    int deleteAll();
    int delete(Meal meal);
    Meal findById(long id);
    Meal findByDate(Date date);
    List<Meal> findInRange(Date start, Date end);
}
