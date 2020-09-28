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
public class SimplePowerUpFactory {
    
    public static IPowerUp createPrototype(AvailablePowerUp type, List<IAnimation> animations){
        IPowerUp prototype = null;
        
        switch(type){
            case wall:
                prototype = new Wall(animations, type);
                break;
        }
        
        return prototype;
    }
}
