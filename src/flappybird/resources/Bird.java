/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.animationTool.IAnimation;
import java.awt.Graphics;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Bird implements ICreature {

    private List<IAnimation> animations;
    private IAnimation currentAnimation;
    
    private AvailableCreature personality;
    private float xPosition;
    private float yPosition;
    
    public Bird(List<IAnimation> animations, AvailableCreature pers) {
        this.animations = animations;
        this.currentAnimation = animations.get(0);
        this.personality = pers;
    }

    @Override
    public boolean matchPersonality(AvailableCreature pers) {
        return this.personality == pers;
    }

    @Override
    public void updateAnimation(AnimationType type) {
        this.currentAnimation.pauseAnimation();
        
        for(IAnimation a : animations)
            if(a.matchType(type)){
                a.resumeAnimation();
                this.currentAnimation = a;
            }
    }
    
    @Override
    public synchronized void draw(Graphics g) {
        g.drawImage(this.currentAnimation.getFrame(), 
                    (int) xPosition, 
                    (int) yPosition, 
                    null);
    }

    @Override
    public void setLocation(int xPos, int yPos) {
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.centerLocation();
    }
    
    private void centerLocation(){
        this.xPosition = this.xPosition - this.currentAnimation.getFrame().getWidth(null) / 2;
        this.yPosition = this.yPosition - this.currentAnimation.getFrame().getHeight(null) / 2;
    }   

    @Override
    public void update() {
        // Location
    }
    
}
