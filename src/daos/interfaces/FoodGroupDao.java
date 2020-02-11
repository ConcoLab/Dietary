package daos.interfaces;

import models.Food;
import models.FoodGroup;
import models.Group;

import java.util.ArrayList;

public interface FoodGroupDao {
    FoodGroup insert(FoodGroup foodGroup);
    ArrayList<Food> getFoodsInGroup(long groupId);
    ArrayList<Group> getGroupsOfOneFood(long foodId);
    void deleteAll();
}
