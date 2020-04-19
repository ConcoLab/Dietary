package main;

import daoFactories.ContextFactory;
import models.*;
import views.main.MainGUI;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ContextFactory _context = new ContextFactory("dietary");

        System.out.println();
        MainGUI main = new MainGUI();
//
    }
}
