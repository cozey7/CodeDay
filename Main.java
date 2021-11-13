import javax.swing.*;

public class Main{
    public static void main(String [] args){
        JFrame frame = new JFrame("My Game");
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}