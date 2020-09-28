/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Graphics;

/**
 *
 * @author davidecolombo
 */
public interface IPowerUp {
    
    public abstract void powerup(ICreature creature);
    
    public abstract boolean matchType(AvailablePowerUp type);
        
    public abstract void draw(Graphics g);
    
    public abstract void update();
    
}
