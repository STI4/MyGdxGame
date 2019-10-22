package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class BadPoint {

    private Texture score;
    private Vector3 pos;
    private Rectangle hBox;

    public int getWidth() {
        return score.getWidth();
    }

    public int getHeight() {
        return score.getHeight();
    }

    public BadPoint(int x, int y) {
        pos = new Vector3(x, y, 0);
        score = new Texture("badScore.png");
        hBox = new Rectangle(pos.x, pos.y, score.getWidth(), score.getHeight());
    }

    public Texture getScore() {
        return score;
    }

    public Vector3 getPos() {
        return pos;
    }

    public void updatePos () {
        Random rnd = new Random();
        pos.set(Constants.WIDTH / 2 - PlayState.img.getWidth()/2 + PlayState.sas.getWidth()/2 + 1 + rnd.nextInt((Constants.WIDTH/2 + PlayState.img.getWidth()/2 - score.getWidth() - PlayState.sas.getWidth()/2 - 1) - (Constants.WIDTH / 2 - PlayState.img.getWidth()/2 + PlayState.sas.getWidth()/2 + 1) + 1),Constants.HEIGHT / 2 - PlayState.img.getHeight()/2 + PlayState.sas.getHeight()/2 + 1 + rnd.nextInt((Constants.HEIGHT/2 + PlayState.img.getHeight()/2 - score.getHeight() - PlayState.sas.getHeight()/2 - 1) - (Constants.HEIGHT / 2 - PlayState.img.getHeight()/2 + PlayState.sas.getHeight()/2 + 1) + 1),0);
        hBox.setPosition(pos.x, pos.y);
    }

    public Rectangle getRect() {
        return hBox;
    }

    public void dispose(){
        score.dispose();
    }
}

