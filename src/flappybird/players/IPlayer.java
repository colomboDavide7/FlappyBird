/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.players;

import flappybird.resources.AnimationType;
import flappybird.generalInterfaces.IRenderable;
import flappybird.generalInterfaces.IUpdatable;

/**
 *
 * @author davidecolombo
 */
public interface IPlayer extends IUpdatable, IRenderable {
    
    public abstract void updateAnimation(AnimationType type);
    
    public abstract void setLocation(int x, int y);
    
    public abstract void jump();
    
    public abstract void fall();
    
}
