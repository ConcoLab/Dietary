package daos.interfaces;

import models.Food;
import models.FoodGroup;
import models.Group;

import java.util.ArrayList;

public interface FoodGroupDao {
    FoodGroup insert(FoodGroup foodGroup);
    ArrayList<Food> getFoodsInGroup(long foodId);
    ArrayList<Group> getGroupsOfOneFood(long groupId);
    void deleteAll();
}
