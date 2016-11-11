package GamDev.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ScrMainMenu implements Screen {
    
    private Stage stgMain;
    private Table tblMain;
    private Button btnStart, btnLevels;
    private Label lblStart, lblLevels;
    private ButtonStyle bsMain;
    private Viewport vpMain;
    private OrthographicCamera ocMain;
    
    @Override
    public void show() {
        ocMain = new OrthographicCamera();
        vpMain = new FitViewport(1000, 700, ocMain);
        stgMain = new Stage(vpMain, GamDev.GamDev.sbMain);
        tblMain = new Table();
        bsMain = new ButtonStyle();
        btnStart = new Button();
        lblStart = new Label("Start", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lblLevels = new Label("Levels", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        btnStart = new Button(lblStart, new Button.ButtonStyle());
        btnLevels = new Button(lblLevels, new Button.ButtonStyle());
        tblMain.bottom();
        tblMain.add(lblStart).expandX();
        tblMain.top();
        tblMain.add(lblLevels).expandX().padBottom(20);
        stgMain.addActor(tblMain);
    }

    @Override
    public void render(float f) {
        stgMain.draw();
    }

    @Override
    public void resize(int width, int height) {
        ocMain.viewportHeight = height / 2;
        ocMain.viewportWidth = width / 2;
        ocMain.translate(width * -0.25f , height);
        ocMain.update();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stgMain.dispose();
    }
    
}
