/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author davidecolombo
 */
public abstract class GraphicsObject {
    
    protected int envID;
    protected Image image;
    protected int xPosition;
    protected int yPosition;
    
    public GraphicsObject(Image image, int envID){
        this.image = image;
        this.envID = envID;
    }
    
    public abstract Image getImage();
    
    public abstract int getXPositionInPixel();
    
    public abstract int getYPositionInPixel();
    
    public abstract String getType();
            
    protected int getEnvID(){
        return this.envID;
    }
    
    protected Image cloneImage(){
        BufferedImage clonedImage = new BufferedImage(this.image.getWidth(null), 
                                                      this.image.getHeight(null), 
                                                      BufferedImage.TYPE_INT_ARGB);
        Graphics g = clonedImage.getGraphics();
        g.drawImage(this.image, 0, 0, null);
        g.dispose();
        return clonedImage;
    }
    
    @Override
    public String toString(){
        String escape = "\n";
        return "envID = " + this.envID + escape +
               "width = " + this.image.getWidth(null) + escape + 
               "height = " + this.image.getHeight(null) + escape +
               "xPos = " + this.xPosition + escape + 
               "yPos = " + this.yPosition + escape;
                
    }
}
