package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private GameStateManager gsm;
	public static Music bgMusic;
	
	@Override
	public void create () {
		Gdx.input.setCatchBackKey(true);
		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		bgMusic.setLooping(true);
		bgMusic.setVolume(0.3f);
		bgMusic.play();
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
