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
    public static boolean create(Unit unit){
        ContextFactory._UnitDao().insert(unit);
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
}
