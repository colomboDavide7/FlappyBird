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
public class SpriteProperties implements IProperties {

    public enum ValidSpriteProperties{
        path, envID, nFrame, nRow, nCol, 
        hSpaceBetween, hSpaceBegin, hSpaceEnd, 
        vSpaceBetween, vSpaceBegin, vSpaceEnd,
        spriteWidth, spriteHeight;
    }
    
    protected static Map<String, String> creatureProperties;
    
    public static boolean isValid(String property){        
        return creatureProperties.containsKey(property);
    }
    
    static {
        defineAllValidProperties(); 
    }
    
    private static void defineAllValidProperties(){
        creatureProperties = new HashMap<>();
        for(ValidSpriteProperties p : ValidSpriteProperties.values())
            creatureProperties.put(p.name(), p.name());
    }
    
    
// ======================================================================
    protected Map<String, String> myProperties = new HashMap<>();
    
    @Override
    public void putProperty(String key, String value) throws LoadException {
       if(!isValid(key))
           throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, key);
        myProperties.put(key, value);
    }

    @Override
    public String getPropertyByKey(String key) throws LoadException {
        if(!myProperties.containsKey(key))
            throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, key);
        return myProperties.get(key);
    }

    @Override
    public void clearProperties() {
        myProperties.clear();
    }
    
}
