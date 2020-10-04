/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.players;

import flappybird.animationTool.AnimationType;
import flappybird.generalInterfaces.IRenderable;
import flappybird.generalInterfaces.ISearchable;
import flappybird.generalInterfaces.IUpdatable;

/**
 *
 * @author davidecolombo
 */
public interface IPlayer extends IUpdatable, IRenderable, ISearchable {
    
    public abstract void updateAnimation(AnimationType type);
    
    public abstract void setLocation(int x, int y);
    
    public abstract void jump();
    
    public abstract void fall();
    
    public abstract void kill();
    
    public abstract boolean isAlive();
    
    public abstract boolean matchPosition(int x, int y, int w, int h);
    
}
