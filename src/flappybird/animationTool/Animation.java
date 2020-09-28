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
    
    private AnimationEngine engine;
    
    private List<Image> sprites;
    private AnimationType type;
            
    public Animation(List<Image> sprites, AnimationType type){
        this.engine = new AnimationEngine(150, sprites.size());
        this.sprites = sprites;
        this.type = type;
        this.engine.startEngine();
    }
    
    @Override
    public Image getFrame(){
        return sprites.get(engine.getCurrentFrameIndex());
    }

    @Override
    public boolean matchType(AnimationType type) {
        return this.type == type;
    }

    @Override
    public void pauseAnimation() {
        this.engine.pauseEngine();
    }

    @Override
    public void resumeAnimation() {
        this.engine.resumeEngine();
    }
    
}
