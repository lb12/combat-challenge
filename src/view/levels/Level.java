package view.levels;


import model.characters.Character;
import model.characters.Ninja;
import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Rift Bandits Games
 */
public class Level extends BasicGameState{
    
    private boolean paused;

    private int LEFTMARGIN;
    private int RIGHTMARGIN;
    private int BOTTOMMARGIN;
    
    private StateBasedGame game;
    public static final int ID = 3;
    
    private TiledMap map = null;
    private int mapX;
    private int mapY;
    
    private int gravity  = 6;
    private int speed = 35;
    
    private Character player;
    private int playerX;
    private int playerY;
    private int playerAccel = 0;
    
    private ArrayList<Character> enemies = new ArrayList<>();
    
    private double scale;
    
    private int playerDirection = 1;
    
    private Animation run;
    private Animation idle;
    private Animation attack;
    private Animation jump;
    
    private boolean noMovement = true;
    private boolean jumping = false;
    private boolean attacking = false;
    
    private int jumps;
    private int possibleJumps;
    
    private Music naruto;
    private boolean musicON;
    
    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.game = sbg;
        
        naruto = new Music("res/sounds/naruto.wav");
        naruto.setVolume(1);
        
        scale=0.4;
        
        LEFTMARGIN = gc.getWidth()/3;
        RIGHTMARGIN = (2*gc.getWidth())/3;
        BOTTOMMARGIN = (5*gc.getHeight())/6;
        
        player = new Ninja(LEFTMARGIN,BOTTOMMARGIN,100);
        
        run = player.getRunAnimation();
        idle = player.getIdleAnimation();
        attack = player.getAttackAnimation();
        jump = player.getJumpAnimation();
        
        if(player.canDoubleJump()){
            possibleJumps=2;
        }else{
            possibleJumps=1;
        }
        
        map = new TiledMap("res/maps/Mapa.tmx","res/maps");
        
        
        mapX = 0; mapY = 0;
        playerX = player.getX(); playerY  = player.getY();
        
        
        enemies.add(new Ninja(3000,BOTTOMMARGIN,100));
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        
        renderGame(gc, sbg, grphcs);
        
        if(paused){
            grphcs.setColor(new Color(118, 123, 132, 150));
            grphcs.fillRect(0, 0, gc.getWidth(), gc.getHeight());
            
            renderMenu(gc, sbg, grphcs);
        }
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(!musicON){
            naruto.loop();
            musicON=true;
        }
            
        if(!paused){
            if(attack.getFrame()==9){
                attacking=false;
            }

            if (gc.getInput().isKeyDown(Input.KEY_D)) {
                player.moveX(20);
                if(!attacking){
                    playerDirection=1;
                }
                if(playerX>=RIGHTMARGIN){
                    if(mapX<=((map.getWidth()*map.getTileWidth())-gc.getWidth())*-1){
                        playerX=RIGHTMARGIN;
                        player.setY(RIGHTMARGIN);
                        noMovement=true;
                    }else{
                        mapX = mapX-(int)(speed*(float)768/1080);
                        run.update(i);
                        noMovement=false;
                    }
                }else{
                    playerX = playerX+speed;
                    run.update(i);
                    noMovement=false;
                }
            }
            else if (gc.getInput().isKeyDown(Input.KEY_A)) {
                player.moveX(-20);
                if(!attacking){
                    playerDirection=-1;
                }
                if(playerX<=LEFTMARGIN){
                    if(mapX>=0){
                        playerX=LEFTMARGIN;
                        player.setX(LEFTMARGIN);
                        noMovement=true;
                    }else{
                        mapX = mapX+(int)(speed*(float)768/1080);
                        run.update(i);
                        noMovement=false;
                    }
                }else{
                    playerX = playerX-speed;
                    run.update(i);
                    noMovement=false;
                }
            }else{
                noMovement=true;
            }

            if(gc.getInput().isKeyPressed(Input.KEY_SPACE)&&jumps>0){
                jumping = true;
                jumps--;
                playerAccel =40;
                jump.restart();
            }

            if(gc.getInput().isKeyDown(Input.KEY_RIGHT)&&attacking==false){
                playerDirection = 1;
                attacking=true;
                attack.restart();
            }else if(gc.getInput().isKeyDown(Input.KEY_LEFT)&&attacking==false){
                playerDirection = -1;
                attacking=true;
                attack.restart();
            }

            player.moveY(-playerAccel);
            playerY = playerY - playerAccel;

            if(player.getY()<BOTTOMMARGIN){
                playerAccel = playerAccel-gravity;
            }else{
                player.setY(BOTTOMMARGIN);
                playerY=player.getY();
                jumping=false;
                jumps = possibleJumps;
                playerAccel=0;
            }
        }
        
    }
    
    private void renderMenu(GameContainer gc, StateBasedGame sbg, Graphics grphcs){
        
    }
    
    private void renderGame(GameContainer gc, StateBasedGame sbg, Graphics grphcs){
        
        grphcs.scale((float) 1920/1024,(float)1080/768);
        
        map.render(mapX, mapY);
        
        grphcs.scale((float)1024/1920,(float)768/1080);
        
        if(attacking){
            if(playerDirection==1){
                attack.draw((float) (playerX-attack.getWidth()/2*scale), (float) (playerY-attack.getHeight()*scale), (float) (attack.getWidth()*scale), (float) (attack.getHeight()*scale));
            }else{
                attack.draw((float) (playerX+attack.getWidth()/2*scale), (float) (playerY-attack.getHeight()*scale), (float) ((attack.getWidth()*playerDirection)*scale), (float) (attack.getHeight()*scale));
            }
        }else if(jumping){
            if(playerDirection==1){
                jump.draw((float) (playerX-jump.getWidth()/2*scale), (float) (playerY-jump.getHeight()*scale), (float) (jump.getWidth()*scale), (float) (jump.getHeight()*scale));
            }else{
                jump.draw((float) (playerX+jump.getWidth()/2*scale), (float) (playerY-jump.getHeight()*scale), (float) ((jump.getWidth()*playerDirection)*scale), (float) (jump.getHeight()*scale));
            }
        }else if(noMovement){
            if(playerDirection==1){
                idle.draw((float) (playerX-idle.getWidth()/2*scale), (float) (playerY-idle.getHeight()*scale), (float) (idle.getWidth()*scale), (float) (idle.getHeight()*scale));
            }else{
                idle.draw((float) (playerX+idle.getWidth()/2*scale), (float) (playerY-idle.getHeight()*scale), (float) ((idle.getWidth()*playerDirection)*scale), (float) (idle.getHeight()*scale));
            }
        }else{
            if(playerDirection==1){
                run.draw((float) (playerX-run.getWidth()/2*scale), (float) (playerY-run.getHeight()*scale), (float) (run.getWidth()*scale), (float) (run.getHeight()*scale));
            }else{
                run.draw((float) (playerX+run.getWidth()/2*scale), (float) (playerY-run.getHeight()*scale), (float) ((run.getWidth()*playerDirection)*scale), (float) (run.getHeight()*scale));
            }
        }
        
        for(Character enemy : enemies){
            grphcs.drawString(Integer.toString(mapX) , 300, 200);
            grphcs.drawString(Integer.toString(enemy.getX()+mapX) , 300, 250);
            grphcs.drawString(Integer.toString(enemy.getX()) , 300, 300);
            if(enemy.getX()+mapX>0||enemy.getX()+mapX<gc.getWidth()){
                idle.draw((float) ((enemy.getX()+idle.getWidth()/2*scale))+mapX, (float) (enemy.getY()-idle.getHeight()*scale), (float) ((idle.getWidth()*-1)*scale), (float) (idle.getHeight()*scale)); 
            }
        }
        
    }
    
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            if(!paused){
                run.stop();
                idle.stop();
                jump.stop();
                attack.stop();
                paused = !paused;
            }else{
                run.start();
                idle.start();
                jump.start();
                attack.start();
                paused = !paused;
            }
        }
    }
}
