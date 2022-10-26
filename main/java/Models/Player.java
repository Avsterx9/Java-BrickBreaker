package Models;

import com.mycompany.brickbreaker.ScreenPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player{

    private boolean right = false;
    private boolean left = false;
    public int x;
    public int y;
    private int playerSpeed;
    private int platformSize;
    
    
    public Player(int x, int y, int platformSize, int playerSpeed){
        this.x = x;
        this.y = y;
        this.platformSize = platformSize;
        this.playerSpeed = playerSpeed;
    }
    
    public void tick(){
        if(right){
            x += playerSpeed;
        }
        if(left){
            x -= playerSpeed;
        }
        if(x > ScreenPanel.WIDTH - platformSize){
            x = ScreenPanel.WIDTH - platformSize;
        }
        
        if(x < 0){
            x = 0;
        }
    }
    
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 150, 15);
    }
    
    public void keyPressed(int k){
        if(k == KeyEvent.VK_D){
            right = true;
            left = false;
        }
        if(k == KeyEvent.VK_A){
            left = true;
            right = false;
        }
    }
    
    public void keyReleased(int k){
        if(k == KeyEvent.VK_D) right = false;
        if(k == KeyEvent.VK_A) left = false;
    }
}
