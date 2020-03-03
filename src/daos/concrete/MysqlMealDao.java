package daos.concrete;

import daoFactories.Context;
import daos.interfaces.MealDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Meal;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MysqlMealDao implements MealDao {
    private Context _context;

    public MysqlMealDao(Context context){
        _context = context;
        Meal m1 = new Meal(0, 1,1,2, 4, LocalDateTime.now());
        this.insert(m1);
        Meal m2 = new Meal(1, 1,0,2, 4, LocalDateTime.now());
        this.insert(m2);
        Meal m3 = new Meal(2, 2, 1, 1, 4, LocalDateTime.now());
        this.insert(m3);
    }

    @Override
    public Meal insert(Meal meal) {
        _context.meals.add(meal);
        return null;
    }

    @Override
    public ObservableList<Meal> all() {
        return _context.meals;
    }

    @Override
    public int deleteAll() {
        //meals.removeAll();
        return 0;
    }

    @Override
    public int delete(Meal meal) {
        _context.meals.remove(meal);
        return 0;
    }

    @Override
    public Meal findById(long id) {
        return _context.meals.stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);
    }

    @Override
    public ObservableList<Meal> findMealsByDate(LocalDateTime dateTime) {
        ObservableList<Meal> meals = FXCollections.observableList(new ArrayList<Meal>());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        _context.meals.stream()
                .forEach(meal -> {
                    if (formatter.format(meal.getDateTime()).equals(formatter.format(dateTime)))
                        meals.add(meal);
                });
        return meals;
    }

//    @Override
//    public Food getFood(Meal meal) {
//        return _context.foods.stream()
//                .filter(food -> meal.getFoodId() == food.getId())
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public Location getLocation(Meal meal) {
//        return _context.locations.stream()
//                .filter(location -> meal.getLocationId() == location.getId())
//                .findFirst()
//                .orElse(null);
//    }

    @Override
    public ObservableList<Meal> findInRange(LocalDateTime start, LocalDateTime end) {
        ObservableList<Meal> mealsInRange = FXCollections.observableList(new ArrayList<Meal>());
        // needs a second look
        _context.meals.stream()
                .forEach(meal -> {
                    if (meal.getDateTime().isAfter(start) && meal.getDateTime().isBefore(end))
                        mealsInRange.add(meal);
                });
        return mealsInRange;
    }
}
