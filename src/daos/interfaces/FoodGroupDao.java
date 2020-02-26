package daos.interfaces;

import javafx.collections.ObservableList;
import models.Food;
import models.FoodGroup;
import models.Group;

import java.util.ArrayList;

public interface FoodGroupDao {
    FoodGroup insert(FoodGroup foodGroup);
    ObservableList<Food> getFoodsInGroup(long groupId);
    ObservableList<Group> getGroupsOfOneFood(long foodId);
    void deleteAll();
}
