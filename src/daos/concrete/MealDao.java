package daos.concrete;

import daoFactories.Context;
import daos.interfaces.MealDaoInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Meal;
import observers.MealObserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class should be used in the controller. And moreover we need to call it when we need to CRUD data from the
 * database
 */
public class MealDao extends Observable implements MealDaoInterface {
    private Context _context;

    public MealDao(Context context) throws SQLException {
        _context = context;

        MealObserver mealObserver = new MealObserver();
        this.addObserver(mealObserver);


        String sql = "SELECT * FROM meals";
        ResultSet rs = _context.getCall(sql);
        while (rs.next()) {
            LocalDateTime date = LocalDateTime.parse(rs.getString("dateTime"));
            System.out.println(date);
            _context.meals.add(new Meal(rs.getLong("id")
                    , rs.getInt("foodId")
                    , rs.getInt("mealTypeId")
                    , rs.getLong("locationId")
                    , rs.getLong("amount")
                    , rs.getLong("calories")
                    , rs.getLong("fat")
                    , rs.getLong("carbohydrate")
                    , rs.getLong("salt")
                    , rs.getLong("protein")
                    , rs.getInt("isConsumed")
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
        String sql = "INSERT INTO meals (foodId, mealTypeId, locationId, amount, dateTime, calories, fat, carbohydrate, salt, protein, isConsumed)\n" +
                "VALUES ('"+ meal.getFoodId() +"', " +
                "'"+ meal.getMealTypeId() +"', " +
                "'"+ meal.getLocationId() + "', " +
                "'"+ meal.getAmount() + "', " +
                "'"+ meal.getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "', " +
                "'"+ meal.getCalories() +"', " +
                "'"+ meal.getFat() +"', " +
                "'"+ meal.getCarbohydrate() +"', " +
                "'"+ meal.getSalt() +"', " +
                "'"+ meal.getProtein() +"', " +
                "'"+ meal.getIsConsumed() + "');";
        System.out.println("-- DEBUG: " + sql);
        long rs = _context.insertCall(sql);
        if(rs != 0){
            meal.setId(rs);
//            _context.meals.add(meal);
            setChanged();
            return meal;
        }
        return null;
    }

    /**
     * Queries the meals table and returns all records.
     * @return ArrayList<Meal>
     * @throws SQLException
     */
    @Override
    public ArrayList<Meal> all(LocalDateTime startDate, LocalDateTime endDate, boolean showConsumedFoods, boolean showNotConsumedFoods) throws SQLException {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        String sql = "SELECT * FROM meals WHERE dateTime >= '"
                + startDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                "' AND dateTime <= '" + endDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "'";

        if(showConsumedFoods && !showNotConsumedFoods){
            sql = sql + " AND isConsumed = " + true + "";
        }
        else if(!showConsumedFoods && showNotConsumedFoods){
            sql = sql + " AND isConsumed = " + false + "";
        }
        else if(!showConsumedFoods && !showNotConsumedFoods)
            sql = "SELECT * FROM meals WHERE id = ?;";

        System.out.println(sql);
        ResultSet rs = _context.getCall(sql);
        while (rs.next()) {
            LocalDateTime date = LocalDateTime.parse(rs.getString("dateTime"));
            System.out.println(date);
            meals.add(new Meal(rs.getLong("id")
                    , rs.getInt("foodId")
                    , rs.getInt("mealTypeId")
                    , rs.getLong("locationId")
                    , rs.getLong("amount")
                    , rs.getLong("calories")
                    , rs.getLong("fat")
                    , rs.getLong("carbohydrate")
                    , rs.getLong("salt")
                    , rs.getLong("protein")
                    , rs.getInt("isConsumed")
                    , LocalDateTime.parse(rs.getString("dateTime"))
            ));
        }
        setChanged();
        return meals;
    }

    public ArrayList<Meal> allInDinings(LocalDateTime startDate, LocalDateTime endDate, boolean showConsumedFoods, boolean showNotConsumedFoods) throws SQLException {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        String sql = "SELECT * FROM meals WHERE dateTime >= '"
                + startDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                "' AND dateTime <= '" + endDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "' "+
                " AND locationId = 0";

        if(showConsumedFoods && !showNotConsumedFoods){
            sql = sql + " AND isConsumed = " + true + "";
        }
        else if(!showConsumedFoods && showNotConsumedFoods){
            sql = sql + " AND isConsumed = " + false + "";
        }
        else if(!showConsumedFoods && !showNotConsumedFoods)
            sql = "SELECT * FROM meals WHERE id = ?;";

        System.out.println(sql);
        ResultSet rs = _context.getCall(sql);
        while (rs.next()) {
            LocalDateTime date = LocalDateTime.parse(rs.getString("dateTime"));
            System.out.println(date);
            meals.add(new Meal(rs.getLong("id")
                    , rs.getInt("foodId")
                    , rs.getInt("mealTypeId")
                    , rs.getLong("locationId")
                    , rs.getLong("amount")
                    , rs.getLong("calories")
                    , rs.getLong("fat")
                    , rs.getLong("carbohydrate")
                    , rs.getLong("salt")
                    , rs.getLong("protein")
                    , rs.getInt("isConsumed")
                    , LocalDateTime.parse(rs.getString("dateTime"))
            ));
        }
        setChanged();
        return meals;
    }

    public ArrayList<Meal> allOutDinings(LocalDateTime startDate, LocalDateTime endDate, boolean showConsumedFoods, boolean showNotConsumedFoods) throws SQLException {
        ArrayList<Meal> meals = new ArrayList<Meal>();
        String sql = "SELECT * FROM meals WHERE dateTime >= '"
                + startDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) +
                "' AND dateTime <= '" + endDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "' " +
                " AND locationId != 0";

        if(showConsumedFoods && !showNotConsumedFoods){
            sql = sql + " AND isConsumed = " + true + "";
        }
        else if(!showConsumedFoods && showNotConsumedFoods){
            sql = sql + " AND isConsumed = " + false + "";
        }
        else if(!showConsumedFoods && !showNotConsumedFoods)
            sql = "SELECT * FROM meals WHERE id = ?;";

        System.out.println(sql);
        ResultSet rs = _context.getCall(sql);
        while (rs.next()) {
            LocalDateTime date = LocalDateTime.parse(rs.getString("dateTime"));
            System.out.println(date);
            meals.add(new Meal(rs.getLong("id")
                    , rs.getInt("foodId")
                    , rs.getInt("mealTypeId")
                    , rs.getLong("locationId")
                    , rs.getLong("amount")
                    , rs.getLong("calories")
                    , rs.getLong("fat")
                    , rs.getLong("carbohydrate")
                    , rs.getLong("salt")
                    , rs.getLong("protein")
                    , rs.getInt("isConsumed")
                    , LocalDateTime.parse(rs.getString("dateTime"))
            ));
        }
        setChanged();
        return meals;
    }

    @Override
    public int deleteAll() {
        //meals.removeAll();
        return 0;
    }

    /**
     * deletes a meal from the database.
     * @param id
     * @return
     */
    @Override
    public int delete(long id) {
        int rs = _context.deleteCall(id, "meals");
        if (rs != 0){
            setChanged();
        }
        return 0;
    }

    /**
     * finds a record of a meal with a specific id.
     * @param id
     * @return
     */
    @Override
    public Meal findById(long id) throws SQLException {
        String sql = "SELECT * FROM meals WHERE id = " + id;
        System.out.println(sql);
        ResultSet rs = _context.getCall(sql);
        return new Meal(rs.getLong("id")
                , rs.getInt("foodId")
                , rs.getInt("mealTypeId")
                , rs.getLong("locationId")
                , rs.getLong("amount")
                , rs.getLong("calories")
                , rs.getLong("fat")
                , rs.getLong("carbohydrate")
                , rs.getLong("salt")
                , rs.getLong("protein")
                , rs.getInt("isConsumed")
                , LocalDateTime.parse(rs.getString("dateTime"))
            );
        }
//        return _context.meals.stream().filter(meal -> meal.getId() == id).findFirst().orElse(null);

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

    @Override
    public boolean makeFoodIsConsumed(Long id, boolean isConsumed) {
        String sql = "UPDATE meals\n" +
                "SET isConsumed = "+ isConsumed +"\n" +
                "WHERE id = " + id + ";";
        long rs = _context.updateCall(sql);
        setChanged();
        return true;
    }

}
