/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.properties;

import flappybird.environment.AvailableEnvironment;
import flappybird.players.AvailablePlayer;
import flappybird.powerUp.AvailablePowerUp;
import flappybird.resources.LoadException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class SimplePropertyFactory {
    
    private static final String KEY_VALUE_SEPARATOR = "=";
    private static final String SAY_NEXT = "NEXT";
    
    public static List<IProperties> createProperties(
            String pers, List<String> lines, AvailableProperties propType
    ) throws LoadException {

        List<IProperties> properties = new ArrayList<>();
        IProperties myProp = createProperty(propType, pers);
        for(String line : lines){
            if(line.equals(SAY_NEXT)){
                properties.add(myProp);
                myProp = createProperty(propType, pers);
                continue;
            }
            
            String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
            String key = keyValuePair[0].trim();
            String value = keyValuePair[1].trim();
            myProp.putProperty(key, value);
        }
        
        return properties;
    }
    
    private static IProperties createProperty(AvailableProperties propType, String pers) throws LoadException{
        switch(propType){
            case animation:
                return new AnimationProperties();
            case config:
                return getPropertyByPersonality(pers);
            default:
                throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, propType.name());
        }
    }
    
    private static IProperties getPropertyByPersonality(String pers) throws LoadException{
        if(AvailablePlayer.isAvailable(pers))
            return createPlayerProperties(AvailablePlayer.valueOf(pers));
        else if(AvailablePowerUp.isAvailable(pers))
            return createPowerUpProperties(AvailablePowerUp.valueOf(pers));
        else if(AvailableEnvironment.isAvailable(pers))
            return new EnvironmentProperties();
        else 
            throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers);
    }
    
    private static IProperties createPlayerProperties(AvailablePlayer pers) throws LoadException{
        switch(pers){
            case bird:
                return null;
            default:
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers.name());
        }
    }
    
    private static IProperties createPowerUpProperties(AvailablePowerUp pers) throws LoadException{
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
