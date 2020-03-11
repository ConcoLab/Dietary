package daos.concrete;

import daoFactories.Context;
import daos.interfaces.LocationDoaInterface;
import models.Location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class allows us to CRUD the data in the locations table in the sql
 *
 */
public class LocationDao implements LocationDoaInterface {
    private Context _context;

    /**
     * This initializes the data from locations table
     * @param context
     * @throws SQLException
     */
    public LocationDao(Context context) throws SQLException {

        _context = context;
//        Location l0 = new Location("Home", "Mi Casa");
//        Location l1 = new Location("Tim Hortons", "Snowdon");
//        _context.locations.add(l0);
//        _context.locations.add(l1);
        String sql = "SELECT * FROM locations";
        ResultSet rs = _context.dbCall(sql);
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
        ResultSet rs = _context.dbCall(sql);
        _context.locations.add(location);
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
        ResultSet rs = _context.dbCall(sql);
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
     * @param location
     * @return
     */
    @Override
    public int delete(Location location) {
        String sql = "DELETE FROM locations WHERE locations.id = "+location.getId()+";";
        ResultSet rs = _context.dbCall(sql);
        _context.locations.remove(location);
        return 0;
    }

    /**
     * Finds the location by its id.
     * @param id
     * @return
     */
    @Override
    public Location findById(long id) {
        // Database should be implemented
        return _context.locations.stream().filter(location -> location.getId()==id).findFirst().orElse(null);
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
