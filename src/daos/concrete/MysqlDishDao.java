package daos.concrete;

import daos.interfaces.DishDao;
import models.Dish;

import java.util.ArrayList;

public class MysqlDishDao implements DishDao {
    public static ArrayList<Dish> dishes = new ArrayList<Dish>();

    @Override
    public Dish insert(Dish dish) {
        dishes.add(dish);
        return null;
    }

    @Override
    public ArrayList<Dish> all() {
        return dishes;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int delete(Dish dish) {
        dishes.remove(dish);
        return 0;
    }

    @Override
    public Dish findById(long id) {
        return dishes.stream()
                .filter(dish -> dish.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
