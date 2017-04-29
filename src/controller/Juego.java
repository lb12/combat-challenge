package controller;

import view.levels.Level;
import view.ui.SelectMenu;
import view.ui.StartMenu;
import view.ui.Opening;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Rift Bandits Games
 */
public class Juego extends StateBasedGame{  
    
    public Juego(){
        super("Juego");
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new Opening());
        addState(new StartMenu());
        addState(new SelectMenu());
        addState(new Level());
    }
    
    /**
	 * Entry point to our test
	 * 
	 * @param argv The arguments to pass into the test
	 */
	public static void main(String[] argv) {
		try {                        
			AppGameContainer container = new AppGameContainer(new Juego());
			container.setDisplayMode(1280,720,false);
                        container.setMinimumLogicUpdateInterval(50);
                        //container.setVSync(true);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}	
}
