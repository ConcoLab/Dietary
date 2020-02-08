package daos.concrete;

import daos.interfaces.FoodDao;
import models.Food;
import java.util.ArrayList;

public class MysqlFoodDao implements FoodDao {
    public static ArrayList<Food> foods = new ArrayList<Food>();

    public MysqlFoodDao(){
        Food f1 = new Food(1, "Apple", 80, 1);
        this.insert(f1);
    }
    @Override
    public Food insert(Food food) {
        foods.add(food);
        return null;
    }

    @Override
    public ArrayList<Food> all() {
        return foods;
    }

    @Override
    public int deleteAll() {
        //foods.removeAll();
        return 0;
    }

    @Override
    public int delete(Food food) {
        foods.remove(food);
        return 0;
    }

    @Override
    public Food findById(long id) {
        return foods.stream()
                .filter(food -> food.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Food findByName(String name) {
        return foods.stream()
                .filter(food -> food.getName().contains(name))
                .findFirst()
                .orElse(null);
    }
}
