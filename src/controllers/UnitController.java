package controllers;

import daoFactories.ContextFactory;
import javafx.collections.ObservableList;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;

public class UnitController {
    /**
     * This action is used to create a new record on the database.
     * Views should use this method to create a new unit.
     * @param unit
     * @return
     */
    public static boolean create(Unit unit) throws SQLException {
        ContextFactory._UnitDao().insert(unit);
        ContextFactory._UnitDao().notifyObservers();
        return true;
    }

    /**
     * This method receives all the units from the database and passes to the view
     * Be aware that anywhere in view, you should use this method to receive the list of units.
     * @return ArrayList<Unit>
     * @throws SQLException
     */
    public static ArrayList<Unit> getAllUnits() throws SQLException {
        return ContextFactory._UnitDao().all();
    }

    public static boolean delete(long id){
        ContextFactory._UnitDao().delete(id);
        ContextFactory._UnitDao().notifyObservers();
        return true;
    }

    public static Unit getById(long id) throws SQLException {
        Unit unit = ContextFactory._UnitDao().findById(id);
        return unit;
    }


}
