package model.characters;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Rift Bandits Games
 */
public class Ninja extends Character{

    private Animation run;
    private Animation idle;
    private Animation attack;
    private Animation jump;
    
    
    public Ninja(int x, int y, int hp) throws SlickException {
        super(x, y, hp, true);
        
        
        SpriteSheet runSprites = new SpriteSheet("res/characters/ninja/ninjaRun2.png", 363, 444);
        run = new Animation(false);
        for (int i=0;i<10;i++) {
                run.addFrame(runSprites.getSprite(i,0), 100);
        }
        
        SpriteSheet idleSprites = new SpriteSheet("res/characters/ninja/ninjaIdle.png", 232, 439);
        idle = new Animation();
        for (int i=0;i<10;i++) {
                idle.addFrame(idleSprites.getSprite(i,0), 100);
        }
        
        SpriteSheet attackSprites = new SpriteSheet("res/characters/ninja/ninjaAttack2.png", 536, 453);
        attack = new Animation();
        for (int i=0;i<10;i++) {
                attack.addFrame(attackSprites.getSprite(i,0), 50);
        }
        attack.stopAt(9);
        
        SpriteSheet jumpSprites = new SpriteSheet("res/characters/ninja/ninjaJump.png", 362, 483);
        jump = new Animation();
        for (int i=0;i<10;i++) {
                jump.addFrame(jumpSprites.getSprite(i,0), 100);
        }
        jump.stopAt(9);
    }
    
    public Animation getRunAnimation(){
        return run;
    }
    
    public Animation getIdleAnimation(){
        return idle;
    }
    
    public Animation getAttackAnimation(){
        return attack;
    }
    
    public Animation getJumpAnimation(){
        return jump;
    }

}