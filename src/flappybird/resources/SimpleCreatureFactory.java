/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.animationTool.IAnimation;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class SimpleCreatureFactory {
    
    public static ICreature createPrototype(AvailableCreature personality, List<IAnimation> animations){
        
        ICreature prototype = null;
        switch(personality){
            case bird:
                prototype = new Bird(animations, personality);
                break;
        }
        
        return prototype;
    }
    
}
