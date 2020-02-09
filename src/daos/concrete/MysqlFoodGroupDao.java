package daos.concrete;

import daos.interfaces.FoodGroupDao;
import models.FoodGroup;

import java.util.ArrayList;

public class MysqlFoodGroupDao implements FoodGroupDao {
    private ArrayList<FoodGroup> foodGroups;


    public MysqlFoodGroupDao() {
        FoodGroup fg1 = new FoodGroup();
        fg1.setFoodId(1);
        fg1.setGroupId(1);
        foodGroups.add(fg1);
        FoodGroup fg2 = new FoodGroup();
        fg1.setFoodId(2);
        fg1.setGroupId(3);
        foodGroups.add(fg2);
        FoodGroup fg3 = new FoodGroup();
        fg1.setFoodId(2);
        fg1.setGroupId(4);
        foodGroups.add(fg3);
    }

    @Override
    public FoodGroup insert(FoodGroup foodGroup) {
        foodGroups.add(foodGroup);
        return foodGroup;
    }

    @Override
    public ArrayList<FoodGroup> getFoodsInGroup(long groupId) {
//        return foodGroups.stream()
//                .filter(foodGroup -> foodGroup.getGroupId() == groupId).;
        return null;
    }

    @Override
    public ArrayList<FoodGroup> getGroupsOfOneFood(long foodId) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
