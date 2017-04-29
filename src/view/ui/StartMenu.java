package view.ui;

import model.model.Screen;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
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
public class StartMenu extends BasicGameState {

    private StateBasedGame game;
    public static final int ID = 1;

    private Screen screen;

    private int selected = 0;

    private UnicodeFont font;

    private Image bg;
    private Image frame;
    private Image button;
    private Image buttonSelected;

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        this.game = sbg;

        this.screen = new Screen(gc);

        this.font = new UnicodeFont("res/wolfsbane2iiacad.ttf", 72, false, false);
        this.font.addAsciiGlyphs();
        this.font.addGlyphs(400, 600);
        this.font.getEffects().add(new ColorEffect());
        this.font.loadGlyphs();

        this.bg = new Image("res/backgrounds/BG.png");
        this.frame = new Image("res/menu/SmallFrame.png");
        this.button = new Image("res/menu/Button.png");
        this.buttonSelected = new Image("res/menu/ButtonSelected.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //Draw background
        g.drawImage(bg, 0, 0, screen.getScreenWidth(), screen.getScreenHeight(), 0, 0, bg.getWidth(), bg.getHeight());

        //Draw frame
        g.drawImage(frame, screen.getSecureZoneStart(), 0, screen.getSecureZoneEnd(), screen.getSecureZoneHeigth(), 0, 0, frame.getWidth(), frame.getHeight());

        //Draw buttons
        if (selected == 0) { //Play Button -> Selected            
            drawPlayButton(g, buttonSelected, Color.gray);
        } else { //Play Button -> Not Selected
            drawPlayButton(g, button, Color.black);
        }

        if (selected == 1) { //Options Button -> Selected
            drawOptionsButton(g, buttonSelected, Color.gray);
        } else { //Options Button -> Not Selected
            drawOptionsButton(g, button, Color.black);
        }

        if (selected == 2) { //Exit Button -> Selected
            drawExitButton(g, buttonSelected, Color.gray);
        } else {  //Exit Button ->  Not Selected
            drawExitButton(g, button, Color.black);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }

    public void keyReleased(int key, char c) {
        if (key == Input.KEY_DOWN) {
            if (selected == 2) {
                selected = 0;
            } else {
                selected++;
            }
        }
        if (key == Input.KEY_UP) {
            if (selected == 0) {
                selected = 2;
            } else {
                selected--;
            }
        }
        if (key == Input.KEY_ENTER || key == Input.KEY_SPACE) {
            if (selected == 0) {
                game.enterState(SelectMenu.ID, new EmptyTransition(), new EmptyTransition());
            }
            if (selected == 2) {
                System.exit(0);
            }
        }
    }

    /**
     * Metodo que dibuja los botones
     */
    private void drawPlayButton(Graphics g, Image button, Color color) {
        g.drawImage(button, getButtonWidth(button), getButtonHeight(button));
        font.drawString(getButtonWidth(button) + button.getWidth() / 3.5f, getButtonHeight(button) + button.getHeight() / 3.5f, "JUGAR", color);
    }

    private void drawOptionsButton(Graphics g, Image button, Color color) {
        g.drawImage(button, getButtonWidth(button), getButtonHeight(button) + screen.getSecureZoneStart());
        font.drawString(getButtonWidth(button) + button.getWidth() / 4, getButtonHeight(button) + button.getHeight() / 3.5f + screen.getSecureZoneStart(), "OPCIONES", color);
    }

    private void drawExitButton(Graphics g, Image button, Color color) {
        g.drawImage(button, getButtonWidth(button), getButtonHeight(button) + 2 * screen.getSecureZoneStart());
        font.drawString(getButtonWidth(button) + button.getWidth() / 3, getButtonHeight(button) + button.getHeight() / 3.5f + 2 * screen.getSecureZoneStart(), "SALIR", color);
    }

    private float getButtonWidth(Image button) {
        return (screen.getScreenWidth() / 2 - button.getWidth() + screen.getSecureZoneStart());
    }

    private float getButtonHeight(Image button) {
        return (screen.getScreenHeight() / 2 - (button.getHeight() + button.getHeight() / 2));
    }
}
