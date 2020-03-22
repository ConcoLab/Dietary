package daos.concrete;

import daoFactories.Context;
import daos.interfaces.LocationDoaInterface;
import models.Location;
import observers.LocationObserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class allows us to CRUD the data in the locations table in the sql
 *
 */
public class LocationDao extends Observable implements LocationDoaInterface {
    private Context _context;

    /**
     * This initializes the data from locations table
     * @param context
     * @throws SQLException
     */
    public LocationDao(Context context) throws SQLException {

        _context = context;

        LocationObserver locationObserver = new LocationObserver();
        this.addObserver(locationObserver);


//        Location l0 = new Location("Home", "Mi Casa");
//        Location l1 = new Location("Tim Hortons", "Snowdon");
//        _context.locations.add(l0);
//        _context.locations.add(l1);
        String sql = "SELECT * FROM locations";
        ResultSet rs = _context.getCall(sql);
        while (rs.next()) {
            _context.locations.add(new Location(rs.getLong("id"), rs.getString("name"), rs.getString("address")));
        }

    }

    /**
     * Inserts new record of location to the locations table.
     * @param location
     * @return
     */
    @Override
    public Location insert(Location location) {
        String sql = "INSERT INTO locations (name, address)\n" +
                    "VALUES ('"+ location.getName() +"', '"+ location.getAddress() +"');";
        long newId = _context.insertCall(sql);
        if(newId != 0){
            location.setId(newId);
            _context.locations.add(location);
            setChanged();
        }
        return null;
    }

    /**
     * This method reads the whole locations from the database and fetch them to the ObservableList in the memory
     * @return ArrayList<Location>
     * @throws SQLException
     */
    @Override
    public ArrayList<Location> all() throws SQLException {
        ArrayList<Location> locations = new ArrayList<Location>();
        String sql = "SELECT * FROM locations";
        ResultSet rs = _context.getCall(sql);
        while (rs.next()) {
            locations.add(new Location(rs.getLong("id"), rs.getString("name"), rs.getString("address")));
        }
        return locations;

    }

    @Override
    public int deleteAll() {
//        locations.removeAll();
        return 0;
    }

    /**
     * Deletes a location from the database and the list in the memory.
     * @param id
     * @return
     */
    @Override
    public int delete(long id) {
        int rs = _context.deleteCall(id, "units");
        if(rs == 1){
            setChanged();
        }
        return 0;
    }

    /**
     * Finds the location by its id.
     * @param id
     * @return
     */
    @Override
    public Location findById(long id) throws SQLException {
        ResultSet rs = _context.findByIdCall(id, "locations");
        if(rs.next()){
            return new Location(rs.getLong("id"),rs.getString("name"), rs.getString("address"));
        }
        return null;
        // Database should be implemented
//        return _context.locations.stream().filter(location -> location.getId()==id).findFirst().orElse(null);
    }

    /**
     * Finds the location by it's name.
     * @param name
     * @return
     */
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
