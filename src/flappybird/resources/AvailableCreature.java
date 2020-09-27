/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

/**
 *
 * @author davidecolombo
 */
public enum AvailableCreature {
    bird;
    
    public static boolean isAvailable(String pers) {
        for(AvailableCreature val : AvailableCreature.values())
            if(pers.equals(val.name()))
                return true;
        return false;
    }
}
