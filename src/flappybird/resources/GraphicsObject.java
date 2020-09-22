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
public abstract class GraphicsObject {
    
    protected Image image;
    protected int xPosition;
    protected int yPosition;
    
    public GraphicsObject(Image image){
        this.image = image;
    }
    
    public abstract Image getImage();
    
    public abstract int getXPositionInPixel();
    
    public abstract int getYPositionInPixel();
    
}
