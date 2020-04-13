package daos.concrete;

import daoFactories.SqliteConnection;
import daos.interfaces.UnitDaoInterface;
import models.Unit;
import observers.UnitObserver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * This Data Access Object is used to get access to the data which we have on database and also on our observables
 */
public class UnitDao extends Observable implements UnitDaoInterface {
    public SqliteConnection _sqliteConnection;


    /**
     * This is the constructor which initialized the list of units from the database
     * in this case the data come from the database and fetch to an array list.
     *
     * @param sqliteConnection
     * @throws SQLException
     */
    public UnitDao(SqliteConnection sqliteConnection) throws SQLException {
        _sqliteConnection = sqliteConnection;
        UnitObserver unitObserver = new UnitObserver();
        this.addObserver(unitObserver);
    }

    /**
     * This method is used to add new unit to the database and also the list we have on memory as observable
     * @param unit
     * @return
     */
    @Override
    public Unit insert(Unit unit) throws SQLException {
        String sql = "INSERT INTO units (name)\n" +
                "VALUES ('"+ unit.getName() +"');";
        long rs = _sqliteConnection.insertCall(sql);
        if(rs != 0){
            unit.setId(rs);
//            _context.units.add(unit);
            setChanged();
        }

        return null;
    }

    /**
     * This method reads all the units from the database and pass them to controller as a list of units
     * @return ArrayList<unit>
     * @throws SQLException
     */
    @Override
    public ArrayList<Unit> all() throws SQLException {
        ArrayList<Unit> units = new ArrayList<Unit>();
        String sql = "SELECT * FROM units";
        ResultSet rs = _sqliteConnection.getCall(sql);
        while (rs.next()) {
            units.add(new Unit(rs.getLong("id"), rs.getString("name")));
        }
        return units;
    }

    @Override
    public int deleteAll() {
        ResultSet rs = _sqliteConnection.truncate("units");
        setChanged();
        return 0;
    }

    /**
     * This method gets a unit and delete it from the database and in memory observable
     * @param id
     * @return
     */
    @Override
    public int delete(long id) {
        int rs = _sqliteConnection.deleteCall(id, "units");
        if(rs != 0){
//            boolean result = _context.units.remove(unit.getId());
            setChanged();
        }
        return 0;
    }

    /**
     * This method is used to find a unit by its id.
     * This would be helpful for edit method
     * @param id
     * @return
     */
    @Override
    public Unit findById(long id) throws SQLException {
        String sql = "SELECT * FROM units WHERE units.id = " + id + " LIMIT 1;";
        ResultSet rs = _sqliteConnection.getCall(sql);
        Unit unit = new Unit(0, "");
        if(rs.next()){
            unit.setId(rs.getLong("id"));
            unit.setName(rs.getString("name"));
        }
        // this method should also be implemented using sql

        return unit;
    }

    /**
     * This method gets the name of a unit and returns its whole data.
     * @param name
     * @return
     */
    @Override
    public Unit findByName(String name) {
        throw new NotImplementedException();
//        return _context.units.stream()
//                .filter(unit -> unit.getName().contains(name))
//                .findFirst()
//                .orElse(null);
    }

}
