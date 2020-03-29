package daos.interfaces;

import javafx.collections.ObservableList;
import models.Food;
import models.FoodGroup;
import models.Group;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodGroupDaoInterface {
    FoodGroup insert(FoodGroup foodGroup);
    ArrayList<Food> getFoodsInGroup(long groupId) throws SQLException;
    ArrayList<Group> getGroupsOfOneFood(long foodId) throws SQLException;
    void deleteAll();
}
