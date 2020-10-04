/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.powerUp;

import flappybird.animationTool.IAnimation;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class SimplePowerUpFactory {
    
    public static IPowerUp createPowerUpByType(AvailablePowerUp type, List<IAnimation> animations){
        IPowerUp powerUp = null;
        switch(type){
            case upperWall: 
                powerUp = new UpperWall(animations, type);
                break;
            case downWall:
                powerUp = new DownWall(animations, type);
                break;
        }
        
        return powerUp;
    }
    
}
