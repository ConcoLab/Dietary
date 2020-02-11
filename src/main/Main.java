package main;

import daoFactories.Context;
import daoFactories.ContextFactory;
import daos.interfaces.FoodDao;
import models.Food;
import models.FoodGroup;
import models.Group;
import views.location.Add;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ContextFactory _context = new ContextFactory();
        Food test = _context.get_mysqlFoodDao().findById(1);
        System.out.println(test.getName());
        ArrayList<Group> groups = _context.get_mysqlGroupDao().all();
        ArrayList<Group> fg = _context.get_mysqlFoodGroupDao().getGroupsOfOneFood(2);
//        System.out.println(test.getName());


//        System.out.println("Let's Start From Here");
//        Add add = new Add();
    }
}
