import javax.swing.*;
import java.awt.*;

public class Background extends JPanel
{
    Image image;

    public Background()
    {
        ImageIcon fruitBG = new ImageIcon("src/Images/FruitBG.jpg");

        image = fruitBG.getImage();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(image, 0,0, getWidth(), getHeight(), this);
    }
}