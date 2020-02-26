package daos.concrete;

import daoFactories.Context;
import daoFactories.ContextFactory;
import daos.interfaces.GroupDao;
import javafx.collections.ObservableList;
import models.Group;
import java.util.ArrayList;

public class MysqlGroupDao implements GroupDao {
    private Context _context;


    public MysqlGroupDao(Context context){
        _context = context;
        // "SELECT * FROM groups"
        String [] g = {"Fruit", "Vegetables", "Grains", "Protein", "Dairy", "Sweets"};
        for (int i = 0; i < g.length; i++)
            this.insert(new Group(g[i]));
    }

    @Override
    public Group insert(Group group) {
        _context.groups.add(group);
        return null;
    }

    @Override
    public ObservableList<Group> all() {
        return _context.groups;
    }

    @Override
    public int deleteAll() {
        // "TRUNCATE groups"
        //groups.removeAll();
        return 0;
    }

    @Override
    public int delete(Group group) {
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
