package daoFactories;

import daos.concrete.*;

public class ContextFactory {

    private static MysqlFoodDao _mysqlFoodDao;
    private static MysqlFoodGroupDao _mysqlFoodGroupDao;
    private static MysqlGroupDao _mysqlGroupDao;
    private static MysqlUnitDao _mysqlUnitDao;
    private static MysqlMealDao _mysqlMealDao;
    private static MysqlLocationDao _mysqlLocationDao;
    private Context _context;

    public ContextFactory() {
        _context = new Context();
        _mysqlFoodDao = new MysqlFoodDao(_context);
        _mysqlFoodGroupDao = new MysqlFoodGroupDao(_context);
        _mysqlGroupDao = new MysqlGroupDao(_context);
        _mysqlUnitDao = new MysqlUnitDao(_context);
        _mysqlMealDao = new MysqlMealDao(_context);
        _mysqlLocationDao = new MysqlLocationDao(_context);
    }

    public static MysqlGroupDao get_mysqlGroupDao() {
        return _mysqlGroupDao;
    }

    public static MysqlFoodGroupDao get_mysqlFoodGroupDao() {
        return _mysqlFoodGroupDao;
    }

    public static MysqlFoodDao get_mysqlFoodDao() {
        return _mysqlFoodDao;
    }

    public static MysqlMealDao get_mysqlMealDao() {
        return _mysqlMealDao;
    }

    public static MysqlLocationDao get_mysqlLocationDao() {
        return _mysqlLocationDao;
    }

    public static MysqlUnitDao get_mysqlUnitDao() {
        return _mysqlUnitDao;
    }

    public Context getContext() {
        return _context;
    }


}
