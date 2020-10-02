/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.powerUp;

/**
 *
 * @author davidecolombo
 */
public class SimplePowerUpFactory {
    
    public static IPowerUp createPowerUpByType(AvailablePowerUp type){
        IPowerUp powerUp = null;
        switch(type){
            case upperWall: 
                powerUp = new UpperWall();
                break;
            case downWall:
                powerUp = new DownWall();
                break;
        }
        
        return powerUp;
    }
    
}
