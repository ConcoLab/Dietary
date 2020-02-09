package daos.interfaces;

import models.Food;
import models.FoodGroup;
import models.Group;

import java.util.ArrayList;

public interface FoodGroupDao {
    FoodGroup insert(FoodGroup foodGroup);
    ArrayList<FoodGroup> getFoodsInGroup(long groupId);
    ArrayList<FoodGroup> getGroupsOfOneFood(long foodId);
    void deleteAll();
}
