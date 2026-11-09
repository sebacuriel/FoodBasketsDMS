/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * November 6, 2026
 * Controller.java
 * This class handles all the methods called by the Main class.
 * It contains a GetDatabase method to obtain a database from the user.
 * It contains a LoadDatabase method to load the data from the database.
 * It contains a CreateBasket method to handle manually adding baskets.
 * It contains a ViewBaskets method to see the list of created baskets.
 * It contains an EditBasket method to edit information about a selected basket.
 * It contains a CalculateCalories method to automatically get the basket's calories.
 * It contains a CalculatePrepTime method to automatically get the basket's preparation time.
 */

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Controller
{
    private final ArrayList<Basket> baskets = new ArrayList<>();

    private final FoodList foodList = new FoodList();

    private Connection connection;

    /**
     * method: GetDatabase
     * parameters: none
     * return: boolean
     * purpose: obtains a database from the user
     */
    public boolean GetDatabase()
    {
        while (true)
        {
            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Select an SQLite Database");

            int c = chooser.showOpenDialog(null);

            if (c != JFileChooser.APPROVE_OPTION) return false;

            File file = chooser.getSelectedFile();

            try
            {
                connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());

                Statement statement = connection.createStatement();

                statement.executeQuery("SELECT BasketID, BasketName, Fruit1, Fruit2, Fruit3, Additive1, Additive2 FROM Basket");

                statement.close();

                return true;
            }

            catch (SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Invalid Database, try again.");
            }
        }
    }

    /**
     * method: LoadDatabase
     * parameters: none
     * return: none
     * purpose: adds the baskets from the database
     */
    public void LoadDatabase()
    {
        try
        {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM Basket");

            while (rs.next())
            {
                String basketName = rs.getString("BasketName");

                if (basketName == null || basketName.isBlank())
                {
                    basketName = "Unnamed";
                }

                Food fruit1 = null;
                Food fruit2 = null;
                Food fruit3 = null;
                Food additive1 = null;
                Food additive2 = null;

                for (Food food : foodList.getFoods())
                {
                    if (food.getFoodName().equalsIgnoreCase(rs.getString("Fruit1")))
                    {
                        fruit1 = food;
                    }

                    if (food.getFoodName().equalsIgnoreCase(rs.getString("Fruit2")))
                    {
                        fruit2 = food;
                    }

                    if (food.getFoodName().equalsIgnoreCase(rs.getString("Fruit3")))
                    {
                        fruit3 = food;
                    }

                    if (food.getFoodName().equalsIgnoreCase(rs.getString("Additive1")))
                    {
                        additive1 = food;
                    }

                    if (food.getFoodName().equalsIgnoreCase(rs.getString("Additive2")))
                    {
                        additive2 = food;
                    }
                }

                Basket basket = new Basket(basketName, fruit1, fruit2, fruit3, additive1, additive2);
                basket.setBasketID(rs.getInt("BasketID"));

                baskets.add(basket);
            }
        }

        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Invalid Database, try again.");
        }
    }

    /**
     * method: CreateBasket
     * parameters: none
     * return: none
     * purpose: create baskets and add them to the database
     */
    public void CreateBasket()
    {
        JFrame frame = new JFrame("Create Basket");
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JTextField name = new JTextField();

        JComboBox<Food> fruit1 = new JComboBox<>();
        JComboBox<Food> fruit2 = new JComboBox<>();
        JComboBox<Food> fruit3 = new JComboBox<>();
        JComboBox<Food> additive1 = new JComboBox<>();
        JComboBox<Food> additive2 = new JComboBox<>();

        JLabel calories = new JLabel("Total Calories: 0 calories.");
        JLabel prepTime = new JLabel("Preparation Time: 0.0 seconds.");

        JButton Confirm = new JButton("Create Basket");
        JButton Exit =  new JButton("Exit to Menu");

        fruit1.addItem(null);
        fruit2.addItem(null);
        fruit3.addItem(null);
        additive1.addItem(null);
        additive2.addItem(null);

        for (Food food : foodList.getFoods())
        {
            if (food.getCategory().equals("Fruit"))
            {
                fruit1.addItem(food);
                fruit2.addItem(food);
                fruit3.addItem(food);
            }

            else if (food.getCategory().equals("Additive"))
            {
                additive1.addItem(food);
                additive2.addItem(food);
            }
        }

        ActionListener calc = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Food f1 = (Food) fruit1.getSelectedItem();
                Food f2 = (Food) fruit2.getSelectedItem();
                Food f3 = (Food) fruit3.getSelectedItem();
                Food a1 = (Food) additive1.getSelectedItem();
                Food a2 = (Food) additive2.getSelectedItem();

                calories.setText("Total Calories: " + CalculateCalories(f1, f2, f3, a1, a2) + " calories.");
                prepTime.setText("Preparation Time: " + CalculatePrepTime(f1, f2, f3, a1, a2) + " seconds.");
            }
        };

        fruit1.addActionListener(calc);
        fruit2.addActionListener(calc);
        fruit3.addActionListener(calc);
        additive1.addActionListener(calc);
        additive2.addActionListener(calc);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setPreferredSize(new Dimension(300, 500));

        mainPanel.add(new JLabel("Basket Name: "));
        mainPanel.add(name);

        mainPanel.add(new JLabel("Fruit 1: "));
        mainPanel.add(fruit1);

        mainPanel.add(new JLabel("Fruit 2: "));
        mainPanel.add(fruit2);

        mainPanel.add(new JLabel("Fruit 3: "));
        mainPanel.add(fruit3);

        mainPanel.add(new JLabel("Additive 1: "));
        mainPanel.add(additive1);

        mainPanel.add(new JLabel("Additive 2: "));
        mainPanel.add(additive2);

        mainPanel.add(new JLabel(""));
        mainPanel.add(calories);
        mainPanel.add(prepTime);

        mainPanel.add(new JLabel(""));
        mainPanel.add(Confirm);
        mainPanel.add(new JLabel(""));
        mainPanel.add(Exit);

        Background center = new Background();
        center.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));

        center.add(mainPanel);

        frame.add(center);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Confirm.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Food f1 = (Food) fruit1.getSelectedItem();
                Food f2 = (Food) fruit2.getSelectedItem();
                Food f3 = (Food) fruit3.getSelectedItem();
                Food a1 = (Food) additive1.getSelectedItem();
                Food a2 = (Food) additive2.getSelectedItem();

                if (f1 == null && f2 == null && f3 == null && a1 == null && a2 == null)
                {
                    JOptionPane.showMessageDialog(frame, "Select at least one food item.");
                }

                else
                {
                    String basketName = name.getText();

                    if (basketName.isBlank())
                    {
                        basketName = "Unnamed";
                    }

                    try
                    {
                        PreparedStatement statement = connection.prepareStatement("INSERT INTO Basket (BasketName, Fruit1, Fruit2, Fruit3, Additive1, Additive2) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                        statement.setString(1, basketName);
                        statement.setString(2, f1 == null ? null : f1.getFoodName());
                        statement.setString(3, f2 == null ? null : f2.getFoodName());
                        statement.setString(4, f3 == null ? null : f3.getFoodName());
                        statement.setString(5, a1 == null ? null : a1.getFoodName());
                        statement.setString(6, a2 == null ? null : a2.getFoodName());

                        statement.executeUpdate();

                        ResultSet rs = statement.getGeneratedKeys();

                        Basket basket = new Basket(basketName, f1, f2, f3, a1, a2);

                        if (rs.next()) basket.setBasketID(rs.getInt(1));

                        baskets.add(basket);

                        JOptionPane.showMessageDialog(frame, "Basket successfully created.");

                        frame.dispose();
                    }

                    catch (SQLException ee)
                    {
                        JOptionPane.showMessageDialog(frame, "Could not create the basket.");
                    }
                }
            }
        });

        Exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });
    }

    /**
     * method: ViewBaskets
     * parameters: none
     * return: none
     * purpose: show all the created baskets
     * select a row to edit or delete them
     */
    public void ViewBaskets()
    {
        JFrame frame = new JFrame("View Baskets");
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JButton EditBasket =  new JButton("Edit Basket");
        JButton DeleteBasket = new JButton("Delete Basket");
        JButton Exit = new JButton("Exit to Menu");

        String[] columns = { "Name", "Fruit 1", "Fruit 2", "Fruit 3", "Additive 1", "Additive 2", "Calories", "Prep Time" };
        String[][] data = new String[baskets.size()][8];

        for (int i = 0; i < baskets.size(); i++)
        {
            Basket basket = baskets.get(i);

            data[i][0] = basket.getBasketName();
            data[i][1] = basket.getFruit1() == null ? "" : basket.getFruit1().getFoodName();
            data[i][2] = basket.getFruit2() == null ? "" : basket.getFruit2().getFoodName();
            data[i][3] = basket.getFruit3() == null ? "" : basket.getFruit3().getFoodName();
            data[i][4] = basket.getAdditive1() == null ? "" : basket.getAdditive1().getFoodName();
            data[i][5] = basket.getAdditive2() == null ? "" : basket.getAdditive2().getFoodName();
            data[i][6] = String.valueOf(CalculateCalories(basket.getFruit1(), basket.getFruit2(), basket.getFruit3(), basket.getAdditive1(), basket.getAdditive2()));
            data[i][7] = String.valueOf(CalculatePrepTime(basket.getFruit1(), basket.getFruit2(), basket.getFruit3(), basket.getAdditive1(), basket.getAdditive2()));
        }

        JTable table = new JTable(data, columns);
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 450));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        Background mainPanel = new Background();
        mainPanel.setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel();
        tablePanel.setOpaque(false);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(64, 0, 0, 0));

        tablePanel.add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 0));

        buttonPanel.add(EditBasket);
        buttonPanel.add(DeleteBasket);
        buttonPanel.add(Exit);

        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        EditBasket.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int row = table.getSelectedRow();

                if (row == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Select a basket by clicking on it.");
                }

                else
                {
                     Basket basket = baskets.get(row);

                     EditBasket(basket);

                     frame.dispose();
                }
            }
        });

        DeleteBasket.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int row = table.getSelectedRow();

                if (row == -1)
                {
                    JOptionPane.showMessageDialog(frame, "Select a basket by clicking on it.");
                }

                else
                {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Delete the selected basket?", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION)
                    {
                        Basket basket = baskets.get(row);

                        try
                        {
                            PreparedStatement statement = connection.prepareStatement("DELETE FROM Basket WHERE BasketID = ?");

                            statement.setInt(1, basket.getBasketID());

                            statement.executeUpdate();

                            baskets.remove(row);

                            ViewBaskets();

                            frame.dispose();
                        }

                        catch (SQLException ee)
                        {
                            JOptionPane.showMessageDialog(frame, "Could not delete the basket.");
                        }
                    }
                }
            }
        });

        Exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });
    }

    /**
     * method: EditBasket
     * parameters: Basket
     * return: none
     * purpose: edit a selected basket's contents
     */
    public void EditBasket(Basket basket)
    {
        JFrame frame = new JFrame("Edit Basket");
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JTextField name = new JTextField(basket.getBasketName());

        JComboBox<Food> fruit1 = new JComboBox<>();
        JComboBox<Food> fruit2 = new JComboBox<>();
        JComboBox<Food> fruit3 = new JComboBox<>();
        JComboBox<Food> additive1 = new JComboBox<>();
        JComboBox<Food> additive2 = new JComboBox<>();

        JLabel calories = new JLabel("Total Calories: 0 calories.");
        JLabel prepTime = new JLabel("Preparation Time: 0.0 seconds.");

        JButton Confirm = new JButton("Save Changes");
        JButton Exit =  new JButton("Exit to Menu");

        fruit1.addItem(null);
        fruit2.addItem(null);
        fruit3.addItem(null);
        additive1.addItem(null);
        additive2.addItem(null);

        for (Food food : foodList.getFoods())
        {
            if (food.getCategory().equals("Fruit"))
            {
                fruit1.addItem(food);
                fruit2.addItem(food);
                fruit3.addItem(food);
            }

            else if (food.getCategory().equals("Additive"))
            {
                additive1.addItem(food);
                additive2.addItem(food);
            }
        }

        fruit1.setSelectedItem(basket.getFruit1());
        fruit2.setSelectedItem(basket.getFruit2());
        fruit3.setSelectedItem(basket.getFruit3());
        additive1.setSelectedItem(basket.getAdditive1());
        additive2.setSelectedItem(basket.getAdditive2());

        ActionListener calc = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Food f1 = (Food) fruit1.getSelectedItem();
                Food f2 = (Food) fruit2.getSelectedItem();
                Food f3 = (Food) fruit3.getSelectedItem();
                Food a1 = (Food) additive1.getSelectedItem();
                Food a2 = (Food) additive2.getSelectedItem();

                calories.setText("Total Calories: " + CalculateCalories(f1, f2, f3, a1, a2) + " calories.");
                prepTime.setText("Preparation Time: " + CalculatePrepTime(f1, f2, f3, a1, a2) + " seconds.");
            }
        };

        fruit1.addActionListener(calc);
        fruit2.addActionListener(calc);
        fruit3.addActionListener(calc);
        additive1.addActionListener(calc);
        additive2.addActionListener(calc);

        calc.actionPerformed(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        mainPanel.setPreferredSize(new Dimension(300, 500));

        mainPanel.add(new JLabel("Basket Name: "));
        mainPanel.add(name);

        mainPanel.add(new JLabel("Fruit 1: "));
        mainPanel.add(fruit1);

        mainPanel.add(new JLabel("Fruit 2: "));
        mainPanel.add(fruit2);

        mainPanel.add(new JLabel("Fruit 3: "));
        mainPanel.add(fruit3);

        mainPanel.add(new JLabel("Additive 1: "));
        mainPanel.add(additive1);

        mainPanel.add(new JLabel("Additive 2: "));
        mainPanel.add(additive2);

        mainPanel.add(new JLabel(""));
        mainPanel.add(calories);
        mainPanel.add(prepTime);

        mainPanel.add(new JLabel(""));
        mainPanel.add(Confirm);
        mainPanel.add(new JLabel(""));
        mainPanel.add(Exit);

        Background center = new Background();
        center.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));

        center.add(mainPanel);

        frame.add(center);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Confirm.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Food f1 = (Food) fruit1.getSelectedItem();
                Food f2 = (Food) fruit2.getSelectedItem();
                Food f3 = (Food) fruit3.getSelectedItem();
                Food a1 = (Food) additive1.getSelectedItem();
                Food a2 = (Food) additive2.getSelectedItem();

                if (f1 == null && f2 == null && f3 == null && a1 == null && a2 == null)
                {
                    JOptionPane.showMessageDialog(frame, "Select at least one food item.");
                }

                else
                {
                    String basketName = name.getText();

                    if (basketName.isBlank())
                    {
                        basketName = "Unnamed";
                    }

                    try
                    {
                        PreparedStatement statement = connection.prepareStatement("UPDATE Basket SET BasketName = ?, Fruit1 = ?, Fruit2 = ?, Fruit3 = ?, Additive1 = ?, Additive2 = ? WHERE BasketID = ?");

                        statement.setString(1, basketName);
                        statement.setString(2, f1 == null ? null : f1.getFoodName());
                        statement.setString(3, f2 == null ? null : f2.getFoodName());
                        statement.setString(4, f3 == null ? null : f3.getFoodName());
                        statement.setString(5, a1 == null ? null : a1.getFoodName());
                        statement.setString(6, a2 == null ? null : a2.getFoodName());

                        statement.setInt(7, basket.getBasketID());

                        statement.executeUpdate();

                        basket.setBasketName(basketName);
                        basket.setFruit1(f1);
                        basket.setFruit2(f2);
                        basket.setFruit3(f3);
                        basket.setAdditive1(a1);
                        basket.setAdditive2(a2);

                        JOptionPane.showMessageDialog(frame, "Basket successfully edited.");

                        frame.dispose();
                    }

                    catch (SQLException ee)
                    {
                        JOptionPane.showMessageDialog(frame, "Could not edit the basket.");
                    }
                }
            }
        });

        Exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });
    }

    /**
     * method: CalculateCalories
     * parameters: Food x5
     * return: int
     * purpose: automatically calculate a basket's
     * calories using the selected food objects' data.
     */
    public int CalculateCalories(Food fruit1, Food fruit2, Food fruit3, Food additive1, Food additive2)
    {
        int c = 0;

        if (fruit1 != null)
        {
            c += fruit1.getCalories();
        }

        if (fruit2 != null)
        {
            c += fruit2.getCalories();
        }

        if (fruit3 != null)
        {
            c += fruit3.getCalories();
        }

        if (additive1 != null)
        {
            c += additive1.getCalories();
        }

        if (additive2 != null)
        {
            c += additive2.getCalories();
        }

        return c;
    }

    /**
     * method: CalculatePrepTime
     * parameters: Food x5
     * return: double
     * purpose: automatically calculate a basket's
     * prep time using the selected food objects' data.
     */
    public double CalculatePrepTime(Food fruit1, Food fruit2, Food fruit3, Food additive1, Food additive2)
    {
        double t = 0;

        if (fruit1 != null)
        {
            t += fruit1.getPrepTime();
        }

        if (fruit2 != null)
        {
            t += fruit2.getPrepTime();
        }

        if (fruit3 != null)
        {
            t += fruit3.getPrepTime();
        }

        if (additive1 != null)
        {
            t += additive1.getPrepTime();
        }

        if (additive2 != null)
        {
            t += additive2.getPrepTime();
        }

        return t;
    }
}