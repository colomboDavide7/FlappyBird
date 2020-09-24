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
public abstract class InteractorObject extends GraphicsObject {
    
    protected int envID;
    
    public InteractorObject(int id, Image img) {
        super(img);
        this.envID = id;
    }
    
}
