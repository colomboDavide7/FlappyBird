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
public abstract class Sprite {
    
    protected Animation currentAnimation;
    protected int xPosition;
    protected int yPosition;
    
    public Sprite(Animation a){
        this.currentAnimation = a;
    }
    
    public Image getAnimationImage(){
        return this.currentAnimation.getFrame();
    }
    
    
//    protected Image cloneImage(){
//        BufferedImage clonedImage = new BufferedImage(this.image.getWidth(null), 
//                                                      this.image.getHeight(null), 
//                                                      BufferedImage.TYPE_INT_ARGB);
//        Graphics g = clonedImage.getGraphics();
//        g.drawImage(this.image, 0, 0, null);
//        g.dispose();
//        return clonedImage;
//    }
    
//    @Override
//    public String toString(){
//        String escape = "\n";
//        return "width = " + this.image.getWidth(null) + escape + 
//               "height = " + this.image.getHeight(null) + escape +
//               "xPos = " + this.xPosition + escape + 
//               "yPos = " + this.yPosition + escape;
//    }
    
}
