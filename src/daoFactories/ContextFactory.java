package daoFactories;

import daos.concrete.MysqlFoodDao;
import daos.concrete.MysqlFoodGroupDao;
import daos.concrete.MysqlGroupDao;

public class ContextFactory {


    private MysqlGroupDao _mysqlGroupDao;
    private MysqlFoodGroupDao _mysqlFoodGroupDao;
    private MysqlFoodDao _mysqlFoodDao;
    private Context _context;

    public ContextFactory(){
        _context = new Context();
        _mysqlFoodDao = new MysqlFoodDao(_context);
        _mysqlGroupDao = new MysqlGroupDao(_context);
        _mysqlFoodGroupDao = new MysqlFoodGroupDao(_context);
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


}
