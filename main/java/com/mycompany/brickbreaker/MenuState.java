package States;

import Helpers.RandomGenerator;
import Helpers.SoundManager;
import Models.Particle;
import com.mycompany.brickbreaker.GameState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import com.mycompany.brickbreaker.ScreenPanel;
import com.mycompany.brickbreaker.StatesManager;

public class MenuState extends GameState {
    
    private String[] options = {"Level 1","Level 2","Level 3"};
    private int selected = 0;
    private SoundManager soundManager = new SoundManager();
    
    public static int Level1Score = 0;
    public static int Level2Score = 0;
    public static int Level3Score = 0;
    
    private ArrayList<Particle> particles = new ArrayList<>();
    private int particleCount = 80;
    private RandomGenerator random = new RandomGenerator();

    public MenuState(StatesManager sm) {
        super(sm);
        initParticles();
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {
        for(Particle p : particles){
            p.tick();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ScreenPanel.WIDTH, ScreenPanel.HEIGHT);
        
        for(Particle p : particles){
            p.draw(g);
        }
        
        setDashboard(g);
        
        for(int i = 0;i < options.length; i++){
            if(i == selected){
                g.setColor(ScreenPanel.primaryColor);
            } else {
                g.setColor(Color.WHITE);
            }
            
            g.setFont(ScreenPanel.primaryFont);
            g.drawString(options[i], 100, 300 + i*50);
        }
        
    }

    @Override
    public void KeyPressed(int k) {
        soundManager.playBallHitSound();
        if(k == KeyEvent.VK_S){
            selected++;
            if(selected >= options.length){
                selected = 0;
            }
        }
        else if(k == KeyEvent.VK_W){
            selected--;
            if(selected < 0){
                selected = 2;
            }
        }
        
        if(k == KeyEvent.VK_ENTER){
            switch(selected){
                case 0: sm.states.push(new Statelv1(sm));
                    break;
                case 1: sm.states.push(new Statelv2(sm));
                    break;
                case 2: sm.states.push(new Statelv3(sm));
                    break;
            }
            soundManager.playSelectSound();
        }
    }

    @Override
    public void KeyReleased(int k) {
    }
    
    private void setDashboard(Graphics g){
        g.setFont(ScreenPanel.primaryFont);
        g.setColor(ScreenPanel.primaryColor);
        g.drawString("DASHBOARD", ScreenPanel.WIDTH - 300, 100);
        
        g.setFont(ScreenPanel.secondaryFont);
        g.drawString("Level 1: " + Level1Score + " / 21", ScreenPanel.WIDTH - 270, 140);
        g.drawString("Level 2: " + Level2Score + " / 20", ScreenPanel.WIDTH - 270, 170);
        g.drawString("Level 3: " + Level3Score + " / 9", ScreenPanel.WIDTH - 270, 200);
    }
    
    private void initParticles(){
       for(int i = 0;i < particleCount; i++){
           particles.add(new Particle(random.getRandomIntValue(1, ScreenPanel.WIDTH - 50),
                   random.getRandomIntValue(1, ScreenPanel.HEIGHT - 50),
                   random.getRandomIntValue(3, 15),
                   random.getRandomIntValue(30, 80)
           ));
       } 
    }
}