package daos.concrete;

import daoFactories.Context;
import daos.interfaces.FoodGroupDaoInterface;
import models.Food;
import models.FoodGroup;
import models.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class is used as an instance which allows us to READ, ADD , and DELETE the data from
 * FoodGroups table. This mehtod only should be called from the controller only.
 */
public class FoodGroupDao implements FoodGroupDaoInterface {
    private Context _context;


    /**
     * This method loads all the data from the database to the local memory and initializes the whole relations
     * @param context
     * @throws SQLException
     */
    public FoodGroupDao(Context context) throws SQLException {
        _context = context;
        String sql = "SELECT * FROM foodGroups";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            _context.foodGroups.add(new FoodGroup(rs.getInt("foodId"), rs.getInt("groupId")));
        }
    }

    /**
     * This method works when we are adding a new food which belongs to different groups.
     * The method create the relations in the foodGroup table.
     * @param foodGroup
     * @return
     */
    @Override
    public FoodGroup insert(FoodGroup foodGroup) {
        String sql = "INSERT INTO groups (foodId, groupId)\n" +
                "VALUES ('"+ foodGroup.getFoodId() +"', '"+ foodGroup.getGroupId() +"')";
        ResultSet rs = _context.dbCall(sql);
        _context.foodGroups.add(foodGroup);
        return foodGroup;
    }

    /**
     * This method gets the foods in a certain group by having a groupId and querying the foodGroupTable.
     * @param groupId
     * @return ArrayList<Food>
     * @throws SQLException
     */
    @Override
    public ArrayList<Food> getFoodsInGroup(long groupId) throws SQLException {
//        return foodGroups.stream()
//                .filter(foodGroup -> foodGroup.getGroupId() == groupId).;
        ArrayList<Food> foods = new ArrayList<Food>();
        String sql = "SELECT * FROM foods " +
                "WHERE id = '"+ groupId +"' \n";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            foods.add(new Food(rs.getLong("id")
                    , rs.getString("name")
                    , rs.getLong("calories")
                    , rs.getLong("unitId")
                    , rs.getLong("quantity")));
        }

//        _context.foodGroups.stream().forEach(foodGroup -> {
//            if(foodGroup.getFoodId() == groupId)
//                foods.add(_context.foods.stream()
//                        .filter(food -> food.getId() == foodGroup.getFoodId())
//                        .findFirst()
//                        .orElse(null));
//        });
        return foods;
    }

    /**
     * This method is able to get the list of groupIds which are relevant to a certain food
     * @param foodId
     * @return
     * @throws SQLException
     */
    @Override
    public ArrayList<Group> getGroupsOfOneFood(long foodId) throws SQLException {
        ArrayList<Group> groups = new ArrayList<Group>();
        String sql = "SELECT * FROM groups " +
                "WHERE id = '"+ foodId +"' \n";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            groups.add(new Group(rs.getLong("id"), rs.getString("name")));
        }

//        _context.foodGroups.stream().forEach(foodGroup -> {
//            if(foodGroup.getFoodId() == foodId)
//                groups.add(_context.groups.stream()
//                        .filter(group -> group.getId() == foodGroup.getGroupId())
//                        .findFirst()
//                        .orElse(null));
//        });
        return groups;
    }

    @Override
    public void deleteAll() {

    }

}
