/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.properties;

/**
 *
 * @author davidecolombo
 */
public enum AvailableProperties {
    animation, config, cloneconfig;
    
    public static boolean isValid(String myProp) {
        for(AvailableProperties prop : AvailableProperties.values())
            if(myProp.equals(prop.name()))
                return true;
        return false;
    }
    
}
