package States;

import Helpers.SoundManager;
import Models.Ball;
import Models.Brick;
import Models.Player;
import com.mycompany.brickbreaker.GameState;
import com.mycompany.brickbreaker.ScreenPanel;
import com.mycompany.brickbreaker.StatesManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Statelv1 extends GameState {
    private boolean isRunning = false;

    private int brickCols = 3;
    private int brickRows = 7;

    private int platformX = ScreenPanel.HEIGHT / 2;
    private int platformY = ScreenPanel.WIDTH / 2 + 50;

    private Player player;
    private Ball ball;
    private int brickWidth = 80;
    private int brickHeight = 20;
    private ArrayList<Brick> bricks = new ArrayList<Brick>();

    private int score = 0;

    private SoundManager soundManager = new SoundManager();

    public Statelv1(StatesManager sm) {
        super(sm);
        isRunning = true;
        initGame();
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {
        if (isRunning) {
            Rectangle ballRect = new Rectangle(ball.x, ball.y, ball.ballSize, ball.ballSize);

            try {
                for (Brick b : bricks) {
                    if (ballRect.intersects(new Rectangle(b.x, b.y, brickWidth, brickHeight))) {
                        ball.yDirection = -ball.yDirection;
                        bricks.remove(b);
                        
                        score++;
                        soundManager.playScoreSound();
                    }
                }

                if (ballRect.intersects(new Rectangle(player.x,
                    player.y, 150, 20))) {
                    ball.yDirection = -ball.yDirection;
                    soundManager.playBallHitSound();
                }

                if (ball.y > ScreenPanel.HEIGHT) {
                    isRunning = false;
                    soundManager.playGameOverSound();
                }
                
                if(score == brickCols * brickRows){
                    isRunning = false;
                }
        
                player.tick();
                ball.tick();
                
            } catch (Exception e) {}
        }
    }

    @Override
    public void draw(Graphics g) {
        g.clearRect(0, 0, ScreenPanel.WIDTH, ScreenPanel.HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ScreenPanel.WIDTH, ScreenPanel.HEIGHT);

        for (int i = 0; i < bricks.size(); i++) {
            bricks.get(i).draw(g);
        }
        
        ball.draw(g);
        player.draw(g);
        
        if(score == brickCols * brickRows){
            displayMessage(g, "You won!");
            setDashboardScore();
        }
        
        if(ball.y > ScreenPanel.HEIGHT){
            displayMessage(g, "Game Over");
            setDashboardScore();
        }

        g.setFont(ScreenPanel.secondaryFont);
        g.setColor(ScreenPanel.primaryColor);
        String strScore = String.valueOf(score);
        g.drawString("Points: " + strScore, 20, ScreenPanel.HEIGHT - 10);
    }

    @Override
    public void KeyPressed(int k) {
        if(isRunning == true){
            player.keyPressed(k);
        }
        if(isRunning == false){
            sm.states.pop();
        }
    }

    @Override
    public void KeyReleased(int k) {
        player.keyReleased(k);
    }

    private void initGame() {
        bricks = new ArrayList<Brick>();
        player = new Player(platformX, platformY, 150, 4);
        ball = new Ball(ScreenPanel.HEIGHT / 2 + 120, ScreenPanel.WIDTH / 2 - 200, 25, 1f);

        initBricks();
        score = 0;
    }

    private void initBricks() {
        for (int i = 0; i < brickRows; i++) {
            for (int j = 0; j < brickCols; j++) {
                bricks.add(new Brick(i * 100 + 110, j * 50 + 20, brickWidth, brickHeight));
            }
        }
    }

    public void displayMessage(Graphics g, String msg){
        g.setFont(ScreenPanel.primaryFont);
        g.setColor(ScreenPanel.primaryColor);
        int stringDisplayWidth = g.getFontMetrics().stringWidth(msg);
        g.drawString(msg, ScreenPanel.WIDTH/2 - stringDisplayWidth/2, ScreenPanel.HEIGHT/2);
    }
    
    private void setDashboardScore(){
        if(score > MenuState.Level1Score){
            MenuState.Level1Score = score;
        }
    }
}
