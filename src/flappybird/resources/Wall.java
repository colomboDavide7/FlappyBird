/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.animationTool.IAnimation;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidecolombo
 */
public class Wall implements ICloneable, IUpdatable, IConfigurable, IRenderable {
    
    private List<IAnimation> animations;
    private IAnimation currentAnimation;
    private IAvailable personality;
    private IProperties myProp;
    
    public Wall(List<IAnimation> animations, IAvailable personality) {
        this.animations = animations;
        this.personality = personality;
        this.currentAnimation = animations.get(0);
    }
    
    private Wall(){
        animations = new ArrayList<>();
    }
    
    private void setCurrentAnimationByType(AnimationType type){
        for(IAnimation a : animations)
            if(a.matchType(type))
                this.currentAnimation = a;
    }
    
// ==================================================================
    // ICloneable's inherited methods
// ==================================================================
    @Override
    public Wall clone() {
        Wall clone = new Wall();
        
        for(IAnimation a : animations)
            clone.animations.add(a.clone());
        clone.personality = new AvailablePrototypes(this.personality.getMyPersonality());
        clone.currentAnimation = clone.animations.get(0);
        
        return clone;
    }
    
    @Override
    public boolean matchPersonality(String pers) {
        return pers.equals(this.personality.getMyPersonality());
    }

// ==================================================================
    // IUpdatable's inherited methods
// ==================================================================
    @Override
    public void update() {
        
        int xPosition;
        try {
            
            xPosition = Integer.parseInt(this.myProp.getPropertyByKey("xPosition"));
            if((xPosition + this.currentAnimation.getFrame().getWidth()) <= 0)
                this.myProp.putProperty("xPosition", Integer.toString(288));
            else 
                this.myProp.putProperty("xPosition", Integer.toString(xPosition - 1)); 
        } catch (LoadException ex) {
            Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void updateAnimation(AnimationType type) {
        this.currentAnimation.pauseAnimation();
        setCurrentAnimationByType(type);
        this.currentAnimation.resumeAnimation();
    }
    
// ==================================================================
    // IConfigurable's inherited methods
// ==================================================================
    @Override
    public void configure(IProperties myProp) {    
        this.myProp = myProp;
    }

    @Override
    public void putProperty(String key, String value) throws LoadException {
        this.myProp.putProperty(key, value);
    }
    
    @Override
    public String getProperty(String key) throws LoadException {
        return this.myProp.getPropertyByKey(key);
    }
    
// ==================================================================
    // IRenderable's inherited methods
// ==================================================================
    @Override
    public void draw(Graphics g) {
        
        try {
            int initX = Integer.parseInt(myProp.getPropertyByKey("xInit"));
            int initY = Integer.parseInt(myProp.getPropertyByKey("yInit"));
            int xPosition = Integer.parseInt(myProp.getPropertyByKey("xPosition"));
            int yPosition = Integer.parseInt(myProp.getPropertyByKey("yPosition"));
            int width = this.currentAnimation.getFrame().getWidth();
            int height = Integer.parseInt(myProp.getPropertyByKey("height"));
            
            g.drawImage(this.currentAnimation.getFrame().getSubimage(initX, initY, width, height), 
                        xPosition,
                        yPosition,
                        null);
            
        } catch (LoadException ex) {
            System.out.println(ex.errorMessage());
            System.exit(1);
        }
    }

}
