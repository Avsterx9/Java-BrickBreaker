package com.mycompany.brickbreaker;

import java.awt.Graphics;

public abstract class GameState {
    
    protected StatesManager sm;
    
    public GameState(StatesManager sm){
        this.sm = sm;
    }
    
    public abstract void init();
    public abstract void tick();
    public abstract void draw(Graphics g);
    public abstract void KeyPressed(int k);
    public abstract void KeyReleased(int k);
}

