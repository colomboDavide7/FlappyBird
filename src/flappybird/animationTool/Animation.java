/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

import java.awt.Image;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Animation {
    
    private List<Image> sprites;
    private int currentFrame = 0;
            
    public Animation(List<Image> sprites){
        this.sprites = sprites;
    }
    
    public Image getFrame(){
        return sprites.get(currentFrame);
    }
    
}
