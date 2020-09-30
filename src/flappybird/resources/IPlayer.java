/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

/**
 *
 * @author davidecolombo
 */
public interface IPlayer {
    
    public abstract void updateAnimation(AnimationType type);
    
    public abstract void setLocation(int x, int y);
    
    public abstract void jump();
    
    public abstract void fall();
    
}
