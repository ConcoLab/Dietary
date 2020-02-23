package main;

import daoFactories.ContextFactory;
import models.*;
import views.main.MainGUI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ContextFactory _context = new ContextFactory();
        Food test = _context.get_mysqlFoodDao().findByName("Soup");
        System.out.println(test.getName());
        Location testLocation = _context.get_mysqlLocationDao().findByName("Home");
        System.out.println(testLocation.getName());
//        LocalDateTime start = LocalDateTime.of(2000, Month.JANUARY, 1, 0,0,0);
//        LocalDateTime end = LocalDateTime.of(2030, Month.JANUARY, 1, 0,0,0);
//        m.findInRange(start,end).forEach(meal -> System.out.print(meal.getMealTypeId() + " "));
        System.out.println();
        MainGUI main = new MainGUI();
//        System.out.println(test.getName());


//        System.out.println("Let's Start From Here");
//        Add add = new Add();
    }
}
