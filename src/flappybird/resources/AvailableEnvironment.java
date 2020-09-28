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
public enum AvailableEnvironment {
    easy, medium, hard;
    
    public static boolean isAvailable(String value) {
        for(AvailableEnvironment e : AvailableEnvironment.values())
            if(value.equals(e.name()))
                return true;
        return false;
    }
    
}
