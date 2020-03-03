package daos.concrete;

import daoFactories.Context;
import daos.interfaces.UnitDao;
import javafx.collections.ObservableList;
import models.Unit;

import java.util.ArrayList;

public class MysqlUnitDao implements UnitDao {
    private Context _context;

    public MysqlUnitDao(Context context){
        _context = context;
        String [] u = {"g", "ml", "cup", "glass", "piece"};
        int u_size = u.length;
        for (int i = 0; i < u_size; i++)
            this.insert(new Unit(u[i]));
    }
    @Override
    public Unit insert(Unit unit) {
        _context.units.add(unit);
        return null;
    }

    @Override
    public ObservableList<Unit> all() {
        return _context.units;
    }

    @Override
    public int deleteAll() {
        //units.removeAll();
        return 0;
    }

    @Override
    public int delete(Unit unit) {
        _context.units.remove(unit);
        return 0;
    }

    @Override
    public Unit findById(long id) {
        return _context.units.stream()
                .filter(unit -> unit.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Unit findByName(String name) {
        return _context.units.stream()
                .filter(unit -> unit.getName().contains(name))
                .findFirst()
                .orElse(null);
    }
}
