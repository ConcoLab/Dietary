package daoFactories;

import daos.concrete.*;

public class ContextFactory {

    private MysqlFoodDao _mysqlFoodDao;
    private MysqlFoodGroupDao _mysqlFoodGroupDao;
    private MysqlGroupDao _mysqlGroupDao;
    private MysqlUnitDao _mysqlUnitDao;
    private MysqlFoodMealDao _mysqlFoodMealDao;
    private MysqlMealDao _mysqlMealDao;
    private MysqlMealTypeDao _mysqlMealTypeDao;
    private MysqlLocationDao _mysqlLocationDao;
    private Context _context;

    public ContextFactory(){
        _context = new Context();
        _mysqlFoodDao = new MysqlFoodDao(_context);
        _mysqlFoodGroupDao = new MysqlFoodGroupDao(_context);
        _mysqlGroupDao = new MysqlGroupDao(_context);
        _mysqlUnitDao = new MysqlUnitDao(_context);
        _mysqlFoodMealDao = new MysqlFoodMealDao(_context);
        _mysqlMealDao = new MysqlMealDao(_context);
        _mysqlMealTypeDao = new MysqlMealTypeDao(_context);
        _mysqlLocationDao = new MysqlLocationDao(_context);
    }

    public MysqlGroupDao get_mysqlGroupDao() {
        return _mysqlGroupDao;
    }

    public MysqlFoodGroupDao get_mysqlFoodGroupDao() {
        return _mysqlFoodGroupDao;
    }

    public MysqlFoodDao get_mysqlFoodDao() {
        return _mysqlFoodDao;
    }

    public MysqlFoodMealDao get_mysqlFoodMealDao() {
        return _mysqlFoodMealDao;
    }

    public MysqlMealDao get_mysqlMealDao() {
        return _mysqlMealDao;
    }

    public MysqlLocationDao get_mysqlLocationDao() {
        return _mysqlLocationDao;
    }


}
