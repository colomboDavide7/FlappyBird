/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author davidecolombo
 */
public class ObjectProperties {
    
    public enum ValidProperties{
        envID, resType, width, height, nWall, nSprite, dist, path;
    }
    
// ===========================================================================================================
    // CLASS VARIABLES AND METHODS
    private static Map<String, String> allProperties;
    
    public static boolean isValid(String property){        
        return allProperties.containsKey(property);
    }
    
    private static void defineAllProperties(){
        allProperties = new HashMap<>();
        for(ValidProperties p : ValidProperties.values())
            allProperties.put(p.name(), p.name());
    }
    
    static{
        defineAllProperties();
    }
    
// ===========================================================================================================
    private Map<String, String> myProperties;
    
    public ObjectProperties(Map<String, String> myProp){
        this.myProperties = myProp;
    }
    
    String getPropertyByKey(String key) throws LoadException {
        if(!myProperties.containsKey(key))
            throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, key);
        return myProperties.get(key);
    }
    
//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        Set<String> keyset = myProperties.keySet();
//        for(String key : keyset)
//            sb.append(key).append(" = ").append(myProperties.get(key)).append("\n");
//        
//        return sb.toString();
//    }
    
}
