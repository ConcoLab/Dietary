package daos.concrete;

import daoFactories.Context;
import models.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Food DOA is used to connect to the database and ADD, REMOVE and READ data from database
 * Be aware that these functions should be called only by the controllers
 */
public class FoodDao implements daos.interfaces.FoodDaoInterface {
    private Context _context;


    /**
     * This constructor is used to grab the whole data once the program starts and put them in
     * an ObservableList
     * @param context
     * @throws SQLException
     */
    public FoodDao(Context context) throws SQLException {
        _context = context;
        String sql = "SELECT * FROM foods";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            _context.foods.add(new Food(rs.getLong("id")
                    , rs.getString("name")
                    , rs.getLong("calories")
                    , rs.getLong("unitId")
                    , rs.getLong("quantity")));
        }
    }

    /**
     * This method is used to add new food row in the foods list in memory and database
     * @param food
     * @return
     */
    @Override
    public Food insert(Food food) {
        String sql = "INSERT INTO foods (name, calories, quantity, unitId)\n" +
                "VALUES ('"+ food.getName() +"', " +
                "'"+ food.getCalories() +"', " +
                "'"+ food.getQuantity() + "', " +
                "'"+ food.getUnitId() +"');";
        ResultSet rs = _context.dbCall(sql);
        _context.foods.add(food);
        return food;
    }

    /**
     * This method receives the whole foods from the database without any filter.
     * @return ArrayList<Food>
     * @throws SQLException
     */
    @Override
    public ArrayList<Food> all() throws SQLException {
        ArrayList<Food> foods = new ArrayList<Food>();
        String sql = "SELECT * FROM foods";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            foods.add(new Food(rs.getLong("id")
                    , rs.getString("name")
                    , rs.getLong("calories")
                    , rs.getLong("unitId")
                    , rs.getLong("quantity")));
        }
        return foods;
    }

    @Override
    public int deleteAll() {
        //foods.removeAll();
        return 0;
    }

    /**
     * This method deletes a food record from the database.
     * @param food
     * @return
     */
    @Override
    public int delete(Food food) {
        String sql = "DELETE FROM foods WHERE foods.id = "+food.getId()+";";
        ResultSet rs = _context.dbCall(sql);
        _context.foods.remove(food);
        return 0;
    }

    /**
     * This method finds a food record from the database and returns it to the user.
     * @param id
     * @return
     */
    @Override
    public Food findById(long id) {
        //TODO: this method should be modified to read the data from the database and use sql queries
        return _context.foods.stream()
                .filter(food -> food.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * This method would be able to search the database to find the food by its name
     * and returns it to the user.
     * @param name
     * @return
     */
    @Override
    public Food findByName(String name) {
        //TODO: This method should be implemented using the sql query.
        return _context.foods.stream()
                .filter(food -> food.getName().contains(name))
                .findFirst()
                .orElse(null);
    }

}
