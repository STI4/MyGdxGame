package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Snake {
    private Vector3 position;
    private Vector2 velosity, velosityResume;
    public static Integer direction = 0, dir = 0;

    private Texture snake;
    private Rectangle snakeRect;

    public Snake(int x, int y){
        position = new Vector3(x, y, 0);
        velosity = new Vector2(0, 0);
        velosityResume = new Vector2(0, 25);
        snake = new Texture("snake.png");
        snakeRect = new Rectangle(position.x, position.y, snake.getWidth(), snake.getHeight());
    }

    public Vector3 getPosition() {
        return position;
    }
    public Vector2 getVelosity() {
        return velosity;
    }
    public Vector2 getVelosityResume() {
        return velosityResume;
    }

    public Texture getSnake() {
        return snake;
    }

    public int getWidth(){
        return snake.getWidth();
    }
    public int getHeight(){
        return snake.getHeight();
    }

    public void jump(){

        if (dir == 0)
            velosity.set(25, 25);

        if (dir == 1)
            velosity.set(25, -25);

        if (dir == 2)
            velosity.set(-25, -25);

        if (dir == 3)
            velosity.set(-25, 25);

        direction=0;

    }

    public void move(){

        if (direction == 0){
            if ((position.x + snake.getWidth() / 2 <= Constants.WIDTH / 2 - PlayState.img.getWidth() / 2) && ((position.y + snake.getHeight() / 2 >= Constants.HEIGHT / 2 - PlayState.img.getHeight() / 2 - 1) && (position.y + snake.getHeight() / 2 < Constants.HEIGHT / 2 + PlayState.img.getHeight() / 2)) && (velosity.x != 0) && (velosity.y != 25)) {
                velosity.set(0, 25);
                dir = 0;
            }
            else if ((position.y + snake.getHeight() / 2 >= Constants.HEIGHT / 2 + PlayState.img.getHeight() / 2) && ((position.x + snake.getWidth() / 2 >= Constants.WIDTH / 2 - PlayState.img.getWidth() / 2 - 1) && (position.x + snake.getWidth() / 2 < Constants.WIDTH / 2 + PlayState.img.getWidth() / 2))&& (velosity.x != 25) && (velosity.y != 0)){
                velosity.set(25, 0);
                dir = 1;
            }
            else if ((position.x + snake.getWidth() / 2 >= Constants.WIDTH / 2 + PlayState.img.getWidth() / 2) && ((position.y + snake.getHeight() / 2 <= Constants.HEIGHT / 2 + PlayState.img.getHeight() / 2 + 1) && (position.y + snake.getHeight() / 2 > Constants.HEIGHT / 2 - PlayState.img.getHeight() / 2)) && (velosity.x != 0) && (velosity.y != -25)){
                velosity.set(0, -25);
                dir = 2;
            }
            else if ((position.y + snake.getHeight() / 2 <= Constants.HEIGHT / 2 - PlayState.img.getHeight() / 2) && ((position.x + snake.getWidth() / 2 <= Constants.WIDTH / 2 + PlayState.img.getWidth() / 2 + 1) && (position.x + snake.getWidth() / 2 > Constants.WIDTH / 2 - PlayState.img.getWidth() / 2))&& (velosity.x != -25) && (velosity.y != 0)){
                velosity.set(-25, 0);
                dir = 3;
            }
        }

    }

    public void moveResume(){
        velosity.x = velosityResume.x; velosity.y = velosityResume.y;
    }

    public void setMoveResume(){
        velosityResume.x = velosity.x; velosityResume.y = velosity.y;
    }

    public void update(float dt){

        snakeRect.setPosition(position.x, position.y);
        velosity.scl(dt);
        position.add(0, velosity.y, 0);
        position.add(velosity.x, 0, 0);
        velosity.scl(1 / dt);
        if ((position.y + snake.getHeight() / 2 < Constants.HEIGHT / 2 - PlayState.img.getHeight() / 2 - 1))
            position.y = Constants.HEIGHT / 2 - PlayState.img.getHeight() / 2;

        if ((position.x + snake.getWidth() / 2 > Constants.WIDTH / 2 + PlayState.img.getWidth() / 2 + 1))
            position.x = Constants.WIDTH / 2 + PlayState.img.getWidth() / 2 - snake.getWidth()/2;

        if ((position.y + snake.getHeight() / 2 > Constants.HEIGHT / 2 + PlayState.img.getHeight() / 2 + 1))
            position.y = Constants.HEIGHT / 2 + PlayState.img.getHeight() / 2 - snake.getHeight()/2;

        if ((position.x + snake.getWidth() / 2 < Constants.WIDTH / 2 - PlayState.img.getWidth() / 2 - 1))
            position.x = Constants.WIDTH / 2 - PlayState.img.getWidth() / 2 + snake.getWidth()/2;
    }

    public boolean collides(Rectangle player){
        return player.overlaps(snakeRect);
    }

    public void updatePose (int x, int y) {
        position.set(x, y, 0);
    }

    public void setVelosity (int x, int y){
        velosity.set(x, y);
    }

    public void dispose(){
        snake.dispose();
    }

}
