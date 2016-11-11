package GamDev.Misc;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class tbButtonMain extends TextButton {

    String sName;

    public tbButtonMain(String sName, TextButtonStyle tbsMain) {
        super(sName, tbsMain);
        this.sName = sName;
        this.setSize(100, 50);
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent evt, float x, float y) {
                System.out.println("Clicked " + x + y);
            }
        });
    }
}
