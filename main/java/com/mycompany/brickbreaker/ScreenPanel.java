package com.mycompany.brickbreaker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class ScreenPanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 900, HEIGHT = 550;
    public static Color primaryColor = new Color(124, 32, 253);
    
    public static Font secondaryFont = new Font("Calibri", Font.PLAIN, 20);
    public static Font primaryFont = new Font("Calibri", Font.PLAIN, 30);
    
    
    private boolean isRunning = false;
    
    private Thread t;
    
    private int FPS = 60;
    private int targetTime = 1000/FPS;
    
    private StatesManager sm;
    
    public ScreenPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        addKeyListener(this);
        setFocusable(true);
        start();
    }

    public void start(){
        isRunning = true;
        t = new Thread(this);
        t.start();
    }
    
    public void tick(){
         sm.tick();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.clearRect(0, 0, WIDTH, HEIGHT);
        sm.draw(g);
    }
    
    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;
        
        sm = new StatesManager();
        
        while(isRunning){
            start = System.nanoTime();
            
            tick();
            repaint();
            
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;
            
            if(wait <= 5){
                wait = 5;
            }
            
            try{
                Thread.sleep(wait);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }        
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        sm.KeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        sm.KeyReleased(e.getKeyCode());
    }
}