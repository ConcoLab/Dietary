package daos.interfaces;

import models.Dish;

import java.util.ArrayList;
import java.util.Date;

public interface DishDao {
    Dish insert(Dish dish);
    ArrayList<Dish> all();
    int deleteAll();
    int delete(Dish dish);
    Dish findById(long id);
}
