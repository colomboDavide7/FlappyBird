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
public class Wall implements IPowerUp {
    
    private List<IAnimation> animations;
    private IAnimation currentAnimation;
    private AvailablePowerUp type;
    
    private float xPosition;
    private float yPosition;
    
    public Wall(List<IAnimation> animations, AvailablePowerUp type) {
        this.animations = animations;
        this.type = type;
        this.currentAnimation = animations.get(0);
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
        g.drawImage(this.currentAnimation.getFrame(), 
                    (int) xPosition, 
                    (int) yPosition, 
                    null);
    }

    @Override
    public void update() {
        if(this.xPosition <= 0)
            this.xPosition = 500;
        else
            this.xPosition -= 10;
    }
    
}
