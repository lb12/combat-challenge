package view.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;

/**
 *
 * @author Rift Bandits Games
 */
public class Opening extends BasicGameState{
    
    private StateBasedGame game;
    public static final int ID = 0;
    
    private UnicodeFont font;
    private int alpha;
    private boolean fadeOut = true;
    private int time=0;
    
    private Image bg;
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game=sbg;
        
        this.font = new UnicodeFont("res/wolfsbane2iiacad.ttf", 144, false, false);
        this.font.addAsciiGlyphs();
        this.font.addGlyphs(400, 600);
        this.font.getEffects().add(new ColorEffect());
        this.font.loadGlyphs();
        this.alpha = 255;
        
        this.bg = new Image("res/backgrounds/BG.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        float screenWidth = (float)gc.getWidth(); //1280
        float screenHeight = (float)gc.getHeight(); //720
        
        grphcs.drawImage(bg,0,0,screenWidth,screenHeight,0,0,bg.getWidth(),bg.getHeight());
              
        Color c = new Color(0,0,0,alpha);
        font.drawString(250, 500, "PRESIONE UNA TECLA",c);
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(time>50){
            if(fadeOut){
                if(alpha>15) alpha=alpha-15;
                else{
                    fadeOut=false;
                    alpha=0;
                }
            }
            else{
                if(alpha<240) alpha=alpha+15;
                else{
                    fadeOut=true;
                    alpha=255;
                }
            }
            time=0;
        }
        time=time+i;
        
    }

    public void keyReleased(int key, char c) {
        game.enterState(StartMenu.ID,new EmptyTransition() ,new EmptyTransition() );
    }
}