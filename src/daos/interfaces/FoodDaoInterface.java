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
    int delete(long id);
    Food findById(long id) throws SQLException;
    Food findByName(String name);
}
