/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.environment;

/**
 *
 * @author davidecolombo
 */
public enum AvailableEnvironment {
    easy, medium, hard;
    
    public static boolean isAvailable(String type){
        for(AvailableEnvironment a : AvailableEnvironment.values())
            if(type.equals(a.name()))
                return true;
        return false;
    }
    
}
