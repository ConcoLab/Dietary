package daos.interfaces;

import javafx.collections.ObservableList;
import models.Food;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodDaoInterface {
    Food insert(Food food);
    ArrayList<Food> all() throws SQLException;
    int deleteAll();
    int delete(Food food);
    Food findById(long id);
    Food findByName(String name);
}
