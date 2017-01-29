package com.lenwohl.dreammachine.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lenwohl.dreammachine.DreamMachine;

/**
 * Created by Len Wohl on 1/28/2017.
 */

public class PlayState extends State {

    private Texture kev;
    private Texture bg;

    protected PlayState(StateManager sm) {
        super(sm);
        kev = new Texture("kevin.png");
        bg = new Texture("bg2.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, DreamMachine.WIDTH, DreamMachine.HEIGHT);
        sb.draw(kev, (DreamMachine.WIDTH/2)-kev.getWidth()/2, (DreamMachine.HEIGHT/2)-kev.getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}

