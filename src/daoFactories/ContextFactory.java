package daoFactories;

import daos.concrete.*;
import observers.FoodGroupObserver;
import observers.FoodObserver;
import observers.GroupObserver;
import observers.UnitObserver;

import java.sql.*;

public class ContextFactory {
    private Connection connect() {
        // SQLite connection string

        String url = "jdbc:sqlite:/db/dietary.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private static FoodDao _FoodDao;
    private static FoodGroupDao _FoodGroupDao;
    private static GroupDao _GroupDao;
    private static UnitDao _UnitDao;
    private static MealDao _MealDao;
//    private static MysqlMealTypeDao _mysqlMealTypeDao;
    private static LocationDao _LocationDao;
    private Context _context;

    public ContextFactory() throws SQLException {
        _context = new Context();
        _FoodDao = new FoodDao(_context);
        _FoodGroupDao = new FoodGroupDao(_context);
        _GroupDao = new GroupDao(_context);
        _UnitDao = new UnitDao(_context);
        _MealDao = new MealDao(_context);
//        _mysqlMealTypeDao = new MysqlMealTypeDao(_context);
        _LocationDao = new LocationDao(_context);

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
//    public static MysqlMealTypeDao get_mysqlMealTypeDao(){return _mysqlMealTypeDao;}
    public Context getContext(){return _context;}




}
