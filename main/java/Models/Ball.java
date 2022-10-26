package Models;

import Helpers.RandomGenerator;
import Helpers.SoundManager;
import com.mycompany.brickbreaker.ScreenPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Ball {

    public int xDirection;
    public int yDirection = -6;
    public int x;
    public int y;
    public int ballSize;
    
    private SoundManager soundManager = new SoundManager();
    private RandomGenerator random = new RandomGenerator();
    
    public Ball(int x, int y, int ballSize, float speed){
        this.x = x;
        this.y = y;
        this.ballSize = ballSize;
        
        setBallPlayDirection();
    }
    
    public void tick(){
        x += xDirection;
        y += yDirection;

        if(x < 0){
            xDirection = -xDirection;
            soundManager.playBallHitSound();
        }
        if(y < 0){
            yDirection = -yDirection;
            soundManager.playBallHitSound();
        }
        if(x > ScreenPanel.WIDTH - ballSize){
            xDirection = -xDirection;
            soundManager.playBallHitSound();
        }
    }
    
    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(x,y,20,20);
    }

    private void setBallPlayDirection() {
        this.xDirection = random.getRandomIntValue(-3, 3);
    }
}
