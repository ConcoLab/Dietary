package main;

import daoFactories.ContextFactory;
import models.*;
import views.main.MainGUI;

public class Main {
    public static void main(String[] args){
        ContextFactory _context = new ContextFactory();
        Food test = _context._FoodDao().findByName("Soup");
        Location testLocation = _context._LocationDao().findByName("Home");

        System.out.println();
        MainGUI main = new MainGUI();
//
    }
}
