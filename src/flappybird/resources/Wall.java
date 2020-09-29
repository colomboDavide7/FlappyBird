/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.animationTool.IAnimation;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Wall implements IPowerUp {
    
    private List<IAnimation> animations;
    private IAnimation currentAnimation;
    private AvailablePowerUp type;
    private float xPosition;
    private float yPosition;
    
    private int initialX;
    private int initialY;
    private int totalHeight;
    private int totalWidth;
    
    public Wall(List<IAnimation> animations, AvailablePowerUp type) {
        this.animations = animations;
        this.type = type;
        this.currentAnimation = animations.get(0);
    }
    
    @Override
    public Image getCurrentFrame() {
        return this.currentAnimation.getFrame();
    }
    
    @Override
    public void powerup(ICreature creature) {
        // TODO: kill creature
    }

    @Override
    public boolean matchType(AvailablePowerUp type) {
        return this.type == type;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.currentAnimation.getFrame().getSubimage(initialX, initialY, totalWidth, totalHeight),
                    (int) xPosition, 
                    (int) yPosition,
                    null);
    }

    @Override
    public void update(int totalWidthInPixel) {
        if(this.xPosition <= 0)
            this.xPosition = totalWidthInPixel;
        else
            this.xPosition -= 1;
    }

// ====================================================
    @Override
    public IPowerUp clone() {
        Wall clone = new Wall();
        
        for(IAnimation a : animations)
            clone.animations.add(a.clone());
        
        clone.type = AvailablePowerUp.valueOf(this.type.name());
        clone.currentAnimation = clone.animations.get(0);
        return clone;
    }
    
    private Wall(){
        animations = new ArrayList<>();
    }

    @Override
    public boolean matchCurrentAnimation(AnimationType type) {
        return this.currentAnimation.matchType(type);
    }

    @Override
    public void setRectPositionToDrawSubImage(int x, int y, int w, int h) {
        this.initialX = x;
        this.initialY = y;
        this.totalWidth = w;
        
        if(h >= this.currentAnimation.getFrame().getHeight())
            this.totalHeight = this.currentAnimation.getFrame().getHeight();
        else
            this.totalHeight = h;
    }

    @Override
    public void setInitialLocation(int upperLeftX, int upperLeftY) {
        this.xPosition = upperLeftX;
        this.yPosition = upperLeftY;
    }
    
}
