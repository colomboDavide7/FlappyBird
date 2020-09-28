/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

import java.awt.Image;

/**
 *
 * @author davidecolombo
 */
public interface IAnimation {
    
    public abstract Image getFrame();
    
    public abstract boolean matchType(AnimationType type);
    
    public abstract void pauseAnimation();
    
    public abstract void resumeAnimation();
    
}
