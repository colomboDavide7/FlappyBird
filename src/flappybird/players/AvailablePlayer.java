/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.players;

/**
 *
 * @author davidecolombo
 */
public enum AvailablePlayer {
    
    bird;
    
    public static boolean isAvailable(String pers){
        for(AvailablePlayer a : AvailablePlayer.values())
            if(pers.equals(a.name()))
                return true;
        return false;
    }
    
}
