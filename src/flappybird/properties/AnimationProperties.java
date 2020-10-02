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
public class AnimationProperties implements IProperties {

    public enum ValidProperties{
        path, frame, row, col, mirroring, flipping, type;
    }
    
    public static boolean isValid(String property){
        for(ValidProperties prop : ValidProperties.values())
            if(property.equals(prop.name()))
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
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        Set<String> keyset = myProperties.keySet();
        for(String key : keyset)
            sb.append(key).append(" = ").append(myProperties.get(key)).append("\n");
        
        return sb.toString();
    }
    
}
