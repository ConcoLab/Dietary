package daos.concrete;

import daos.interfaces.GroupDao;
import models.Group;
import java.util.ArrayList;

public class MysqlGroupDao implements GroupDao {
    public static ArrayList<Group> groups = new ArrayList<Group>();

    public MysqlGroupDao(){
        String [] g = {"Fruit", "Vegetables", "Grains", "Protein", "Dairy", "Sweets"};
        int gg_size = g.length;
        for (int i = 0; i < gg_size; i++)
            this.insert(new Group(i, g[i]));
    }

    @Override
    public Group insert(Group group) {
        groups.add(group);
        return null;
    }

    @Override
    public ArrayList<Group> all() {
        return groups;
    }

    @Override
    public int deleteAll() {
        //groups.removeAll();
        return 0;
    }

    @Override
    public int delete(Group group) {
        groups.remove(group);
        return 0;
    }

    @Override
    public Group findById(long id) {
        return groups.stream()
                .filter(group -> group.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Group findByName(String name) {
        return groups.stream()
                .filter(group -> group.getName().contains(name))
                .findFirst()
                .orElse(null);
    }
}
