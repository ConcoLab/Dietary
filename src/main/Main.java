package main;

import daoFactories.Context;
import daoFactories.ContextFactory;
import daos.concrete.MysqlMealDao;
import daos.interfaces.FoodDao;
import models.Food;
import models.FoodGroup;
import models.Group;
import models.Meal;
import views.location.Add;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args){
        ContextFactory _context = new ContextFactory();
        Food test = _context.get_mysqlFoodDao().findByName("Soup");
        System.out.println(test.getName());
        MysqlMealDao m = new MysqlMealDao();
        LocalDateTime start = LocalDateTime.of(2000, Month.JANUARY, 1, 0,0,0);
        LocalDateTime end = LocalDateTime.of(2030, Month.JANUARY, 1, 0,0,0);
        m.findInRange(start,end).forEach(meal -> System.out.print(meal.getMealTypeId() + " "));
        System.out.println();
        ArrayList<Group> groups = _context.get_mysqlGroupDao().all();
        ArrayList<Group> fg = _context.get_mysqlFoodGroupDao().getGroupsOfOneFood(2);
//        System.out.println(test.getName());


//        System.out.println("Let's Start From Here");
//        Add add = new Add();
    }
}
