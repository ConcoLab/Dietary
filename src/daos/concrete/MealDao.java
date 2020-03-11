package daos.concrete;

import daoFactories.Context;
import daos.interfaces.MealDaoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Meal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class should be used in the controller. And moreover we need to call it when we need to CRUD data from the
 * database
 */
public class MealDao implements MealDaoInterface {
    private Context _context;

    public MealDao(Context context) throws SQLException {
        _context = context;
        String sql = "SELECT * FROM meals";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            LocalDateTime date = LocalDateTime.parse(rs.getString("dateTime"));
            System.out.println(date);
            _context.meals.add(new Meal(rs.getLong("id")
                    , rs.getInt("foodId")
                    , rs.getInt("mealTypeId")
                    , rs.getLong("locationId")
                    , rs.getLong("amount")
                    , rs.getLong("calories")
                    , LocalDateTime.parse(rs.getString("dateTime"))
            ));
        }
    }

    /**
     * Inserts a new meal to the meals database table.
     * @param meal
     * @return
     */
    @Override
    public Meal insert(Meal meal) {
        String sql = "INSERT INTO meals (foodId, mealTypeId, locationId, amount, dateTime, calories)\n" +
                "VALUES ('"+ meal.getFoodId() +"', " +
                "'"+ meal.getMealTypeId() +"', " +
                "'"+ meal.getLocationId() + "', " +
                "'"+ meal.getAmount() + "', " +
                "'"+ meal.getDateTime() + "', " +
                "'"+ meal.getCalories()  +"');";
        ResultSet rs = _context.dbCall(sql);
        _context.meals.add(meal);
        return null;
    }

    /**
     * Queries the meals table and returns all records.
     * @return ArrayList<Meal>
     * @throws SQLException
     */
    @Override
    public ArrayList<Meal> all() throws SQLException {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        String sql = "SELECT * FROM meals";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            LocalDateTime date = LocalDateTime.parse(rs.getString("dateTime"));
            System.out.println(date);
            meals.add(new Meal(rs.getLong("id")
                    , rs.getInt("foodId")
                    , rs.getInt("mealTypeId")
                    , rs.getLong("locationId")
                    , rs.getLong("amount")
                    , rs.getLong("calories")
                    , LocalDateTime.parse(rs.getString("dateTime"))
            ));
        }
        return meals;
    }

    @Override
    public int deleteAll() {
        //meals.removeAll();
        return 0;
    }

    /**
     * deletes a meal from the database.
     * @param meal
     * @return
     */
    @Override
    public int delete(Meal meal) {
        _context.meals.remove(meal);
        return 0;
    }

    /**
     * finds a record of a meal with a specific id.
     * @param id
     * @return
     */
    @Override
    public Meal findById(long id) {
        return _context.meals.stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);
    }

    /**
     * Queries the database using a specific date.
     * @param dateTime
     * @return
     */
    @Override
    public ArrayList<Meal> findMealsByDate(LocalDateTime dateTime) {
        ArrayList<Meal> meals = new ArrayList<Meal>();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//        _context.meals.stream()
//                .forEach(meal -> {
//                    if (formatter.format(meal.getDateTime()).equals(formatter.format(dateTime)))
//                        meals.add(meal);
//                });
        return meals;
    }

//    @Override
//    public Food getFood(Meal meal) {
//        return _context.foods.stream()
//                .filter(food -> meal.getFoodId() == food.getId())
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public Location getLocation(Meal meal) {
//        return _context.locations.stream()
//                .filter(location -> meal.getLocationId() == location.getId())
//                .findFirst()
//                .orElse(null);
//    }

    @Override
    public ObservableList<Meal> findInRange(LocalDateTime start, LocalDateTime end) {
        ObservableList<Meal> mealsInRange = FXCollections.observableList(new ArrayList<Meal>());
        // needs a second look
        _context.meals.stream()
                .forEach(meal -> {
                    if (meal.getDateTime().isAfter(start) && meal.getDateTime().isBefore(end))
                        mealsInRange.add(meal);
                });
        return mealsInRange;
    }

}
