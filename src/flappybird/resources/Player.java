/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Image;

/**
 *
 * @author davidecolombo
 */
public abstract class Player extends GraphicsObject {
    
    public Player(Image img) {
        super(img);
    }
    
    public abstract void update();
    
}
