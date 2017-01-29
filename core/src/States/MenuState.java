package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lenwohl.dreammachine.DreamMachine;

/**
 * Created by Len Wohl on 1/28/2017.
 */

public class MenuState extends State{

    private Texture bg;
    private Texture kev;

    public MenuState(StateManager sm) {
        super(sm);
        bg = new Texture("bg1.png");
        kev = new Texture("kevin.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()) {
            sm.push(new PlayState(sm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
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
        bg.dispose();
        kev.dispose();
    }
}

