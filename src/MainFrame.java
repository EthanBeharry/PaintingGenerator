import javax.swing.*;

public class MainFrame extends JFrame implements Runnable {
    MyGraphics gpanel;

    public MainFrame() {
        JFrame frame = new JFrame("matice generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        frame.setLocation(100, 50);

        gpanel = new MyGraphics();
        frame.add(gpanel);

        frame.setVisible(true);

        Thread t = new Thread(this);
        t.start();
    }

    public void run() {
        while (true) {
            gpanel.repaint();
        }
    }
}
