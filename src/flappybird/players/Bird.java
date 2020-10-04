/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.players;

import flappybird.animationTool.IAnimation;
import flappybird.animationTool.AnimationType;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Bird implements IPlayer {

    private final double FLY_START = -4.0;
    private final double GRAVITY = 0.64;
    private final double MAX_FALLING_SPEED = 6;
    
    private AvailablePlayer personality;
    private List<IAnimation> animations;
    private IAnimation currentAnimation;
    
    private int xPosition;
    private int yPosition;
    private double dy = 0;
    private boolean jumping = false;
    private boolean isAlive = false;
    
    public Bird(List<IAnimation> animations, AvailablePlayer pers) {
        this.animations       = animations;
        this.currentAnimation = animations.get(0);
        this.personality      = pers;
        this.isAlive = true;
    }
    
    private void setCurrentAnimationByType(AnimationType type){
        for(IAnimation a : animations)
            if(a.matchType(type))
                this.currentAnimation = a;
    }
    
// ==================================================================
    // IPlayer's inherited methods
// ==================================================================
    @Override
    public void setLocation(int xInPixel, int yInPixel){
        this.xPosition = xInPixel;
        this.yPosition = yInPixel;
    }
    
    @Override
    public void updateAnimation(AnimationType type){
        this.currentAnimation.pauseAnimation();
        setCurrentAnimationByType(type);
        this.currentAnimation.resumeAnimation();
    }
    
    @Override
    public void jump() {
        this.jumping = true;
    }
    
    @Override
    public void fall(){
        this.jumping = false;
    }
    
    @Override
    public void kill() {
        // Update kill animation
        
        // Update flag kill
        isAlive = false;
    }
    
    @Override
    public boolean isAlive() {
        return isAlive;
    }
    
    @Override
    public boolean matchPosition(int x, int y) {
        return (this.xPosition <= x && (this.xPosition + currentAnimation.getFrame().getWidth()) >= x &&
                this.yPosition <= y && (this.yPosition + currentAnimation.getFrame().getHeight()) >= y);
    }
        
// ==================================================================
    // IUpdatable's inherited methods
// ==================================================================
    @Override
    public void update() {

        if(jumping)
            this.dy = FLY_START;
        else
            this.dy += GRAVITY;
        
        if(this.dy >= MAX_FALLING_SPEED)
            this.dy = MAX_FALLING_SPEED;
        
        this.yPosition += this.dy;
    }
    
// ==================================================================
    // IRenderable's inherited methods
// ==================================================================
    @Override
    public void draw(Graphics g) {
        g.drawImage(this.currentAnimation.getFrame(), 
                    (int) xPosition, 
                    (int) yPosition, 
                    null);
    }

// ==================================================================
    // ISearchable's inherited methods
// ==================================================================
    @Override
    public boolean matchPersonality(String pers) {
        return pers.equals(this.personality.name());
    }
    
}
