package GamDev.Sprites;

import GamDev.Screens.ScrGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class SprMain extends Sprite {

    private Texture imgStand[] = new Texture[3];
    private TextureAtlas imgWolv;
    private TextureRegion curTex, texFall;
    private Array<TextureRegion> texFrames = new Array<TextureRegion>();
    private Animation aniWalk, aniStand, aniAttack, aniJump;
    private Vector2 vVelocity;
    private TiledMapTileLayer collisionLayer;
    private float fSpeed = 60 * 2, fGravity = 60 * 1.8f, fTimer;
    private int nFrame = 0, nOffset = 0, nRange = 4;
    public World worlMain;
    public Body bodMain;
    public BodyDef bdMain;
    public FixtureDef fdMain;
    public FixtureDef fdFeet;
    private boolean isFlip, isAttack;

    public SprMain(ScrGame scrMain, float fX, float fY) {
        this.worlMain = scrMain.worlMain;
        fTimer = 0;
        vVelocity = new Vector2();
        setSize(20, 25);
        imgWolv = new TextureAtlas("Wolverine/Wolverine.pack");
        aniStand = getAnimation("Stand", 3, imgWolv, 12f);
        aniWalk = getAnimation("Walk", 6, imgWolv, 9f);
        aniJump = getAnimation("Jump", 3, imgWolv, 25f);
        aniAttack = getAnimation("Attack", 4, imgWolv, 12f);
        texFall = new TextureRegion(imgWolv.findRegion("Falling"));
        setX(fX);
        setY(fY);
        bdMain = new BodyDef();
        bdMain.position.set(getX(), getY());
        bdMain.type = BodyDef.BodyType.DynamicBody;
        bodMain = worlMain.createBody(bdMain);
        PolygonShape pShape = new PolygonShape();
        pShape.setAsBox(getWidth() / 2, getHeight() / 2, new Vector2(bodMain.getLocalCenter().x / 2, bodMain.getLocalCenter().y / 2 + 0.5f), 0);
        fdMain = new FixtureDef();
        fdMain.shape = pShape;
        fdMain.friction = 0f;
        bodMain.createFixture(fdMain);
        fdFeet = new FixtureDef();
        pShape.setAsBox(getWidth() / 2 - 2, 1, new Vector2(bodMain.getLocalCenter().x / 2, bodMain.getLocalCenter().y / 2 - (getHeight() / 2) + 1), 0);
        fdFeet.shape = pShape;
        fdFeet.friction = 10f;
        bodMain.createFixture(fdFeet);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

    public void update(float fDelta) {
        fTimer++;
        if (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            bodMain.applyLinearImpulse(new Vector2(0, 100f), bodMain.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            isAttack = true;
            fTimer = 0;
        }
        if (bodMain.getLinearVelocity().y == 0) {
            bodMain.getLinearVelocity().x = 0;
        }
        setX(bodMain.getPosition().x - getWidth() / 2);
        setY(bodMain.getPosition().y - getHeight() / 2);
        if (bodMain.getLinearVelocity().x > 0) {
            isFlip = false;
        } else if (bodMain.getLinearVelocity().x < 0) {
            isFlip = true;
        }
        if (bodMain.getLinearVelocity().y == 0) {
            setSize(20, 25);

            if (isAttack) {
                if(nRange < 9) {
                    nRange += 2;
                }
                setRegion(getFrame(aniAttack));
                setSize(getFrame(aniAttack).getRegionWidth() / 2.7f, getFrame(aniAttack).getRegionHeight() / 2.7f);
            } else {
                if (bodMain.getLinearVelocity().x != 0) {
                    setRegion(getFrame(aniWalk));
                } else {
                    setRegion(getFrame(aniStand));
                }
            }
        } else {
            if (bodMain.getLinearVelocity().y > 0) {
                System.out.println("f");
                setRegion(getFrame(aniJump));
                setSize(getFrame(aniJump).getRegionWidth() / 2.7f, getFrame(aniJump).getRegionHeight() / 2.7f);
            } else {
                setSize(20, 25);
                if (isFlip && !texFall.isFlipX()) {
                    texFall.flip(true, false);
                } else if (!isFlip && texFall.isFlipX()) {
                    texFall.flip(true, isFlip);
                }
                setRegion(texFall);
                setSize(texFall.getRegionWidth() / 2.7f, texFall.getRegionHeight() / 2.7f);
            }
        }
    }

    public TextureRegion getFrame(Animation aniTemp) {
        TextureRegion texOut;
        if(aniTemp == aniAttack) {
            texOut = aniTemp.getKeyFrame(fTimer, false);
            if(aniTemp.getKeyFrameIndex(fTimer) == 3) {
                isAttack = false;
                nRange = 4;
            }
        } else {
            texOut = aniTemp.getKeyFrame(fTimer, true);
        }
        if (isFlip && !texOut.isFlipX()) {
            texOut.flip(true, false);
        } else if (!isFlip && texOut.isFlipX()) {
            texOut.flip(true, false);
        }
        return texOut;
    }

    public Animation getAnimation(String sFile, int nLength, TextureAtlas texPack, float fSpeed) {
        Animation aniTemp;
        String FileName;
        Array<TextureRegion> arrFrames = new Array<TextureRegion>();
        for (int i = 0; i < nLength; i++) {
            FileName = sFile + i;
            arrFrames.add(texPack.findRegion(FileName));
        }
        aniTemp = new Animation(fSpeed, arrFrames);
        return aniTemp;
    }
}