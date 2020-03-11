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

    public Context() {
        foods = FXCollections.observableList(new ArrayList<Food>());
        foodGroups = FXCollections.observableList(new ArrayList<FoodGroup>());
        groups = FXCollections.observableList(new ArrayList<Group>());
        units = FXCollections.observableList(new ArrayList<Unit>());
        meals = FXCollections.observableList(new ArrayList<Meal>());
        locations = FXCollections.observableList(new ArrayList<Location>());
    }

    public ResultSet dbCall(String sql){
        try{
            String url = "jdbc:sqlite:./src/db/dietary.db";
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
