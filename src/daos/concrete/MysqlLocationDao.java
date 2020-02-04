package daos.concrete;

import daos.interfaces.LocationDoa;
import models.Location;

import java.util.List;

public class MysqlLocationDao implements LocationDoa {
    @Override
    public Location insert(Location location) {
        return null;
    }

    @Override
    public List<Location> all() {
        return null;
    }

    @Override
    public int deleteAll() {
        return 0;
    }

    @Override
    public int delete(Location location) {
        return 0;
    }

    @Override
    public Location findById(long id) {
        return null;
    }

    @Override
    public Location findByName(String name) {
        return null;
    }

    @Override
    public Location findByAddress(String address) {
        return null;
    }
}
