/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.properties;

import com.sun.xml.internal.messaging.saaj.soap.name.NameImpl;
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
    
    public static List<IProperties> createProperties(String pers, List<String> lines) throws LoadException{

        if(AvailablePlayer.isAvailable(pers))
            return createPlayerProperties(AvailablePlayer.valueOf(pers), lines);
        else if(AvailablePowerUp.isAvailable(pers))
            return createPowerUpProperties(AvailablePowerUp.valueOf(pers), lines);
        else if(AvailableEnvironment.isAvailable(pers))
            return createEnvironmentProperties(lines);
        else 
            throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers);
    }
    
    private static List<IProperties> createPlayerProperties(AvailablePlayer pers, List<String> lines) throws LoadException{
        switch(pers){
            case bird:
                return null;
            default:
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers.name());
        }
    }
    
    private static List<IProperties> createPowerUpProperties(AvailablePowerUp pers, List<String> lines) throws LoadException{
        switch(pers){
            case upperWall:
                return createWallProperties(lines);
            case downWall:
                return createWallProperties(lines);
            default:
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers.name());
        }
    }
        
    private static List<IProperties> createWallProperties(List<String> lines) throws LoadException{
        List<IProperties> properties = new ArrayList<>();
        WallProperties myProp = new WallProperties();
        
        for(String line : lines){
            if(line.equals(SAY_NEXT)){
                properties.add(myProp);
                myProp = new WallProperties();
                continue;
            }
            
            String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
            String key = keyValuePair[0].trim();
            String value = keyValuePair[1].trim();
            myProp.putProperty(key, value);
        }
        
        return properties;
    }
    
    private static List<IProperties> createEnvironmentProperties(List<String> lines) throws LoadException {
        List<IProperties> properties = new ArrayList<>();
        EnvironmentProperties myProp = new EnvironmentProperties();
        
        for(String line : lines){
            if(line.equals(SAY_NEXT)){
                properties.add(myProp);
                myProp = new EnvironmentProperties();
                continue;
            }
            
            String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
            String key = keyValuePair[0].trim();
            String value = keyValuePair[1].trim();
            myProp.putProperty(key, value);
        }
        
        return properties;
    }
    
}
