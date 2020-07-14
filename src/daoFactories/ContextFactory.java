package daoFactories;

import daos.concrete.*;

import java.sql.*;

public class ContextFactory {
    private static FoodDao _FoodDao;
    private static FoodGroupDao _FoodGroupDao;
    private static GroupDao _GroupDao;
    private static UnitDao _UnitDao;
    private static MealDao _MealDao;
    private static LocationDao _LocationDao;
    private SqliteConnection _sqliteConnection;

    public ContextFactory(String databaseName) throws SQLException {
        _sqliteConnection = new SqliteConnection(databaseName);
        _FoodDao = new FoodDao(_sqliteConnection);
        _FoodGroupDao = new FoodGroupDao(_sqliteConnection);
        _GroupDao = new GroupDao(_sqliteConnection);
        _UnitDao = new UnitDao(_sqliteConnection);
        _MealDao = new MealDao(_sqliteConnection);
        _LocationDao = new LocationDao(_sqliteConnection);
    }

    public static GroupDao _GroupDao() {
        return _GroupDao;
    }
    public static FoodGroupDao _FoodGroupDao() {
        return _FoodGroupDao;
    }
    public static FoodDao _FoodDao() {
        return _FoodDao;
    }
    public static MealDao _MealDao() {
        return _MealDao;
    }
    public static LocationDao _LocationDao() {
        return _LocationDao;
    }
    public static UnitDao _UnitDao(){return _UnitDao;};
    public SqliteConnection getContext(){return _sqliteConnection;}

}
