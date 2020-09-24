/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author davidecolombo
 */
public class BirdProperties implements ObjectPropertiesIF {
    
    public enum ValidBirdProperties{
        path, nSprite, nRow, nCol, 
        HspaceBetweenSprite, HbeginSpace, HendSpace, 
        VbeginSpace, VendSpace,
        width, height;
    }
// =================================================================
    // STATIC VARIABLES AND METHODS
    protected static Map<String, String> validProperties;
    
    public static boolean isValid(String property){        
        return validProperties.containsKey(property);
    }
    
    static {
        initializeValidProperties();
    }
    
    private static void initializeValidProperties(){
        validProperties = new HashMap<>();
        for(ValidBirdProperties p : ValidBirdProperties.values())
            validProperties.put(p.name(), p.name());
    }
    
// =================================================================
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
            throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, key);
        myProperties.put(key, value);
    }
    
    @Override
    public void clearProperties() {
        myProperties.clear();
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
