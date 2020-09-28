/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author davidecolombo
 */
public interface IEnvironment {
    
    public abstract boolean matchType(AvailableEnvironment type);
    
    public abstract void update();
    
    public abstract void draw(Graphics g);
    
    public abstract Image getBackgroundImage();
    
}
