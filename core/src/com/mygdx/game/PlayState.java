package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.Rectangle;
import java.util.Iterator;

public class PlayState extends State {

    public static Snake sas;
    public static Texture img;
    Texture bg;
    Point point;
    Array<Rectangle> asteroids;
    BadPoint badPoint;
    Sound scoreSound, badSound;
    public static int score, lastScore, life;
    BitmapFont font;
    boolean pause;
    long lastSpawnTime;
    String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"ยด`'<>";

    public PlayState(GameStateManager gsm) {
        super(gsm);
        Snake.direction = 0;
        score = 0;
        life = 2;
        lastScore = 0;
        lastSpawnTime = TimeUtils.nanoTime();
        pause = false;

        Gdx.input.setCatchBackKey(true);
        img = new Texture("figure.png");
        sas = new Snake(Constants.WIDTH/2, Constants.HEIGHT/2 + 1);
        sas.updatePose(Constants.WIDTH/2 - img.getWidth()/2 - sas.getWidth()/2, (int)sas.getPosition().y);
        scoreSound = Gdx.audio.newSound(Gdx.files.internal("trueScore.wav"));
        badSound = Gdx.audio.newSound(Gdx.files.internal("badSound.wav"));
        bg = new Texture("bg.png");
        point = new Point(0, 0);
        badPoint = new BadPoint(0,0);
        point.updatePos();
        badPoint.updatePos();
        font = new BitmapFont();
        font.setColor(1f, 0f, 0f, 1f);
        camera.setToOrtho(false, Constants.WIDTH, Constants.HEIGHT);
        sas.setVelosity(0, 25);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Snake.direction = 1;
            sas.jump();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            pause = !pause;
        }
    }

    @Override
    public void update(float dt) {
        sas.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        Iterator<Rectangle> iter = asteroids.iterator();
        handleInput();
        sb.begin();
        sb.draw(bg, 0, 0, Constants.WIDTH, Constants.HEIGHT);

            font.draw(sb, "SCORE: " + score, 0, Constants.HEIGHT - 100);
            font.draw(sb, "LIFE: " + (life), 0, Constants.HEIGHT - 150);
            if (pause == false) {
                sas.move();
                if (sas.getVelosity().x == 0 && sas.getVelosity().y == 0) {
                    sas.moveResume();
                    MyGdxGame.bgMusic.play();
                }
            }
            else {
                if (sas.getVelosity().x != 0 || sas.getVelosity().y != 0)
                    sas.setMoveResume();
                MyGdxGame.bgMusic.pause();
                font.draw(sb, "PAUSE", Constants.WIDTH / 2, Constants.HEIGHT / 2);
                sas.setVelosity(0, 0);
            }
            update(Gdx.graphics.getDeltaTime());
            sb.draw(img, Constants.WIDTH / 2 - img.getWidth() / 2, Constants.HEIGHT / 2 - img.getHeight() / 2);
            sb.draw(point.getScore(), point.getPos().x, point.getPos().y);
            sb.draw(badPoint.getScore(), badPoint.getPos().x, badPoint.getPos().y);
            sb.draw(sas.getSnake(), sas.getPosition().x, sas.getPosition().y);

        if (sas.collides(point.getRect())) {
            score++;
            lastScore++;
            scoreSound.play();
            point.updatePos();
            if (lastScore % 10 == 0)
                life++;
        }

        if (sas.collides(badPoint.getRect())) {
            badSound.play();
            life--;
             // TODO сделать так. чтобы камни спавнились методом, который добавляет экземпляр класса в очередь. В случае если игрок задевает камень - удалить камень, через 5 сек заспавнить новый
            iter.remove();
        }

        if (life <= 0)
            gsm.set(new MenuState(gsm));

        if (pause == false) {
            sas.move();
            if (sas.getVelosity().x == 0 && sas.getVelosity().y == 0) {
                sas.moveResume();
                MyGdxGame.bgMusic.play();
            }
        }
        else {
            if (sas.getVelosity().x != 0 || sas.getVelosity().y != 0)
                sas.setMoveResume();
            MyGdxGame.bgMusic.pause();
            font.draw(sb, "PAUSE", Constants.WIDTH / 2, Constants.HEIGHT / 2); // TODO вместо этой хрени лучше ссделать красивую картинку, да еще и с анимацией
            sas.setVelosity(0, 0);
        }


        sb.end();

    }

    @Override
    public void dispose() {
        img.dispose();
        badSound.dispose();
        scoreSound.dispose();
        bg.dispose();
        point.dispose();
        badPoint.dispose();
        sas.dispose();
    }
}