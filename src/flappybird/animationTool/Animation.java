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
public class Animation implements IAnimation {
    
    private List<Image> sprites;
    private AnimationType type;
    private int currentFrame = 0;
            
    public Animation(List<Image> sprites, AnimationType type){
        this.sprites = sprites;
        this.type = type;
    }
    
    @Override
    public Image getFrame(){
        return sprites.get(currentFrame);
    }

    @Override
    public AnimationType getType() {
        return this.type;
    }
    
}
