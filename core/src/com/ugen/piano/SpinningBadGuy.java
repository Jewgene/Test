package com.ugen.piano;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Eugene Munblit on 10/16/2017.
 */

public class SpinningBadGuy extends BadGuy {

    private double theta, omega, alpha;
    private float velMag, length;
    private float[] vertices;

    public SpinningBadGuy(BadGuy bg){
        super(bg);
        theta = 0;
        omega = Math.PI/360;
        alpha = Math.PI/2160;
        velMag = 5;
        vertices = new float[6];
        length = 50;
    }

    public SpinningBadGuy(Vector2 pos){
        super(pos);
        theta = 0;
        omega = Math.PI/360;
        alpha = Math.PI/2160;
        velMag = 5;
        vertices = new float[6];
        length = 50;
    }

    @Override
    public void update(Vector2 newTarget){
        double mag = Math.sqrt((newTarget.x - position.x)*(newTarget.x - position.x) + (newTarget.y - position.y)*(newTarget.y - position.y));
        velocity.x = velMag * (float)((newTarget.x - position.x) / mag);
        velocity.y = velMag * (float)((newTarget.y - position.y) / mag);

        velMag += .05;
        omega += alpha;
        theta += omega;

        hitbox.setPosition(position);
        position.add(velocity);
    }

    @Override
    public void draw(ShapeRenderer renderer){
        for(int i = 0; i < 6; i+=2){
            vertices[i] = (float)Math.cos(i*Math.PI/3 + theta)*length + position.x;
            vertices[i+1] = (float)Math.sin(i*Math.PI/3 + theta)*length + position.y;
        }

        renderer.setColor(Color.YELLOW);
        renderer.triangle(vertices[0], vertices[1], vertices[2],
                vertices[3], vertices[4], vertices[5]);
    }

    public void rotate(float theta){
        this.theta = theta;
    }

    @Override
    public void reset(){
        omega = Math.PI/360;
        theta = 0;
        velMag = 5;
    }
}
