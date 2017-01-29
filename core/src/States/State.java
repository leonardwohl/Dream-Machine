package States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Len Wohl on 1/28/2017.
 */

public abstract class State {

    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected StateManager sm;

    protected State(StateManager sm) {
        this.sm = sm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
