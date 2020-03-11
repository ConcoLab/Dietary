package daos.concrete;

import daoFactories.Context;
import daos.interfaces.GroupDaoInterface;
import models.Group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class allows us to access to the sql database and the in memory list we are created as an ObservableList
 * This class should be called only from controllers not from the views.
 */
public class GroupDao implements GroupDaoInterface {
    private Context _context;


    /**
     * This method initializes the list of the groups and fetches the to the memory list.
     * @param context
     * @throws SQLException
     */
    public GroupDao(Context context) throws SQLException {
        _context = context;
        String sql = "SELECT * FROM groups";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            _context.groups.add(new Group(rs.getLong("id"), rs.getString("name")));
        }
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
        ResultSet rs = _context.dbCall(sql);
        _context.groups.add(group);
        return null;
    }

    /**
     * This method returns all records which are in the group table to the controller.
     * @return ArrayList<Group>
     * @throws SQLException
     */
    @Override
    public ArrayList<Group> all() throws SQLException {
        ArrayList<Group> foods = new ArrayList<Group>();
        String sql = "SELECT * FROM groups";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            foods.add(new Group(rs.getLong("id"), rs.getString("name")));
        }
        return foods;
    }

    @Override
    public int deleteAll() {
        // "TRUNCATE groups"
        //groups.removeAll();
        return 0;
    }

    /**
     * We can remove a group from the database using this code
     * @param group
     * @return
     */
    @Override
    public int delete(Group group) {
        String sql = "DELETE FROM groups WHERE groups.id = "+group.getId()+";";
        ResultSet rs = _context.dbCall(sql);
        _context.groups.remove(group);
        return 0;
    }

    @Override
    public Group findById(long id) {
        return _context.groups.stream()
                .filter(group -> group.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Group findByName(String name) {

        // "SELECT * FROM groups WHERE groups.name IS LIKE "%name%"
        return _context.groups.stream()
                .filter(group -> group.getName().contains(name))
                .findFirst()
                .orElse(null);
    }

}
