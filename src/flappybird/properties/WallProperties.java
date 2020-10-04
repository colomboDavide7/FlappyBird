/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.properties;

import flappybird.resources.LoadException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author davidecolombo
 */
public class WallProperties implements IPowerUpProperties {

    public enum ValidProperties{
        levelID, howmany, hspace, maxrandom, slotheight;
    }
    
    public static boolean isValid(String prop){
        for(ValidProperties p : ValidProperties.values())
            if(prop.equals(p.name()))
                return true;
        return false;
    }
    
    private Map<String, String> myProperties = new HashMap<>();
    
    @Override
    public String getPropertyByKey(String key) throws LoadException {
        if(!myProperties.containsKey(key))
            throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, key);
        return myProperties.get(key);
    }

    @Override
    public void putProperty(String key, String value) throws LoadException {
        if(!isValid(key))
            throw new LoadException(LoadException.ErrorCode.INVALID_PROPERTY, key);
        myProperties.put(key, value);
    }
    
    @Override
    public WallProperties cloneObject() throws LoadException {
        WallProperties clone = new WallProperties();
        Set<String> keyset = myProperties.keySet();
        for(String key : keyset)
            clone.putProperty(key, myProperties.get(key));
        return clone;
    }
    
}
