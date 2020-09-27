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
public enum AvailableProperties {
    animation;
    
    public static boolean isValid(String myProp) throws LoadException {
        for(AvailableProperties prop : AvailableProperties.values())
            if(myProp.equals(prop.name()))
                return true;
        throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, myProp);
    }
    
}
