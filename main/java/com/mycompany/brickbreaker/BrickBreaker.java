package com.mycompany.brickbreaker;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class BrickBreaker {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new ScreenPanel(), BorderLayout.CENTER);
        frame.setContentPane(new ScreenPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Brick Breaker | Kamil Chrobok IO2");
    }
}
