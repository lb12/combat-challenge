package view.ui;

import model.model.Screen;
import view.levels.Level;
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
public class SelectMenu extends BasicGameState {

    private StateBasedGame game;
    public static final int ID = 2;

    private Screen screen;

    private int selected = 0;

    private boolean[] unlocked = {true, false, true, true, true};

    private UnicodeFont font;
    private UnicodeFont titleFont;

    private Image bg;
    private Image frame;
    private Image button;
    private Image buttonSelected;
    private Image buttonLocked;
    private Image buttonLockedSelected;
    private Image homeButton;
    private Image homeButtonSelected;
    private Image shopButton;
    private Image shopButtonSelected;

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

        this.titleFont = new UnicodeFont("res/wolfsbane2iiengrave.ttf", 90, true, false);
        this.titleFont.addAsciiGlyphs();
        this.titleFont.addGlyphs(400, 600);
        this.titleFont.getEffects().add(new ColorEffect());
        this.titleFont.loadGlyphs();

        this.bg = new Image("res/backgrounds/BG.png");
        this.frame = new Image("res/menu/BigFrame.png");
        this.button = new Image("res/menu/SmallButton.png");
        this.buttonSelected = new Image("res/menu/SmallButtonSelected.png");
        this.buttonLocked = new Image("res/menu/SmallButtonLocked.png");
        this.buttonLockedSelected = new Image("res/menu/SmallButtonLockedSelected.png");
        this.homeButton = new Image("res/menu/HomeButton.png");
        this.homeButtonSelected = new Image("res/menu/HomeButtonSelected.png");
        this.shopButton = new Image("res/menu/ShopButton.png");
        this.shopButtonSelected = new Image("res/menu/ShopButtonSelected.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //Draw background
        g.drawImage(bg, 0, 0, screen.getScreenWidth(), screen.getScreenHeight(), 0, 0, bg.getWidth(), bg.getHeight());

        //Draw frame
        g.drawImage(frame, screen.getSecureZoneStart(), 0, screen.getSecureZoneEnd(), screen.getSecureZoneHeigth(), 0, 0, frame.getWidth(), frame.getHeight());

        //Draw Title (Niveles)
        titleFont.drawString(screen.getScreenWidth() / 2.46f, 0, "NIVELES", new Color(0, 0, 0, 150));

        //Button Level 1
        drawLevelButton(g, 1, 250, 125, 170, 80);
        //Button Level 2
        drawLevelButton(g, 2, 0, 125, -80, 80);
        //Button Level 3
        drawLevelButton(g, 3, -250, 125, -325, 80);
        //Button Level 4
        drawLevelButton(g, 4, 150, -100, 80, -140);
        //Button Level 5
        drawLevelButton(g, 5, -150, -100, -225, -140);
        //Button Home
        drawOtherButton(g, 5, homeButton, homeButtonSelected, -540, 270);
        //Button Shop
        drawOtherButton(g, 6, shopButton, shopButtonSelected, 540, 270);        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }

    public void keyReleased(int key, char c) {
        if (key == Input.KEY_DOWN) {
            if (selected == 0) {
                selected = 3;
            } else if (selected == 1 || selected == 2) {
                selected = 4;
            } else if (selected == 3) {
                selected = 5;
            } else if (selected == 4) {
                selected = 6;
            } else if (selected == 5) {
                selected = 0;
            } else if (selected == 6) {
                selected = 2;
            }
        }
        if (key == Input.KEY_UP) {
            if (selected == 0 || selected == 1) {
                selected = 5;
            } else if (selected == 2) {
                selected = 6;
            } else if (selected == 3) {
                selected = 0;
            } else if (selected == 4) {
                selected = 1;
            } else if (selected == 5) {
                selected = 3;
            } else if (selected == 6) {
                selected = 4;
            }
        }
        if (key == Input.KEY_LEFT) {
            if (selected == 0) {
                selected = 5;
            } else if (selected == 6) {
                selected = 5;
            } else if (selected == 5) {
                selected = 6;
            } else {
                selected--;
            }
        }
        if (key == Input.KEY_RIGHT) {
            if (selected == 4) {
                selected = 0;
            } else if (selected == 6) {
                selected = 5;
            } else if (selected == 5) {
                selected = 6;
            } else {
                selected++;
            }
        }
        if (key == Input.KEY_ENTER || key == Input.KEY_SPACE) {
            if (selected == 0) {
                game.enterState(Level.ID, new EmptyTransition(), new EmptyTransition());
            } else if (selected == 1) ; else if (selected == 2) ; else if (selected == 3) ; else if (selected == 4) ; else if (selected == 5) ; else if (selected == 6) ;
        }
    }
    
    private void drawOtherButton(Graphics g, int level, Image button, Image buttonSelected, int buttonWidthOffset, int buttonHeightOffset){
        if (selected == level) {
            g.drawImage(buttonSelected, (screen.getScreenWidth() / 2) - (this.button.getWidth() / 2) + buttonWidthOffset, (screen.getScreenHeight() / 2) - (this.button.getHeight() / 2) + buttonHeightOffset);
            return;
        }
        g.drawImage(button, (screen.getScreenWidth() / 2) - (this.button.getWidth() / 2) + buttonWidthOffset, (screen.getScreenHeight() / 2) - (this.button.getHeight() / 2) + buttonHeightOffset);
    }

    private void drawLevelButton(Graphics g, int level, int buttonWidthOffset, int buttonHeightOffset, int levelWidthOffset, int levelHeightOffset) {
        if (selected == (level - 1)) {
            if (unlocked[(level - 1)]) {
                drawButton(g, buttonSelected, buttonWidthOffset, buttonHeightOffset);
                drawString(g, Color.gray, levelWidthOffset, levelHeightOffset, level);
            } else {
                drawButton(g, buttonLockedSelected, buttonWidthOffset, buttonHeightOffset);
            }
        } else if (unlocked[(level - 1)]) {
            drawButton(g, button, buttonWidthOffset, buttonHeightOffset);
            drawString(g, Color.black, levelWidthOffset, levelHeightOffset, level);
        } else {
            drawButton(g, buttonLocked, buttonWidthOffset, buttonHeightOffset);
        }
    }

    public void drawButton(Graphics g, Image button, int widthOffset, int heightOffset) {
        g.drawImage(button, (screen.getScreenWidth() / 2) - (this.button.getWidth() / 2) - widthOffset, (screen.getScreenHeight() / 2) - (this.button.getHeight() / 2) - heightOffset);
    }

    public void drawString(Graphics g, Color color, int widthOffset, int heightOffset, int numberLevel) {
        font.drawString((screen.getScreenWidth() / 2) - (button.getWidth() / 2) - widthOffset, (screen.getScreenHeight() / 2) - (button.getHeight() / 2) - heightOffset, Integer.toString(numberLevel), color);
    }
}
