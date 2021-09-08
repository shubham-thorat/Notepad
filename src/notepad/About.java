package notepad;

import javax.swing.JFrame;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class About extends JFrame implements ActionListener {

    JButton b1;

    About() {
        setBounds(300, 100, 700, 600);
        setLayout(null);
        //ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("notepad/windows.png"));
        ImageIcon i1 = new ImageIcon(getClass().getResource("notepad/windows.png"));
        
        Image i2 = i1.getImage().getScaledInstance(400, 80, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(150, 40, 400, 80);
        add(l1);

        
        //ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("notepad/notepad.png"));
        ImageIcon i4 = new ImageIcon("notepad/windows.png");
        
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(50, 180, 70, 70);
        add(l2);

        JLabel l3 = new JLabel(
                "<html> <h1> Java Desktop application Notepad </h1> <h3> Made By Shubham Thorat</h3> </html>");
        l3.setBounds(150, 70, 500, 300);
        add(l3);

        b1 = new JButton("OK");
        b1.setBounds(580, 500, 80, 25);
        b1.addActionListener(this);
        add(b1);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        this.setVisible(false);
    }

    public static void main(String[] args) {
        About a1 = new About();
        a1.setVisible(true);
        a1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
