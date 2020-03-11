package daos.concrete;

import daoFactories.Context;
import daos.interfaces.UnitDaoInterface;
import models.Unit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This Data Access Object is used to get access to the data which we have on database and also on our observables
 */
public class UnitDao implements UnitDaoInterface {
    private Context _context;

    /**
     * This is the constructor which initialized the list of units from the database
     * in this case the data come from the database and fetch to an array list.
     *
     * @param context
     * @throws SQLException
     */
    public UnitDao(Context context) throws SQLException {
        _context = context;
        String sql = "SELECT * FROM units";
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            _context.units.add(new Unit(rs.getLong("id"), rs.getString("name")));
        }
    }

    /**
     * This method is used to add new unit to the database and also the list we have on memory as observable
     * @param unit
     * @return
     */
    @Override
    public Unit insert(Unit unit) {
        String sql = "INSERT INTO units (name)\n" +
                "VALUES ('"+ unit.getName() +"');";
        ResultSet rs = _context.dbCall(sql);
        _context.units.add(unit);
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
        ResultSet rs = _context.dbCall(sql);
        while (rs.next()) {
            units.add(new Unit(rs.getLong("id"), rs.getString("name")));
        }
        return units;
    }

    @Override
    public int deleteAll() {
        //units.removeAll();
        return 0;
    }

    /**
     * This method gets a unit and delete it from the database and in memory observable
     * @param unit
     * @return
     */
    @Override
    public int delete(Unit unit) {
        String sql = "DELETE FROM units WHERE units.id = "+unit.getId()+";";
        ResultSet rs = _context.dbCall(sql);
        _context.units.remove(unit);
        return 0;
    }

    /**
     * This method is used to find a unit by its id.
     * This would be helpful for edit method
     * @param id
     * @return
     */
    @Override
    public Unit findById(long id) {
        // this method should also be implemented using sql
        return _context.units.stream()
                .filter(unit -> unit.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * This method gets the name of a unit and returns its whole data.
     * @param name
     * @return
     */
    @Override
    public Unit findByName(String name) {
        return _context.units.stream()
                .filter(unit -> unit.getName().contains(name))
                .findFirst()
                .orElse(null);
    }

}
