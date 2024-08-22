import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.Random;

public class MyGraphics extends JPanel implements ActionListener {
    private Color[][] myBoard;
    private ArrayList<Color> colorList;
    private int look;
    /*
    look 1 is the welcome page w rules n shit
    look 2 is the color selector
    look 3 is the final product
     */
    private JButton look2;
    private JTextField redInsert;
    private JTextField greenInsert;
    private JTextField blueInsert;
    private JButton enter;
    private boolean redFilled;
    private boolean greenFilled;
    private boolean blueFilled;
    private String errorMessage;
    private JButton look3;

    public MyGraphics() {
        myBoard = new Color[20][20];
        colorList = new ArrayList<Color>();
        look = 1;
        look2 = new JButton("continue to generator");
        add(look2);
        look2.addActionListener(this);
        redInsert = new JTextField();
        add(redInsert);
        redInsert.setVisible(false);
        redInsert.setColumns(5);
        greenInsert = new JTextField();
        add(greenInsert);
        greenInsert.setVisible(false);
        greenInsert.setColumns(5);
        blueInsert = new JTextField();
        add(blueInsert);
        blueInsert.setVisible(false);
        blueInsert.setColumns(5);
        enter = new JButton("enter");
        add(enter);
        enter.addActionListener(this);
        enter.setVisible(false);
        redFilled = false;
        greenFilled = false;
        blueFilled = false;
        errorMessage = "";
        look3 = new JButton("i'm done!");
        add(look3);
        look3.addActionListener(this);
        look3.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // just do this
        if (look == 1) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Welcome to the ??? generator !!!", 30, 30);
            look2.setLocation(30, 40);
        } else if (look == 2) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Please enter what colors you'd like to generate with!", 30, 30);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            Dimension textSize = redInsert.getPreferredSize();
            g.drawString("red value:", 30, ((40 + (int) textSize.getHeight()) + 40) / 2);
            redInsert.setLocation(100, 40);
            redFilled = redInsert.getText() != null && !blueInsert.getText().equals("");
            textSize = greenInsert.getPreferredSize();
            g.drawString("green value:", 30, ((60 + (int) textSize.getHeight()) + 60) / 2);
            greenInsert.setLocation(110, 60);
            greenFilled = greenInsert.getText() != null && !greenInsert.getText().equals("");
            textSize = blueInsert.getPreferredSize();
            g.drawString("blue value:", 30, ((80 + (int) textSize.getHeight()) + 80) / 2);
            blueInsert.setLocation(105, 80);
            blueFilled = blueInsert.getText() != null && !blueInsert.getText().equals("");
            enter.setLocation(30, 100);
            g.drawString(errorMessage, 100, 117);
            look3.setLocation(30, 130);
            for (int i = 0; i < colorList.size(); i++) {
                g.setColor(colorList.get(i));
                g.fillRect(30, 160 + i * 15, 10, 10);
            }
        } else if (look == 3) {
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("here is your painting!", 30, 30);
            for (int i = 0; i < myBoard.length; i++) {
                for (int j = 0; j < myBoard[i].length; j++) {
                    g.setColor(myBoard[i][j]);
                    g.fillRect(30 + 10 * j, 50 + 10 * i, 10, 10);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton && e.getSource() == look2) {
            look = 2;
            look2.setVisible(false);
            redInsert.setVisible(true);
            greenInsert.setVisible(true);
            blueInsert.setVisible(true);
            enter.setVisible(true);
        } else if (e.getSource() instanceof JButton && e.getSource() == enter) {
            if (!(redFilled && greenFilled && blueFilled)) {
                errorMessage = "not all fields set!";
            } else if (!(isInteger(redInsert.getText()) && isInteger(greenInsert.getText()) && isInteger(blueInsert.getText()))) {
                errorMessage = "please input integers!";
            } else if (Integer.parseInt(redInsert.getText()) < 0 || Integer.parseInt(redInsert.getText()) > 255 || Integer.parseInt(greenInsert.getText()) < 0 || Integer.parseInt(greenInsert.getText()) > 255 || Integer.parseInt(blueInsert.getText()) < 0 || Integer.parseInt(blueInsert.getText()) > 255) {
                errorMessage = "please input integers between 0 and 255!";
            } else {
                errorMessage = "color successfully added!";
                colorList.add(new Color(Integer.parseInt(redInsert.getText()), Integer.parseInt(greenInsert.getText()), Integer.parseInt(blueInsert.getText())));
                look3.setVisible(true);
            }
        } else if (e.getSource() instanceof JButton && e.getSource() == look3) {
            look = 3;
            look3.setVisible(false);
            enter.setVisible(false);
            redInsert.setVisible(false);
            greenInsert.setVisible(false);
            blueInsert.setVisible(false);
            for (int i = 0; i < myBoard.length; i++) {
                for (int j = 0; j < myBoard[i].length; j++) {
                    myBoard[i][j] = colorList.get((int) (Math.random() * colorList.size()));
                }
            }
        }
    }

    //code copied from https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java
    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException e ) {
            return false;
        }
    }
}