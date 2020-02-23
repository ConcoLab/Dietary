package daos.concrete;

import daos.interfaces.UnitDao;
import models.Unit;

import java.util.ArrayList;

public class MysqlUnitDao implements UnitDao {
    public static ArrayList<Unit> units = new ArrayList<Unit>();

    public MysqlUnitDao(){
        String [] u = {"g", "ml", "cup", "glass", "piece"};
        int u_size = u.length;
        for (int i = 0; i < u_size; i++)
            this.insert(new Unit(i, u[i]));
    }
    @Override
    public Unit insert(Unit unit) {
        units.add(unit);
        return null;
    }

    @Override
    public ArrayList<Unit> all() {
        return units;
    }

    @Override
    public int deleteAll() {
        //units.removeAll();
        return 0;
    }

    @Override
    public int delete(Unit unit) {
        units.remove(unit);
        return 0;
    }

    @Override
    public Unit findById(long id) {
        return units.stream()
                .filter(unit -> unit.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Unit findByName(String name) {
        return units.stream()
                .filter(unit -> unit.getName().contains(name))
                .findFirst()
                .orElse(null);
    }
}
