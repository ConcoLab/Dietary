package daos.interfaces;

import javafx.collections.ObservableList;
import models.Location;

import java.util.ArrayList;
import java.util.List;

public interface LocationDoa {
    Location insert(Location location);
    ObservableList<Location> all();
    int deleteAll();
    int delete(Location location);
    Location findById(long id);
    Location findByName(String name);
    Location findByAddress(String address);
}
