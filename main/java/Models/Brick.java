package Models;

import com.mycompany.brickbreaker.ScreenPanel;
import java.awt.Graphics;

public class Brick {
    
    public int x;
    public int y;
    public int brickWidth; 
    public int brickHeight;
    public int xDirection = 3;
    public float speed = 1f;
    
    public Brick(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.brickWidth = width;
        this.brickHeight = height;
    }
    
    public void tick(){
        moveBrick();  
    }
    
    public void moveBrick(){
        x += xDirection * speed;
        if(x < 0){
            xDirection = -xDirection;
        }
        
        if(x > ScreenPanel.WIDTH - brickWidth){
            xDirection = -xDirection;
        }
    }
    
    public void draw(Graphics g){
        g.setColor(ScreenPanel.primaryColor);
        g.fillRect(x, y, brickWidth, brickHeight);
    }
}