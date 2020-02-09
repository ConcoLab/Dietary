package daos.concrete;

import daos.interfaces.FoodGroupDao;
import models.Food;
import models.FoodGroup;
import models.Group;

import java.util.ArrayList;

public class MysqlFoodGroupDao implements FoodGroupDao {
    private ArrayList<FoodGroup> foodGroups;


    public MysqlFoodGroupDao() {
        FoodGroup fg1 = new FoodGroup();
        fg1.setFoodId(1);
        fg1.setGroupId(1);
        foodGroups.add(fg1);
    }

    @Override
    public FoodGroup insert(FoodGroup foodGroup) {
        foodGroups.add(foodGroup);
        return foodGroup;
    }

    @Override
    public ArrayList<Food> getFoodsInGroup(long foodId) {
        return null;
    }

    @Override
    public ArrayList<Group> getGroupsOfOneFood(long groupId) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
