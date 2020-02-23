package daos.concrete;

import daos.interfaces.LocationDoa;
import models.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MysqlLocationDao implements LocationDoa {
    public static ArrayList<Location> locations = new ArrayList<Location>();

    public MysqlLocationDao() {
        Location l0 = new Location(0,"Home", "Mi Casa");
        Location l1 = new Location(1, "Tim Hortons", "Snowdon");
        this.insert(l0);
        this.insert(l1);
    }

    @Override
    public Location insert(Location location) {
        locations.add(location);
        return null;
    }

    @Override
    public ArrayList<Location> all() {
        return locations;
    }

    @Override
    public int deleteAll() {
//        locations.removeAll();
        return 0;
    }

    @Override
    public int delete(Location location) {
        locations.remove(location);
        return 0;
    }

    @Override
    public Location findById(long id) {
        return locations.stream().filter(location -> location.getId()==id).findFirst().orElse(null);
    }

    @Override
    public Location findByName(String name) {
        return locations.stream()
                .filter(location -> location.getName().contains(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Location findByAddress(String address) {
        return locations.stream()
                .filter(location -> location.getAddress().contains(address))
                .findFirst()
                .orElse(null);
    }
}
