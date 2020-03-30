package daos.concrete;

import daoFactories.Context;
import models.Food;
import models.FoodGroup;
import models.Group;
import observers.FoodObserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Food DOA is used to connect to the database and ADD, REMOVE and READ data from database
 * Be aware that these functions should be called only by the controllers
 */
public class FoodDao extends Observable implements daos.interfaces.FoodDaoInterface {
    private Context _context;


    /**
     * This constructor is used to grab the whole data once the program starts and put them in
     * an ObservableList
     * @param context
     * @throws SQLException
     */
    public FoodDao(Context context) throws SQLException {
        _context = context;

        // Adds the observer to this
        FoodObserver foodObserver = new FoodObserver();
        this.addObserver(foodObserver);

        String sql = "SELECT * FROM foods";
        ResultSet rs = _context.getCall(sql);

        while (rs.next()) {
            _context.foods.add(new Food(rs.getLong("id")
                    , rs.getString("name")
                    , rs.getLong("calories")
                    , rs.getLong("fat")
                    , rs.getLong("carbohydrate")
                    , rs.getLong("salt")
                    , rs.getLong("protein")
                    , rs.getLong("unitId")
                    , rs.getLong("quantity")
                    , getGroupsOfOneFood(rs.getLong("id"))));

        }
    }

    public ArrayList<Group> getGroupsOfOneFood(long foodId) throws SQLException {
        ArrayList<Group> groups = new ArrayList<Group>();
        String sql = "SELECT f.groupId as id, g.name as name\n" +
                "FROM foodGroups as f\n" +
                "JOIN groups as g ON g.id = f.groupId\n" +
                "WHERE f.foodId = " + foodId ;
        ResultSet rs = _context.getCall(sql);
        while (rs.next()) {
            groups.add(new Group(rs.getLong("id"), rs.getString("name"), new ArrayList<Food>()));
        }

        return groups;
    }

    /**
     * This method is used to add new food row in the foods list in memory and database
     * @param food
     * @return
     */
    @Override
    public Food insert(Food food) {
        String sql = "INSERT INTO foods (name, calories, fat, carbohydrate, salt, protein, quantity, unitId)\n" +
                "VALUES ('"+ food.getName() +"', " +
                "'"+ food.getCalories() +"', " +
                "'"+ food.getFat() +"', " +
                "'"+ food.getCarbohydrate() +"', " +
                "'"+ food.getSalt() +"', " +
                "'"+ food.getProtein() +"', " +
                "'"+ food.getQuantity() + "', " +
                "'"+ food.getUnitId() +"');";
        long newId = _context.insertCall(sql);
        if(newId != 0){
            food.setId(newId);
            _context.foods.add(food);
            for (Group group : food.getGroups())
            {
                insertFoodGroup(new FoodGroup(newId, group.getId()));
            }
            setChanged();
        }

        return food;
    }

    public FoodGroup insertFoodGroup(FoodGroup foodGroup) {
        String sql = "INSERT INTO foodGroups (foodId, groupId)\n" +
                "VALUES ('"+ foodGroup.getFoodId() +"', '"+ foodGroup.getGroupId() +"')";
        ResultSet rs = _context.getCall(sql);
        _context.foodGroups.add(foodGroup);
        return foodGroup;
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
        ResultSet rs = _context.getCall(sql);
        while (rs.next()) {
            foods.add(new Food(rs.getLong("id")
                    , rs.getString("name")
                    , rs.getLong("calories")
                    , rs.getLong("fat")
                    , rs.getLong("carbohydrate")
                    , rs.getLong("salt")
                    , rs.getLong("protein")
                    , rs.getLong("unitId")
                    , rs.getLong("quantity")
                    , getGroupsOfOneFood(rs.getLong("id"))));
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
     * @param id
     * @return
     */
    @Override
    public int delete(long id) {
//        String sql = "DELETE FROM foods WHERE foods.id = "+food.getId()+";";
        int rs = _context.deleteCall(id, "foods");
        if (rs != 0){
            setChanged();
        }
//        _context.foods.remove(food);
        return 0;
    }

    /**
     * This method finds a food record from the database and returns it to the user.
     * @param id
     * @return
     */
    @Override
    public Food findById(long id) throws SQLException {
        ResultSet rs = _context.findByIdCall(id, "foods");
        if (rs.next()){
            Food food = new Food(rs.getLong("id")
                    , rs.getString("name")
                    , rs.getLong("calories")
                    , rs.getLong("fat")
                    , rs.getLong("carbohydrate")
                    , rs.getLong("salt")
                    , rs.getLong("protein")
                    , rs.getLong("unitId")
                    , rs.getLong("quantity")
                    , getGroupsOfOneFood(rs.getLong("id")));
            return food;
        }
        return null;
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
