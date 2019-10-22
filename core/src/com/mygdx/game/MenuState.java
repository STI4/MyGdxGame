package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State {

    Texture background;
    Texture title;
    BitmapFont efont;
    String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"ยด`'<>";


    public MenuState(GameStateManager gsm) {
        super(gsm);
        title = new Texture("title.png");
        background = new Texture("bg.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Snake.direction = 0;
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {

        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Constants.WIDTH, Constants.HEIGHT);
        sb.draw(title, 50, Constants.HEIGHT / 2);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        title.dispose();
    }
}