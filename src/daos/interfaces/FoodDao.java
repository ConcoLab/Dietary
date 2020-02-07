package daos.interfaces;

import models.Food;
import java.util.ArrayList;

public interface FoodDao {
    Food insert(Food food);
    ArrayList<Food> all();
    int deleteAll();
    int delete(Food food);
    Food findById(long id);
    Food findByName(String name);
}
