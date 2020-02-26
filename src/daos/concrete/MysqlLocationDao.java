package daos.concrete;

import daoFactories.Context;
import daos.interfaces.LocationDoa;
import javafx.collections.ObservableList;
import models.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MysqlLocationDao implements LocationDoa {
    private Context _context;

    public MysqlLocationDao(Context context) {
        _context = context;
        Location l0 = new Location("Home", "Mi Casa");
        Location l1 = new Location("Tim Hortons", "Snowdon");
        this.insert(l0);
        this.insert(l1);
    }

    @Override
    public Location insert(Location location) {
        _context.locations.add(location);
        return null;
    }

    @Override
    public ObservableList<Location> all() {
        return _context.locations;
    }

    @Override
    public int deleteAll() {
//        locations.removeAll();
        return 0;
    }

    @Override
    public int delete(Location location) {
        _context.locations.remove(location);
        return 0;
    }

    @Override
    public Location findById(long id) {
        return _context.locations.stream().filter(location -> location.getId()==id).findFirst().orElse(null);
    }

    @Override
    public Location findByName(String name) {
        return _context.locations.stream()
                .filter(location -> location.getName().contains(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Location findByAddress(String address) {
        return _context.locations.stream()
                .filter(location -> location.getAddress().contains(address))
                .findFirst()
                .orElse(null);
    }
}
