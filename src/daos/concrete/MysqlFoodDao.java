package daos.concrete;

import daoFactories.Context;
import daoFactories.ContextFactory;
import daos.interfaces.FoodDao;
import models.Food;
import java.util.ArrayList;

public class MysqlFoodDao implements FoodDao {
    private Context _context;


    public MysqlFoodDao(Context context){
        _context = context;
        Food f1 = new Food("Apple", 80, 4,1);
        this.insert(f1);
        Food f2 = new Food("Chicken Pasta", 80, 0, 250);
        this.insert(f2);
        Food f3 = new Food("Soup", 80, 1,250);
        this.insert(f3);
    }
    @Override
    public Food insert(Food food) {
        _context.foods.add(food);
        return null;
    }

    @Override
    public ArrayList<Food> all() {
        return _context.foods;
    }

    @Override
    public int deleteAll() {
        //foods.removeAll();
        return 0;
    }

    @Override
    public int delete(Food food) {
        _context.foods.remove(food);
        return 0;
    }

    @Override
    public Food findById(long id) {
        return _context.foods.stream()
                .filter(food -> food.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Food findByName(String name) {
        return _context.foods.stream()
                .filter(food -> food.getName().contains(name))
                .findFirst()
                .orElse(null);
    }
}
