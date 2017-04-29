package model.model;

import org.newdawn.slick.GameContainer;

/**
 *
 * @author Rift Bandits Games
 */
public class Screen {

    private float secureZoneWidth, secureZoneHeigth, screenWidth, screenHeight;
    private float secureZoneStart, secureZoneEnd;

    public Screen(GameContainer gc) {
        this.screenWidth = gc.getWidth(); //1280
        this.screenHeight = gc.getHeight(); //720
        this.secureZoneHeigth = gc.getHeight(); //720
        this.secureZoneWidth = gc.getHeight() * 1.25f; //900 
        this.secureZoneStart = screenWidth / 2 - secureZoneWidth / 2; //190
        this.secureZoneEnd = screenWidth / 2 + secureZoneWidth / 2; //1090
    }

    public float getSecureZoneWidth() {
        return secureZoneWidth;
    }

    public float getSecureZoneHeigth() {
        return secureZoneHeigth;
    }

    public float getScreenWidth() {
        return screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public float getSecureZoneStart() {
        return secureZoneStart;
    }

    public float getSecureZoneEnd() {
        return secureZoneEnd;
    }
}
