/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.players;

import flappybird.animationTool.IAnimation;
import flappybird.resources.AnimationType;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Bird implements IPlayer {

    private final double FLY_START = -4.0;
    private final double GRAVITY = 0.64;
    private final double MAX_FALLING_SPEED = 6;
    
    private List<IAnimation> animations;
    private IAnimation currentAnimation;
    
    private double xPosition;
    private double yPosition;
    private double dy = 0;
    private boolean jumping = false;
    
    public Bird(List<IAnimation> animations) {
        this.animations       = animations;
        this.currentAnimation = animations.get(0);
    }
    
    private Bird(){
        this.animations = new ArrayList<>();
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
    
// ==================================================================
    // ICloneable's inherited methods
// ==================================================================
    @Override
    public Bird clone() {
        Bird clone = new Bird();
        
        for(IAnimation a : animations)
            clone.animations.add(a.clone());
        clone.currentAnimation = this.animations.get(0);
        return clone;
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
    
}
