package com.mycompany.brickbreaker;

import States.MenuState;
import java.awt.Graphics;
import java.util.Stack;

public class StatesManager {
    
    public Stack <GameState> states;
    
    public StatesManager(){
        states = new Stack<GameState>();
        states.push(new MenuState(this));
    }
    public void tick(){
        states.peek().tick();
    }
    public void draw(Graphics g){
        states.peek().draw(g);
    }
    public void KeyPressed(int k){
        states.peek().KeyPressed(k);
    }
    public void KeyReleased(int k){
        states.peek().KeyReleased(k);
    }
}
    