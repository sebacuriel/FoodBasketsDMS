/**
 * Sebastian Yael Curiel Franco
 * Software Development I - CEN 3024C - 13038
 * November 6, 2026
 * Background.java
 * This class handles the application's background.
 * It gets the location of the image file.
 */

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel
{
    Image image;

    public Background()
    {
        java.net.URL imageDir = Background.class.getResource("/Images/FruitBG.jpg");

        ImageIcon fruitBG = new ImageIcon(imageDir);

        image = fruitBG.getImage();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(image, 0,0, getWidth(), getHeight(), this);
    }
}