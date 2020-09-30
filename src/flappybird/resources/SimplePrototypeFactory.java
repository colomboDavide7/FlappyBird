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
public class SimplePrototypeFactory {
    
    public static ICloneable createPrototype(String pers, List<IAnimation> animations)  {
        ICloneable prototype = null;
        switch(pers){
            case "bird":
                prototype = new Bird(animations, new AvailablePrototypes(pers));
                break;
            case "wallup":
                prototype = new Wall(animations, new AvailablePrototypes(pers));
                break;
            case "walldown": 
                prototype = new Wall(animations, new AvailablePrototypes(pers));
                break;
        }
        
        return prototype;
    }
    
}
