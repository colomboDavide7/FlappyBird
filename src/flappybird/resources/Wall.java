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
public class Wall extends GraphicsObject {
    
    public Wall(Image image, int envID) {
        super(image, envID);
    }

    @Override
    public Image getImage() {
        return super.image;
    }

    @Override
    public synchronized int getXPositionInPixel() {
        return super.xPosition;
    }

    @Override
    public synchronized int getYPositionInPixel() {
        return super.yPosition;
    }

    @Override
    public String getType() {
        return "Wall";
    }
    
    @Override
    public String toString(){
        return super.toString() + 
               "type = " + this.getType();
    }
}
