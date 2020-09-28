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
public interface IEnvironment {
    
    public abstract boolean matchID(int id);
    
    public abstract void update();
    
    public abstract void draw(Graphics g);
    
}
