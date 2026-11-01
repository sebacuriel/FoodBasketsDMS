/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * October 31, 2026
 * Main.java
 * This application asks the user to select an option
 * to either create, import, view, edit, or delete baskets.
 * It is used to more easily prepare meal plans.
 */

import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Main
{
    /**
     * method: main
     * parameters: none
     * return: none
     * purpose: creates a menu for the user to
     * create, import, view, edit, or delete
     * baskets, as well as exiting the program.
     */
    public static void main()
    {
        Controller controller = new Controller();

        JFrame frame = new JFrame("Food Baskets DMS");
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel title = new JLabel("Food Baskets", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(new Color(255, 116, 108));
        title.setBorder(BorderFactory.createEmptyBorder(64, 0, 32, 0));

        JButton CreateBasket = new JButton("Create Basket");
        JButton ImportBaskets = new JButton("Import Baskets");
        JButton ViewBaskets = new JButton("View Baskets");
        JButton ExitProgram = new JButton("Exit Program");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 16));
        buttonPanel.setPreferredSize(new Dimension(240, 240));

        buttonPanel.add(CreateBasket);
        buttonPanel.add(ImportBaskets);
        buttonPanel.add(ViewBaskets);
        buttonPanel.add(ExitProgram);

        JPanel center = new JPanel();
        center.setOpaque(false);

        center.add(buttonPanel);

        Background mainPanel = new Background();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(center, BorderLayout.CENTER);

        frame.add(mainPanel);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CreateBasket.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.CreateBasket();
            }
        });

        ImportBaskets.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.ImportBaskets();
            }
        });

        ViewBaskets.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                controller.ViewBaskets();
            }
        });

        ExitProgram.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });
    }
}