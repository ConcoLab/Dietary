package daos.interfaces;

import javafx.collections.ObservableList;
import models.Group;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GroupDaoInterface {
    Group insert(Group group);
    ArrayList<Group> all() throws SQLException;
    int deleteAll();
    int delete(Group group);
    Group findById(long id);
    Group findByName(String name);
}
