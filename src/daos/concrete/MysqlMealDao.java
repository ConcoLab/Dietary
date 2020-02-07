package daos.concrete;

import daos.interfaces.MealDao;
import models.Meal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MysqlMealDao implements MealDao {
    public static ArrayList<Meal> meals = new ArrayList<Meal>();

    public MysqlMealDao(){
        // for example only
        Meal m1 = new Meal(1, Calendar.getInstance().getTime());
        this.insert(m1);
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
    public Meal findByDate(Date date) {
        return meals.stream()
                .filter(meal -> meal.getDate() == date)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Meal> findInRange(Date start, Date end) {
        // needs a second look
        return meals.stream()
                .filter(meal -> meal.getDate().after(start) && meal.getDate().before(end))
                .collect(Collectors.toList());
    }
}
