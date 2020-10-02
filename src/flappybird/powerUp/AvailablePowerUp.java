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
public enum AvailablePowerUp {
    upperWall, downWall;
    
    public static boolean isAvailable(String ident){
        for(AvailablePowerUp a : AvailablePowerUp.values())
            if(ident.equals(a.name()))
                return true;
        return false;
    }
    
}
