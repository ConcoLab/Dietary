package daoFactories;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;

import java.util.ArrayList;

public class Context {
    public ObservableList<Food> foods;
    public ObservableList<FoodGroup> foodGroups;
    public ObservableList<Group> groups;
    public ObservableList<Unit> units;
    public ObservableList<Meal> meals;
    public ObservableList<Location> locations;

    public Context() {
        foods = FXCollections.observableList(new ArrayList<Food>());
        foodGroups = FXCollections.observableList(new ArrayList<FoodGroup>());
        groups = FXCollections.observableList(new ArrayList<Group>());
        units = FXCollections.observableList(new ArrayList<Unit>());
        meals = FXCollections.observableList(new ArrayList<Meal>());
        locations = FXCollections.observableList(new ArrayList<Location>());
    }
}
