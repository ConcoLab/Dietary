package daos.interfaces;

import javafx.collections.ObservableList;
import models.Meal;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public interface MealDaoInterface {
    Meal insert(Meal meal);
    ArrayList<Meal> all(LocalDateTime startDate, LocalDateTime endDate, boolean showConsumedFoods, boolean showNotConsumedFood) throws SQLException;
    int deleteAll();
    int delete(long id);
    Meal findById(long id) throws SQLException;
    ArrayList<Meal> findMealsByDate(LocalDateTime date);
//    Food getFood(Meal meal);
//    Location getLocation(Meal meal);
    ObservableList<Meal> findInRange(LocalDateTime start, LocalDateTime end);

    boolean makeFoodIsConsumed(Long id, boolean isConsumed);
}
