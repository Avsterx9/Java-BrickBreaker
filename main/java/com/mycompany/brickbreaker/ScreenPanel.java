package com.mycompany.brickbreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ScreenPanel extends JPanel implements KeyListener {

    public static final int WIDTH = 900, HEIGHT = 550;
    public static Color primaryColor = new Color(124, 32, 253);
    
    public static Font secondaryFont = new Font("Calibri", Font.PLAIN, 20);
    public static Font primaryFont = new Font("Calibri", Font.PLAIN, 30);

    private StatesManager sm;
    
    public ScreenPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        start();  
        
        setFocusable(true);
        setVisible(true); 
        addKeyListener(this);
    }

    public void start(){
        sm = new StatesManager();
        new Timer(0, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tick();
                    repaint();
                }
        }).start();
    }
    
    public void tick(){
         sm.tick();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.clearRect(0, 0, WIDTH, HEIGHT);
        sm.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        sm.KeyPressed(e.getKeyCode());
        e.consume();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        sm.KeyReleased(e.getKeyCode());
        e.consume();
    }
}