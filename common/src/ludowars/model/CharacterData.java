/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludowars.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Petter
 */
public class CharacterData extends EntityData {
    private float health;
    public float maxHealth;
    public float maxSpeed;
    
    public CharacterData() {
        this.maxHealth = 150;
        this.maxSpeed = 40;
        this.health = this.maxHealth;
    }

    public CharacterData(int maxHealth, int maxSpeed) {
        super();
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.health = this.maxHealth;
    }

    public CharacterData(Vector2 origin, int maxHealth, int maxSpeed) {
        super(origin);
        this.maxHealth = maxHealth;
        this.maxSpeed = maxSpeed;
        this.health = this.maxHealth;
    }

    public float getHealth() {
        return Math.min(health, maxHealth);
    }

    public void setHealth(float amount) {
        health = amount;
    }
    
    public void changeHealth(float amount) {
        health += amount;
    }

    public void die() {
        if (health <= 0) {
            System.out.println("Kill the motherfucker!");
        }
    }

    public int getCakeSlice() {
        float angle = super.angle;
        if (angle > 360 - 22.5 || angle <= 22.5) {
            return 4;
        }

        for (int i = 0; i < 7; i++) {
            double tmp = i * 45.0;
            if (angle > (22.5 + tmp) && angle <= (67.5 + tmp)) {
                return (i + 5) %8;
            }
        }

        return 0;
    }

    @Override
    public Rectangle getBounds() {
        Rectangle b = super.getBounds();
        //b.height -= 16;
        return b;
    }
}
