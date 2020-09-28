/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.animationTool.AnimationType;
import java.awt.Graphics;

/**
 *
 * @author davidecolombo
 */
public interface ICreature {
    
    public abstract boolean matchPersonality(AvailableCreature pers);
    
    public abstract void updateAnimation(AnimationType type);
    
    public abstract void draw(Graphics g);
    
}
