package daos.concrete;

import daoFactories.Context;
import daoFactories.ContextFactory;
import daos.interfaces.FoodGroupDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Food;
import models.FoodGroup;
import models.Group;

import java.util.ArrayList;

public class MysqlFoodGroupDao implements FoodGroupDao {
    private Context _context;


    public MysqlFoodGroupDao(Context context) {
        _context = context;
        FoodGroup fg0 = new FoodGroup(0, 1);
        this.insert(fg0);
        FoodGroup fg1 = new FoodGroup(1, 1);
        this.insert(fg1);
        FoodGroup fg2 = new FoodGroup(2,3);
        this.insert(fg2);
        FoodGroup fg3 = new FoodGroup(2,4);
        this.insert(fg3);
    }

    @Override
    public FoodGroup insert(FoodGroup foodGroup) {
        _context.foodGroups.add(foodGroup);
        return foodGroup;
    }

    @Override
    public ObservableList<Food> getFoodsInGroup(long groupId) {
//        return foodGroups.stream()
//                .filter(foodGroup -> foodGroup.getGroupId() == groupId).;
        ObservableList<Food> foods = FXCollections.observableList(new ArrayList<Food>());
        _context.foodGroups.stream().forEach(foodGroup -> {
            if(foodGroup.getFoodId() == groupId)
                foods.add(_context.foods.stream()
                        .filter(food -> food.getId() == foodGroup.getFoodId())
                        .findFirst()
                        .orElse(null));
        });
        return foods;
    }

    @Override
    public ObservableList<Group> getGroupsOfOneFood(long foodId) {
        ObservableList<Group> groups = FXCollections.observableList(new ArrayList<Group>());
        _context.foodGroups.stream().forEach(foodGroup -> {
            if(foodGroup.getFoodId() == foodId)
                groups.add(_context.groups.stream()
                        .filter(group -> group.getId() == foodGroup.getGroupId())
                        .findFirst()
                        .orElse(null));
        });
        return groups;
    }

    @Override
    public void deleteAll() {

    }
}
