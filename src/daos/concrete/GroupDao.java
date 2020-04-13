package daos.concrete;

import daoFactories.SqliteConnection;
import daoFactories.ContextFactory;
import daos.interfaces.GroupDaoInterface;
import models.Food;
import models.Group;
import observers.GroupObserver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class allows us to access to the sql database and the in memory list we are created as an ObservableList
 * This class should be called only from controllers not from the views.
 */
public class GroupDao extends Observable implements GroupDaoInterface {
    private SqliteConnection _sqliteConnection;


    /**
     * This method initializes the list of the groups and fetches the to the memory list.
     * @param sqliteConnection
     * @throws SQLException
     */
    public GroupDao(SqliteConnection sqliteConnection) throws SQLException {
        _sqliteConnection = sqliteConnection;
        GroupObserver groupObserver = new GroupObserver();
        this.addObserver(groupObserver);
    }

    /**
     * This method adds a new group record to the database and also the in memory ObservableList
     * @param group
     * @return
     */
    @Override
    public Group insert(Group group) {
        String sql = "INSERT INTO groups (name)\n" +
                "VALUES ('"+ group.getName() +"')";
        long newId = _sqliteConnection.insertCall(sql);
        if(newId != 0){
            group.setId(newId);
//            _context.groups.add(group);
            setChanged();
        }
        return null;
    }

    /**
     * This method returns all records which are in the group table to the controller.
     * @return ArrayList<Group>
     * @throws SQLException
     */
    @Override
    public ArrayList<Group> all() throws SQLException {
        ArrayList<Group> groups = new ArrayList<Group>();
        String sql = "SELECT * FROM groups";
        ResultSet rs = _sqliteConnection.getCall(sql);
        while (rs.next()) {
            groups.add(new Group(rs.getLong("id"), rs.getString("name"), getFoodsInGroup(rs.getLong("id"))));
        }
        return groups;
    }

    public ArrayList<Food> getFoodsInGroup(long groupId) throws SQLException {

        ArrayList<Food> foods = new ArrayList<Food>();
        String sql = "SELECT * FROM foods " +
                "WHERE id = '"+ groupId +"' \n";
        ResultSet rs = _sqliteConnection.getCall(sql);
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
                    , ContextFactory._FoodGroupDao().getGroupsOfOneFood(rs.getLong("id"))));
        }

        return foods;
    }

    @Override
    public int deleteAll() {
        ResultSet rs = _sqliteConnection.truncate("groups");
        setChanged();
        return 0;
    }

    /**
     * We can remove a group from the database using this code
     * @param id
     * @return
     */
    @Override
    public int delete(long id) {
        int rs = _sqliteConnection.deleteCall(id, "groups");
        if(rs != 0){
            setChanged();
        }
//        _context.groups.remove(group);
        return 0;
    }

    @Override
    public Group findById(long id) {
        throw new NotImplementedException();
//        return _context.groups.stream()
//                .filter(group -> group.getId() == id)
//                .findFirst()
//                .orElse(null);
    }

    @Override
    public Group findByName(String name) {
        throw new NotImplementedException();
        // "SELECT * FROM groups WHERE groups.name IS LIKE "%name%"
//        return _context.groups.stream()
//                .filter(group -> group.getName().contains(name))
//                .findFirst()
//                .orElse(null);
    }

}
