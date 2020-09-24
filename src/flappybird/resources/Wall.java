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
public class Wall extends InteractionObject {

    public Wall(int id, Image img) {
        super(id, img);
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

    @Override
    public void interaction() {
        // TODO: collision
    }
    
}
