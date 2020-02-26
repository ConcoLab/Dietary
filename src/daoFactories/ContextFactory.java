package daoFactories;

import daos.concrete.*;

public class ContextFactory {

    private static MysqlFoodDao _mysqlFoodDao;
    private static MysqlFoodGroupDao _mysqlFoodGroupDao;
    private static MysqlGroupDao _mysqlGroupDao;
    private static MysqlUnitDao _mysqlUnitDao;
    private static MysqlMealDao _mysqlMealDao;
//    private static MysqlMealTypeDao _mysqlMealTypeDao;
    private static MysqlLocationDao _mysqlLocationDao;
    private Context _context;

    public ContextFactory(){
        _context = new Context();
        _mysqlFoodDao = new MysqlFoodDao(_context);
        _mysqlFoodGroupDao = new MysqlFoodGroupDao(_context);
        _mysqlGroupDao = new MysqlGroupDao(_context);
        _mysqlUnitDao = new MysqlUnitDao(_context);
        _mysqlMealDao = new MysqlMealDao(_context);
//        _mysqlMealTypeDao = new MysqlMealTypeDao(_context);
        _mysqlLocationDao = new MysqlLocationDao(_context);
    }

    public static MysqlGroupDao _GroupDao() {
        return _mysqlGroupDao;
    }
    public static MysqlFoodGroupDao _FoodGroupDao() {
        return _mysqlFoodGroupDao;
    }
    public static MysqlFoodDao _FoodDao() {
        return _mysqlFoodDao;
    }
    public static MysqlMealDao _MealDao() {
        return _mysqlMealDao;
    }
    public static MysqlLocationDao _LocationDao() {
        return _mysqlLocationDao;
    }
    public static MysqlUnitDao _UnitDao(){return _mysqlUnitDao;};
//    public static MysqlMealTypeDao get_mysqlMealTypeDao(){return _mysqlMealTypeDao;}
    public Context getContext(){return _context;}




}
