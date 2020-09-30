/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

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
        
        switch(pers){
            case "wallup":
                return createWallProperties(lines);          
            case "walldown":
                return createWallProperties(lines);
            case "easy":
                return createEnvironmentProperties(lines);
            case "medium":
                return createEnvironmentProperties(lines);
            case "hard":
                return createEnvironmentProperties(lines);
            default:
                throw new LoadException(LoadException.ErrorCode.PERSONALITY_NOT_FOUND, pers);
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
