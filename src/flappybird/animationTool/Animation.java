/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Animation implements IAnimation {
    
    private final int ENGINE_DELAY = 100;
    private AnimationEngine engine = null;
    private List<BufferedImage> sprites;
    private AnimationType type;

    // This method is called by the AnimationBuilder during the configuration
    // of all prototypes. In this case we don't create a new AnimationEngine 
    // because we don't want to waste memory for thread we don't use.
    public Animation(List<BufferedImage> sprites, AnimationType type){
        if(sprites.size() > 1){
            engine = new AnimationEngine(this.ENGINE_DELAY, sprites.size());
            engine.startEngine();
        }
        this.sprites = sprites;
        this.type = type;
    }
    
    @Override
    public BufferedImage getFrame(){
        if(sprites.size() == 1)
            return sprites.get(0);
        else 
            return sprites.get(engine.getCurrentFrameIndex());
    }

    @Override
    public boolean matchType(AnimationType type) {
        return this.type == type;
    }

    @Override
    public void pauseAnimation() {
        if(engine != null)
            this.engine.pauseEngine();
    }

    @Override
    public void resumeAnimation() {
        if(engine != null)
            this.engine.resumeEngine();
    }
    
// ==============================================
    @Override
    public IAnimation clone() {
        Animation clone = new Animation();
        clone.type = AnimationType.valueOf(this.type.name());
        
        for(Image s : sprites)
            clone.sprites.add(cloneImage(s));
        
        if(clone.sprites.size() > 1){
            clone.engine = new AnimationEngine(this.ENGINE_DELAY, clone.sprites.size());
            clone.engine.startEngine();
        }
        
        return clone;
    }
    
    private Animation(){
        sprites = new ArrayList<>();
    }
    
    private BufferedImage cloneImage(Image toClone){
        BufferedImage clonedImage = new BufferedImage(toClone.getWidth(null), 
                                                      toClone.getHeight(null), 
                                                      BufferedImage.TYPE_INT_ARGB);
        Graphics g = clonedImage.getGraphics();
        g.drawImage(toClone, 0, 0, null);
        return clonedImage;
    }
    
}
