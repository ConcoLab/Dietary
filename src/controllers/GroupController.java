package controllers;

import daoFactories.ContextFactory;
import models.Group;
import models.Unit;

import java.sql.SQLException;
import java.util.ArrayList;

public class GroupController {
    public static boolean create(Group group){
        ContextFactory._GroupDao().insert(group);
        ContextFactory._GroupDao().notifyObservers();
        return true;
    }

    public static ArrayList<Group> getAll() throws SQLException {
        ArrayList<Group> groups = ContextFactory._GroupDao().all();
        return groups;
    }

    public static boolean delete(long id){
        ContextFactory._GroupDao().delete(id);
        ContextFactory._GroupDao().notifyObservers();
        return true;
    }


    public static String getGroupNames(ArrayList<Group> groups){
        String listOfNames = "";
        for(Group group : groups){
            listOfNames = listOfNames + group.getName() + ", ";
        }

        return listOfNames;
    }

    public static ArrayList<Group> getGroupsOfOneFood(long foodId) throws SQLException {
        return ContextFactory._FoodGroupDao().getGroupsOfOneFood(foodId);
    }
}
