package views.main;

import javafx.scene.control.DatePicker;
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
        JMenu dbMenu = new JMenu("DB");
        JMenu helpMenu = new JMenu("Help");

        mainMenuBar.add(fileMenu);
        mainMenuBar.add(dbMenu);
        mainMenuBar.add(helpMenu);

        JMenuItem foodGroupMenu = new JMenuItem("Food Groups");

        foodGroupMenu.addActionListener((e) -> {
            addTabPane(foodGroupMenu.getText());
        });

        JMenuItem unitMenu = new JMenuItem("Units");

        unitMenu.addActionListener((e) -> {
            addTabPane(unitMenu.getText());
        });

        JMenuItem foodMenu = new JMenuItem("Foods");

        foodMenu.addActionListener((e) -> {
            addTabPane(foodMenu.getText());
        });

        JMenuItem mealMenu = new JMenuItem("Meals");
        JMenuItem mealTypeMenu = new JMenuItem("Meal Types");
        JMenuItem locationMenu = new JMenuItem("Locations");

        locationMenu.addActionListener((e) -> {
            addTabPane(locationMenu.getText());
        });

        JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.addActionListener((e)->System.exit(0));

        fileMenu.add(exitMenu);
        dbMenu.add(foodGroupMenu);
        dbMenu.add(unitMenu);
        dbMenu.add(foodMenu);
        dbMenu.add(mealMenu);
        dbMenu.add(mealTypeMenu);
        dbMenu.add(locationMenu);

        leftPanel = new JPanel();
        mainPanel.add(leftPanel,BorderLayout.LINE_START);
        leftPanel.setLayout(new GridLayout());
        tabbedPane = new JTabbedPane();
        leftPanel.add(tabbedPane);
        tabbedPane.addTab("Units", null, new UnitPanel());
        tabbedPane.addTab("Locations", null, new LocationPanel());
        tabbedPane.addTab("Food Groups", null, new GroupPanel());
        tabbedPane.addTab("Foods", null, new FoodPanel());

        rightPanel = new JPanel();
        mainPanel.add(rightPanel,BorderLayout.CENTER);
        rightPanel.setLayout(new GridLayout(0,1));
        rightPanel.add(new EatenMealsPanel());
        rightPanel.add(new ReportPanel());


//        tabbedPane = new JTabbedPane();
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

    public void addTabPane(String name){
        int index = tabbedPane.indexOfTab(name);
        if (index == -1) {
            if (name.contains("Food Groups"))
                tabbedPane.addTab(name, null, new GroupPanel());
            if (name.contains("Units"))
                tabbedPane.addTab(name, null, new UnitPanel());
            if (name.contains("Locations"))
                tabbedPane.addTab(name, null, new LocationPanel());
            if (name.contains("Foods"))
                tabbedPane.addTab(name, null, new FoodPanel());
        }

        index = tabbedPane.indexOfTab(name);
        tabbedPane.setSelectedIndex(index);

//        index = tabbedPane.indexOfTab(name);
//        JPanel panelTab = new JPanel(new FlowLayout());
//        panelTab.setOpaque(false);
//        JLabel tabLabel = new JLabel(name);
//        JButton buttonCloseTab = new JButton("x");
//        buttonCloseTab.setBorderPainted(false);
//        buttonCloseTab.setContentAreaFilled(false);
//        panelTab.add(tabLabel);
//        panelTab.add(buttonCloseTab);
//        tabbedPane.setTabComponentAt(index, panelTab);

    }

}
