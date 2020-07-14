package daos.concrete;

import daoFactories.SqliteConnection;
import daos.interfaces.LocationDoaInterface;
import models.Location;
import observers.LocationObserver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This class allows us to CRUD the data in the locations table in the sql
 *
 */
public class LocationDao extends Observable implements LocationDoaInterface {
    private SqliteConnection _sqliteConnection;

    /**
     * This initializes the data from locations table
     * @param sqliteConnection
     * @throws SQLException
     */
    public LocationDao(SqliteConnection sqliteConnection) throws SQLException {
        _sqliteConnection = sqliteConnection;
        LocationObserver locationObserver = new LocationObserver();
        this.addObserver(locationObserver);
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
        long newId = _sqliteConnection.insertCall(sql);
        if(newId != 0){
            location.setId(newId);
//            _context.locations.add(location);
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
        ResultSet rs = _sqliteConnection.getCall(sql);
        while (rs.next()) {
            locations.add(new Location(rs.getLong("id"), rs.getString("name"), rs.getString("address")));
        }
        return locations;

    }

    @Override
    public int deleteAll() {
        ResultSet rs = _sqliteConnection.truncate("locations");
        setChanged();
        return 0;
    }

    /**
     * Deletes a location from the database and the list in the memory.
     * @param id
     * @return
     */
    @Override
    public int delete(long id) {
        int rs = _sqliteConnection.deleteCall(id, "locations");
        if(rs != 0){
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
        ResultSet rs = _sqliteConnection.findByIdCall(id, "locations");
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
        throw new NotImplementedException();
//        return _context.locations.stream()
//                .filter(location -> location.getName().contains(name))
//                .findFirst()
//                .orElse(null);
    }

    @Override
    public Location findByAddress(String address) {
        throw new NotImplementedException();
//        return _context.locations.stream()
//                .filter(location -> location.getAddress().contains(address))
//                .findFirst()
//                .orElse(null);
    }

}
