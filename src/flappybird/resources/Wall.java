/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Wall implements IPowerUp {
    
    private List<IAnimation> animations;
    private float xPosition;
    private float yPosition;
    
    public Wall(List<IAnimation> animations) {
        this.animations = animations;
    }

    @Override
    public void powerup(ICreature creature) {
        
    }
    
}
