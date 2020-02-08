package daos.interfaces;

import models.Unit;
import java.util.ArrayList;

public interface UnitDao {
    Unit insert(Unit unit);
    ArrayList<Unit> all();
    int deleteAll();
    int delete(Unit unit);
    Unit findById(long id);
    Unit findByName(String name);
}
