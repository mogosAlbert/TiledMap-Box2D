package GamDev;
import com.badlogic.gdx.Game;
import GamDev.Screens.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GamDev extends Game {
    public static float ppt = 16;
    public static SpriteBatch sbMain;
    @Override
    public void create() {
        sbMain = new SpriteBatch();
        setScreen(new ScrGame());
    }
    @Override
    public void dispose() {
        super.dispose();
    }
    @Override
    public void render() {
        super.render();
    }
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
    @Override
    public void pause() {
        super.pause();
    }
    @Override
    public void resume() {
        super.render();
    }    
}
