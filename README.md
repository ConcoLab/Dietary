# COMP5541 - Main Project

### Introduction

This project is a Dietary Management Application, written in Java. The purpose of the app will be to organize ones daily diet plan according to the foods consumed, as well as providing basic information about these foods (e.g. food groups, servings, and calories).

### Prerequisities

As noted previously, the project is written in Java. Most members are using Java 11, although the project will at a later date be modified to work on both desktop and mobile platforms, and so JavaFX will be used. JavaFX is no longer included in versions 11 and above, and so it must be installed separately if one of the versions is being used. See the following link for instructions:

https://www.jetbrains.com/help/idea/javafx.html

Additionally, this project is using JDatePicker (2.0.3) components for certain aspects of the GUI:

https://jdatepicker.org/

### Launch

The application can be run via the main method, and it is equipped with a Java Swing User Interface. 

### Present functionality

The interactive functionality thus far includes the following capabilities:

1. Add units of measurement.
2. Delete units of measurement.
3. Add locations (outdining).
   * location name
   * location address
4. Add food groups.
5. Delete food groups.
6. Add foods.
   * food name
   * quantity
   * units of measurement
   * calories
7. Delete foods.
8. Modify items within diet plan:
   * meal type
   * food name (shows quantity beside it)
   * location
   * number of servings
   * date
9. Add to consumed food list (from diet plan)

The non-interactive functionality thus far includes the following capabilities:

1. Report panel additions
   * automatically adds or removes food group based upon whether an added food meets requirement 

### Future functionality

1. Create a new interface that provides user instructions
2. Remove a food from consumed food list
3. Mark a food serving as eaten or uneaten.
4. Remove a food serving.
5. Separate consumed food list panel into two separate panels for Indining and Outdining
6. Create a database schema that will appropriately deal with Indining and Outdining diets
7. Implementing a class for opening and closing database connection
8. Extend functionality to let all diets to load from database at start-up
