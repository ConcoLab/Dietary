# COMP5541 - Main Project
​
### Introduction
​
This project is a Dietary Management Application, written in Java. 
The purpose of the app will be to organize ones daily diet plan according to the foods consumed, 
as well as providing basic information about these foods (e.g. food groups, servings, and calories).
​
### Prerequisities
​
As noted previously, the project is written in Java. Most members are using Java 11, although the project 
will at a later date be modified to work on both desktop and mobile platforms, and so JavaFX will be used. 
​
JavaFX is no longer included in versions 11 and above, and so it must be installed separately if one of the versions 
is being used. See the following link for instructions:
​
https://www.jetbrains.com/help/idea/javafx.html
​
Additionally, this project is using JDatePicker (2.0.3) components for certain aspects of the GUI:
​
https://jdatepicker.org/
​
### Launch
​
The application can be run via the main method, and it is equipped with a Java Swing User Interface. 
​
### Present functionality
​
The interactive functionality thus far includes the following capabilities:
​
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
   * fat
   * carbohydrate
   * salt
   * protein
7. Delete foods.
8. Modify items within diet plan:
   * meal type
   * food name (shows quantity beside it)
   * location
   * number of servings
   * date
9.  Add to diet (from diet plan).
10. Filter foods as indining, outdining, or both.
11. Filter foods by time.
12. Mark a food as consumed/unconsumed.
13. Hide consumed foods/unhide consumed foods.
14. Hide unconsumed foods/unhide unconsumed foods.
15. Remove a food serving.
16. A database schema that appropriately deals with indining and outdining diets.
17. Automatic save-state for the database.
18. Extend functionality to let all diets to load from database at start-up.
19. .jar executable to run the application from the command line.
​
The non-interactive functionality thus far includes the following capabilities:
​
16. Report panel additions
    * adds caloric and nutritional information according to what is being filtered
    * automatically adds or removes food group based upon whether displayed foods meet requirement 
