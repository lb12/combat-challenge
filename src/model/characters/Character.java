package model.characters;

import org.newdawn.slick.Animation;

/**
 *
 * @author Rift Bandits Games
 */
public abstract class Character {

    private int x;
    private int y;
    
    private int hp;
    
    private boolean doublejump;
    
    public Character(int x, int y, int hp, boolean doublejump){
        this.x=x;
        this.y=y;
        this.hp=hp;
        this.doublejump = doublejump;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getHP(){
        return hp;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void takeDamage(int hp){
        this.hp=this.hp-hp;
    }
    
    public void moveX(int x){
        this.x = this.x + x;
    }
    
    public void moveY(int y){
        this.y = this.y + y;
    }
    
    public abstract Animation getRunAnimation();
    
    public abstract Animation getIdleAnimation();
    
    public abstract Animation getAttackAnimation();
    
    public abstract Animation getJumpAnimation();
    
    public boolean canDoubleJump(){
        return doublejump;
    }
    
}