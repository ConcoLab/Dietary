package daos.concrete;

import daos.interfaces.MealDao;
import models.Meal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MysqlMealDao implements MealDao {
    public static ArrayList<Meal> meals = new ArrayList<Meal>();

    public MysqlMealDao(){
        // for example only
        Meal m1 = new Meal(0, 0, LocalDateTime.now());
        this.insert(m1);
        Meal m2 = new Meal(1, 3, LocalDateTime.now());
        this.insert(m2);
        Meal m3 = new Meal(2, 2, LocalDateTime.now());
        this.insert(m3);
    }

    @Override
    public Meal insert(Meal meal) {
        meals.add(meal);
        return null;
    }

    @Override
    public ArrayList<Meal> all() {
        return meals;
    }

    @Override
    public int deleteAll() {
        //meals.removeAll();
        return 0;
    }

    @Override
    public int delete(Meal meal) {
        meals.remove(meal);
        return 0;
    }

    @Override
    public Meal findById(long id) {
        return meals.stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Meal findByDate(LocalDateTime dateTime) {
        return meals.stream()
                .filter(meal -> meal.getDateTime() == dateTime)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Meal> findInRange(LocalDateTime start, LocalDateTime end) {
        ArrayList<Meal> mealsInRange = new ArrayList<Meal>();
        // needs a second look
        meals.stream()
                .forEach(meal -> {
                    if (meal.getDateTime().isAfter(start) && meal.getDateTime().isBefore(end))
                        mealsInRange.add(meal);
                });
        return mealsInRange;
    }
}
