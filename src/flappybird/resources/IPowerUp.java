/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author davidecolombo
 */
public interface IPowerUp {
    
    public abstract Image getCurrentFrame();
    
    public abstract void setRectPositionToDrawSubImage(int x, int y, int w, int h);
            
    public abstract void setInitialLocation(int upperLeftX, int upperLeftY);
    
    public abstract IPowerUp clone();
    
    public abstract void powerup(ICreature creature);
    
    public abstract boolean matchType(AvailablePowerUp type);
    
    public abstract boolean matchCurrentAnimation(AnimationType type);
    
    public abstract void draw(Graphics g);
    
    public abstract void update(int totalWidthInPixel);
    
}
