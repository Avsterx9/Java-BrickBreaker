package Models;

import Helpers.RandomGenerator;
import com.mycompany.brickbreaker.ScreenPanel;
import java.awt.Color;
import java.awt.Graphics;

public class Particle {
    
    public int xDirection;
    public int yDirection;
    public int x;
    public int y;
    public int particleSize;
    public float speed;
    public int opacity;
    private RandomGenerator random = new RandomGenerator();

    public Particle(int x, int y, int particleSize, int opacity) {
        this.x = x;
        this.y = y;
        this.particleSize = particleSize;
        this.opacity = opacity;
        
        setParticlePlayDirection();
    }
    
    public void tick(){
        x += xDirection;
        y += yDirection;

        if(x < 0 || (x > ScreenPanel.WIDTH - particleSize)){
            xDirection = -xDirection;
        }
        
        if(y < 0 || (y > ScreenPanel.HEIGHT - particleSize)){
            yDirection = -yDirection;      
        }
    }
    
    public void draw(Graphics g){
        Color myColour = new Color(255, 255, 255, opacity);
        g.setColor(myColour);
        g.fillOval(x,y,particleSize,particleSize);
    }

    private void setParticlePlayDirection() {
        this.xDirection = random.getRandomIntValue(-5, 5);
        this.yDirection = random.getRandomIntValue(-5, 5);
    }
}
