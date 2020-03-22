package controllers;

import daoFactories.ContextFactory;
import models.Location;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;

public class LocationController {
    public static boolean create(Location location){
        ContextFactory._LocationDao().insert(location);
        ContextFactory._LocationDao().notifyObservers();
        return true;
    }

    public static ArrayList<Location> getAllLocations() throws SQLException {
        return ContextFactory._LocationDao().all();
    }

    public static boolean delete(long id){
        ContextFactory._LocationDao().delete(id);
        ContextFactory._LocationDao().notifyObservers();
        return true;
    }

    public static Location getLocationById(long id) throws SQLException {
        return ContextFactory._LocationDao().findById(id);
    }
}
