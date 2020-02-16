package daos.concrete;

import daoFactories.Context;
import daos.interfaces.MealTypeDao;
import models.MealType;
import java.util.ArrayList;

public class MysqlMealTypeDao implements MealTypeDao {
    private Context _context;

    public MysqlMealTypeDao(Context context){
        _context = context;
        String [] mt = {"Breakfast", "Brunch", "Lunch", "Dinner", "Snack"};
        int mt_size = mt.length;
        for (int i = 0; i < mt_size; i++)
            this.insert(new MealType(i, mt[i]));
    }
    @Override
    public MealType insert(MealType mealType) {
        _context.mealTypes.add(mealType);
        return null;
    }

    @Override
    public ArrayList<MealType> all() {
        return _context.mealTypes;
    }

    @Override
    public int deleteAll() {
        //mealTYpes.removeAll();
        return 0;
    }

    @Override
    public int delete(MealType mealType) {
        _context.mealTypes.remove(mealType);
        return 0;
    }

    @Override
    public MealType findById(long id) {
        return _context.mealTypes.stream()
                .filter(mealType -> mealType.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public MealType findByName(String name) {
        return _context.mealTypes.stream()
                .filter(mealType -> mealType.getName().contains(name))
                .findFirst()
                .orElse(null);
    }
}
