package main;

import daoFactories.ContextFactory;
import models.*;
import views.main.MainGUI;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ContextFactory _context = new ContextFactory();
        Food test = _context.get_mysqlFoodDao().findByName("Soup");
        Location testLocation = _context.get_mysqlLocationDao().findByName("Home");

        System.out.println();
        MainGUI main = new MainGUI();
//
    }
}
