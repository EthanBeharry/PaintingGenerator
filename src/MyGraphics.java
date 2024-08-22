import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Graphics extends JPanel {
    private BufferedImage ermage;
    double centerx;
    double centery;
    final int R = 30;
    double theta;


    public Graphics() {
        theta = 0;
        centerx = 200;
        centery = 50;
        try {
            ermage = ImageIO.read(new File("src/stanloona.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        g.setColor(new Color(150, 75, 0)); //brown
        g.drawLine(80, 80, 50, 50);
        g.setColor(Color.RED);
        g.fillRect(50, 100, 40, 20);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Bodoni MT", Font.BOLD, 18));
        g.drawString("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 50, 300);
        theta += 0.01;
        if (theta >= 2 * Math.PI) {
            theta = 0;
        }
        g.drawImage(ermage, (int) (centerx + R * Math.cos(theta)), (int) (centery + R * Math.sin(theta)), null); //moves it in a circle
        System.out.println(theta);
    }
}
