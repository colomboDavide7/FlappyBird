/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.util.Map;

/**
 *
 * @author davidecolombo
 */
public class Wall implements IPowerUp {
    
    private Map<AnimationType, IAnimation> animations;
    private float xPosition;
    private float yPosition;
    
    public Wall(Map<AnimationType, IAnimation> animations) {
        this.animations = animations;
    }

    @Override
    public void powerup(ICreature creature) {
        
    }
    
}
