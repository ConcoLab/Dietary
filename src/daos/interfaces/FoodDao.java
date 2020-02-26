package daos.interfaces;

import javafx.collections.ObservableList;
import models.Food;
import java.util.ArrayList;

public interface FoodDao {
    Food insert(Food food);
    ObservableList<Food> all();
    int deleteAll();
    int delete(Food food);
    Food findById(long id);
    Food findByName(String name);
}
