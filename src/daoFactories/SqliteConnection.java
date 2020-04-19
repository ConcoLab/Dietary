package daoFactories;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;

public class SqliteConnection {
    private Connection conn;

    public SqliteConnection(String databaseName) {
        try{
            String url = "jdbc:sqlite:src/db/" + databaseName + ".db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public ResultSet getCall(String sql){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            System.out.println("-- DEBUG: " + sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int deleteCall(long id, String tableName){
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("PRAGMA foreign_keys = ON;");
            String sql = "DELETE FROM "+ tableName +" WHERE "+ tableName +".id = "+ id +";";
            int rs = stmt.executeUpdate(sql);
            System.out.println("-- DEBUG: " + sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    synchronized public long insertCall(String sql){
        try{

            Statement stmt = conn.createStatement();

            int rs = stmt.executeUpdate(sql);
            if(rs == 1){
                ResultSet generatedKeys = stmt.executeQuery("SELECT last_insert_rowid();" );
                if (generatedKeys.next()) {
                    System.out.println("-- DEBUG: new unit id = " + generatedKeys.getLong(1));

                }
                System.out.println("-- DEBUG: " + sql);
                return generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    synchronized public long updateCall(String sql){
        // TODO: the following code should be used to implement the update method
        throw new NotImplementedException();
//        try{
//
//
//            Statement stmt = conn.createStatement();
//            System.out.println("-- DEBUG: " + sql);
////            int rs    = stmt.executeUpdate(sql);
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return 0;
    }

    public ResultSet findByIdCall(long id, String tableName){
        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM "+ tableName +" WHERE "+ tableName +".id = "+ id +" LIMIT 1;";
            ResultSet rs    = stmt.executeQuery(sql);
            System.out.println(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ResultSet truncate(String tableName){
        try{
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM " + tableName + ";";
            ResultSet rs    = stmt.executeQuery(sql);
            System.out.println(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
