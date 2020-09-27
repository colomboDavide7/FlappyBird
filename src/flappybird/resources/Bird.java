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
public class Bird implements ICreature {

    private List<IAnimation> animations;
    private float xPosition;
    private float yPosition;
    
    public Bird(List<IAnimation> animations) {
        this.animations = animations;
    }
    
}
