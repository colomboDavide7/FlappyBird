/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.properties;

import flappybird.powerUp.AvailablePowerUp;
import flappybird.resources.LoadException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class PowerUpPropertyFactory {
    private static final String KEY_VALUE_SEPARATOR = "=";
    private static final String SAY_NEXT = "NEXT";
    
    public static List<IPowerUpProperties> createProperties(AvailablePowerUp pers, List<String> lines) throws LoadException{
        List<IPowerUpProperties> myProp = new ArrayList<>();
        IPowerUpProperties singleProp = getPropertyType(pers);
        for(String l : lines){
            if(l.equals(SAY_NEXT)){
                myProp.add(singleProp);
                singleProp = getPropertyType(pers);
                continue;
            }
            
            String[] keyValuePairs = l.split(KEY_VALUE_SEPARATOR);
            String key = keyValuePairs[0].trim();
            String value = keyValuePairs[1].trim();
            singleProp.putProperty(key, value);  
        }
        
        return myProp;
    }
    
    private static IPowerUpProperties getPropertyType(AvailablePowerUp pers) throws LoadException{
        switch(pers){
            case upperWall:
                return new WallProperties();
            case downWall:
                return new WallProperties();
            default:
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers.name());
        }
    }
    
}
