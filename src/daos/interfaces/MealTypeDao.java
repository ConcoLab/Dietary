package daos.interfaces;

import models.MealType;
import java.util.ArrayList;

public interface MealTypeDao {
    MealType insert(MealType mealType);
    ArrayList<MealType> all();
    int deleteAll();
    int delete(MealType mealType);
    MealType findById(long id);
    MealType findByName(String name);
}
