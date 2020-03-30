package daoFactories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;

import java.sql.*;
import java.util.ArrayList;

public class Context {
    public static ObservableList<Food> foods;
    public static ObservableList<FoodGroup> foodGroups;
    public static ObservableList<Group> groups;
    public static ObservableList<Unit> units;
    public static ObservableList<Meal> meals;
    public static ObservableList<Location> locations;
    private Connection conn;

    public Context() {
        foods = FXCollections.observableList(new ArrayList<Food>());
        foodGroups = FXCollections.observableList(new ArrayList<FoodGroup>());
        groups = FXCollections.observableList(new ArrayList<Group>());
//        units = FXCollections.observableList(new ArrayList<Unit>());
        units = FXCollections.observableList(new ArrayList<Unit>());
        meals = FXCollections.observableList(new ArrayList<Meal>());
        locations = FXCollections.observableList(new ArrayList<Location>());

        try{
            String url = "jdbc:sqlite:./src/db/dietary.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public ResultSet getCall(String sql){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
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
            System.out.println(sql);
            int rs = stmt.executeUpdate(sql);
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
                return generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    synchronized public long updateCall(String sql){
        try{

            Statement stmt = conn.createStatement();

            int rs    = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public ResultSet findByIdCall(long id, String tableName){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM "+ tableName +" WHERE "+ tableName +".id = "+ id +" LIMIT 1;");
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
