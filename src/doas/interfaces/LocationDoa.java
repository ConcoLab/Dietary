package doas.interfaces;

import models.Location;

import java.util.List;

public interface LocationDoa {
    Location insert(Location location);
    List<Location> all();
    int deleteAll();
    int delete(Location location);
    Location findById(long id);
    Location findByName(String name);
    Location findByAddress(String address);
}
