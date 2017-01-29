package com.lenwohl.dreammachine.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Len Wohl on 1/28/2017.
 */

public class StateManager {

    private Stack<State> states;

    public StateManager(){
        states = new Stack<State>();
    }

    public void push(State s){
        states.push(s);
    }
    public void pop(){
        states.pop();
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
