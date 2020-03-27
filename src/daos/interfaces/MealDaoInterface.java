package daos.interfaces;

import javafx.collections.ObservableList;
import models.Food;
import models.Location;
import models.Meal;
import models.Unit;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MealDaoInterface {
    Meal insert(Meal meal);
    ArrayList<Meal> all(LocalDateTime startDate, LocalDateTime endDate, boolean hideConsumedFoods) throws SQLException;
    int deleteAll();
    int delete(long id);
    Meal findById(long id);
    ArrayList<Meal> findMealsByDate(LocalDateTime date);
//    Food getFood(Meal meal);
//    Location getLocation(Meal meal);
    ObservableList<Meal> findInRange(LocalDateTime start, LocalDateTime end);

    boolean makeFoodIsConsumed(Long id);
}
