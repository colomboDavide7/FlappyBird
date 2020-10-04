/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.powerUp;

import flappybird.animationTool.IAnimation;
import flappybird.properties.IBaseProperties;
import flappybird.resources.LoadException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public abstract class Wall implements IPowerUp {
    
    protected IBaseProperties myProp;
    protected AvailablePowerUp personality;
    protected List<IAnimation> myAnimations;
    protected IAnimation currentAnimation;
    protected int xPosition;
    protected int yPosition;
    
    protected int upperLeftX;
    protected int upperLeftY;
    protected int totalHeight;
    
    public Wall(List<IAnimation> myAnimations, AvailablePowerUp personality){
        this.myAnimations = myAnimations;
        this.personality = personality;
        this.currentAnimation = myAnimations.get(0);
    }
    
    protected Wall(){
        this.myAnimations = new ArrayList<>();
    }
    
    public void setUpperLeftX(int x){
        this.upperLeftX = x;
    }
    
    public void setUpperLeftY(int y){
        this.upperLeftY = y;
    }
    
    public void setTotalHeight(int h){
        if(h >= currentAnimation.getFrame().getHeight())
            this.totalHeight = currentAnimation.getFrame().getHeight();
        else
            this.totalHeight = h;
    }
    
    public int getMaxRandom(){
        try {
            return Integer.parseInt(myProp.getPropertyByKey("maxrandom"));
        } catch (LoadException ex) {
            System.out.println("maxrandom property not found in class Wall\n");
            System.exit(1);
        }
        return -1;
    }
    
    public int getSlotHeight(){
        try {
            return Integer.parseInt(myProp.getPropertyByKey("slotheight"));
        } catch (LoadException ex) {
            System.out.println("slotheight property not found in class Wall\n");
            System.exit(1);
        }
        return -1;
    }
    
}
