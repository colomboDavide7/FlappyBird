/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.powerUp;

import flappybird.animationTool.IAnimation;
import flappybird.players.IPlayer;
import java.awt.Graphics;
import flappybird.properties.IBaseProperties;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class UpperWall extends Wall {
    
    public UpperWall(List<IAnimation> myAnimations, AvailablePowerUp personality) {
        super(myAnimations, personality);
    }
    
    protected UpperWall(){
        super();
    }
    
    @Override
    public void powerUp(IPlayer player) {
        if(player.matchPosition(xPosition, yPosition, this.currentAnimation.getFrame().getWidth(), totalHeight))
            player.kill();
    }

    @Override
    public Wall cloneObject() {
        Wall clone = new UpperWall();
        clone.personality = AvailablePowerUp.valueOf(personality.name());
        for(IAnimation a : myAnimations)
            clone.myAnimations.add(a.clone());
        clone.currentAnimation = clone.myAnimations.get(0);
        return clone;
    }
    
    @Override
    public void update() {
        if((xPosition + this.currentAnimation.getFrame().getWidth()) <= 0)
            xPosition = 500;
        else
            xPosition -= 3;
    }

    @Override
    public void draw(Graphics g) {
        BufferedImage frame = super.currentAnimation.getFrame();
        g.drawImage(frame.getSubimage(upperLeftX, upperLeftY, this.currentAnimation.getFrame().getWidth(), totalHeight), 
                    xPosition, 
                    yPosition, 
                    null);
    }

    @Override
    public void configure(IBaseProperties myProperties) {
        super.myProp = myProperties;
    }

    @Override
    public void setXPositionInPixel(int x) {
        super.xPosition = x;
    }

    @Override
    public void setYPositionInPixel(int y) {
        super.yPosition = y;
    }

    @Override
    public int getXPositionInPixel() {
        return super.xPosition;
    }

    @Override
    public int getYPositionInPixel() {
        return super.yPosition;
    }

    @Override
    public boolean matchPersonality(String pers) {
        return pers.equals(super.personality.name());
    }
    
}
