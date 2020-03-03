package daos.interfaces;

import javafx.collections.ObservableList;
import models.Group;
import java.util.ArrayList;

public interface GroupDao {
    Group insert(Group group);
    ObservableList<Group> all();
    int deleteAll();
    int delete(Group group);
    Group findById(long id);
    Group findByName(String name);
}
