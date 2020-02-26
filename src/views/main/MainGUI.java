package views.main;

import daoFactories.ContextFactory;
import javafx.collections.ObservableList;
import models.*;
import views.panels.*;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainGUI extends JFrame{
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel southPanel;
    private JPanel rightPanel;
    private JPanel rightTopPanel;
    private JPanel leftPanel;
    private JMenuBar mainMenuBar;
    private JLabel statusText;
    private JTabbedPane tabbedPane;


    public MainGUI(){
        // DATA
        ObservableList<Food> foods = ContextFactory._FoodDao().all();
        ObservableList<Unit> units = ContextFactory._UnitDao().all();
        ObservableList<Group> groups = ContextFactory._GroupDao().all();
        ObservableList<Location> locations = ContextFactory._LocationDao().all();
        ObservableList<Meal> meals = ContextFactory._MealDao().all();
        String[] mealTypes = {"Breakfast", "Lunch", "Dinner", "Brunch", "Coffee"};

        // Init panels
//        mealPanel = new MealPanel(foods, units, groups, locations, mealTypes);

        setTitle("Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainPanel = new JPanel();

        add(mainPanel);

        mainPanel.setLayout(new BorderLayout(3,3));
        northPanel = new JPanel();
        mainPanel.add(northPanel,BorderLayout.NORTH);
        northPanel.setLayout(new GridLayout());
        mainMenuBar = new JMenuBar();
        northPanel.add(mainMenuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        mainMenuBar.add(fileMenu);
        mainMenuBar.add(helpMenu);

        JMenuItem foodGroupMenu = new JMenuItem("Food Groups");



        JMenuItem unitMenu = new JMenuItem("Units");



        JMenuItem foodMenu = new JMenuItem("Foods");



        JMenuItem mealMenu = new JMenuItem("Meals");
        JMenuItem mealTypeMenu = new JMenuItem("Meal Types");
        JMenuItem locationMenu = new JMenuItem("Locations");


        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener((e)->System.exit(0));

        fileMenu.add(exitMenu);

        leftPanel = new JPanel();
        mainPanel.add(leftPanel,BorderLayout.LINE_START);
        leftPanel.setLayout(new GridLayout());
        tabbedPane = new JTabbedPane();
        leftPanel.add(tabbedPane);
        tabbedPane.addTab("Meals", null, new MealPanel(foods, units, groups, locations, mealTypes));
        tabbedPane.addTab("Foods", null, new FoodPanel(foods, units, groups));
        tabbedPane.addTab("Food Groups", null, new GroupPanel(groups));
        tabbedPane.addTab("Locations", null, new LocationPanel(locations));
        tabbedPane.addTab("Units", null, new UnitPanel(units));

        rightPanel = new JPanel();
        mainPanel.add(rightPanel,BorderLayout.CENTER);
        rightPanel.setLayout(new GridLayout(0,1));
        rightPanel.add(new EatenMealPanel(meals, foods, mealTypes, locations, groups));
        rightPanel.add(new ReportPanel(meals, groups));


//
        tabbedPane = new JTabbedPane();
//        rightPanel.add(tabbedPane);
//        JPanel pnlTab = new JPanel(new FlowLayout());
//        pnlTab.setOpaque(false);
//        JLabel lblTitle = new JLabel("Food Groups");
//        JButton btnClose = new JButton("x");
//        btnClose.setBorderPainted(false);
//        btnClose.setContentAreaFilled(false);
//        pnlTab.add(lblTitle);
//        pnlTab.add(btnClose);
//        tabbedPane.addTab("Tab 1",null, new JPanel());
//        int index = tabbedPane.indexOfTab("Tab 1");
//        tabbedPane.setTabComponentAt(index, pnlTab);
        southPanel = new JPanel();
        mainPanel.add(southPanel,BorderLayout.SOUTH);

        southPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
        BorderFactory.createMatteBorder(2, 0, 0, 0, Color.BLACK)));
        //southPanel.setLayout();
        statusText = new JLabel();
        statusText.setText(""+ new SimpleDateFormat("dd/MM/yy").format(new Date()));
        //southPanel.add(statusText);

        pack();
//        setLocationRelativeTo(null);
//        setSize(300,300);
        setVisible(true);

    }


}
