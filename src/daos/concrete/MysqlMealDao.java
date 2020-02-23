package daos.concrete;

import daoFactories.Context;
import daos.interfaces.MealDao;
import models.Meal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MysqlMealDao implements MealDao {
    private Context _context;

    public MysqlMealDao(Context context){
        _context = context;
        Meal m1 = new Meal(0, LocalDateTime.now());
        this.insert(m1);
        Meal m2 = new Meal(3, LocalDateTime.now());
        this.insert(m2);
        Meal m3 = new Meal(2, LocalDateTime.now());
        this.insert(m3);
    }

    @Override
    public Meal insert(Meal meal) {
        _context.meals.add(meal);
        return null;
    }

    @Override
    public ArrayList<Meal> all() {
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
    public Meal findByDate(LocalDateTime dateTime) {
        return _context.meals.stream()
                .filter(meal -> meal.getDateTime() == dateTime)
                .findFirst()
                .orElse(null);
    }

    @Override
    public ArrayList<Meal> findInRange(LocalDateTime start, LocalDateTime end) {
        ArrayList<Meal> mealsInRange = new ArrayList<Meal>();
        // needs a second look
        _context.meals.stream()
                .forEach(meal -> {
                    if (meal.getDateTime().isAfter(start) && meal.getDateTime().isBefore(end))
                        mealsInRange.add(meal);
                });
        return mealsInRange;
    }
}
