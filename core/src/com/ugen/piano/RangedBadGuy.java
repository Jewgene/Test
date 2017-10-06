package com.ugen.piano;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Eugene Munblit on 10/3/2017.
 */

public class RangedBadGuy extends BadGuy {
    private ArrayList<Particle> bullets;
    private Sprite bullet;

    private float width, height;
    private float fireRate = 0.5f;
    private long lastShot;
    private Iterator<Particle> particleIter;

    public RangedBadGuy(RangedBadGuy rbg){
        super(rbg);
        initialize();
    }

    public RangedBadGuy(Vector2 pos){
        super(pos);
        initialize();
    }

    public void initialize(){
        lastShot = System.currentTimeMillis();
        bullet = new Sprite(new Texture("particle.png"));
        bullet.setColor(new Color(1, 0, 0, 1));
        bullets = new ArrayList<Particle>();
    }

    @Override
    public void draw(ShapeRenderer renderer){
        renderer.setColor(Color.PURPLE);
        renderer.rect(position.x, position.y, size.x, size.y);
    }

    public void update(Vector2 newTarget, SpriteBatch batch){
        /*if(System.currentTimeMillis() - lastShot > 1000/fireRate)
            shoot(newTarget);

        particleIter = bullets.iterator();

        while(particleIter.hasNext()){
            Particle p = particleIter.next();

            p.update();
            p.draw(batch);
            if(p.getX() < 0 || p.getX() > width || p.getY() < 0 || p.getY() > height) {
                particleIter.remove();
            }
        }*/
    }

    public void shoot(Vector2 target, Particle bullet){
        double mag = Math.sqrt((target.x - position.x)*(target.x - position.x)
                + (target.y - position.y)*(target.y - position.y));

        float velocityX = 5 * (float)((target.x - position.x) / mag);
        float velocityY = 5 * (float)((target.y - position.y) / mag);

        float theta = (float)Math.atan(velocityY/ velocityX);


        bullet.setPosition(position.x, position.y);
        bullet.rotate(theta * 180 / (float)Math.PI);
        bullet.setFaction("bad");
        bullet.setColor(Color.RED);
        Gdx.app.log("DEBUG", ""+bullet.getColor());
        bullet.setVelocity(new Vector2(velocityX, velocityY));

        bullet.setAcceleration(new Vector2(0.0f, 0.0f));

        lastShot = System.currentTimeMillis();
    }

    public ArrayList<Particle> getBullets(){
        return bullets;
    }

    public long getLastShot(){
        return lastShot;
    }

    public float getFireRate(){
        return fireRate;
    }
}