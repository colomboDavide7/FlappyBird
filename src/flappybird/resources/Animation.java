/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Image;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Animation implements IAnimation {
    
    private List<Image> sprites;
    private int currentFrame = 0;
            
    public Animation(List<Image> sprites){
        this.sprites = sprites;
    }
    
    @Override
    public Image getFrame(){
        return sprites.get(currentFrame);
    }
    
}
