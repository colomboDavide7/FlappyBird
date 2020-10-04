/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.properties;

import flappybird.environment.AvailableEnvironment;
import flappybird.resources.LoadException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class EnvironmentPropertiesFactory {
    private static final String KEY_VALUE_SEPARATOR = "=";
    private static final String SAY_NEXT = "NEXT";
    
    public static List<IBaseProperties> createProperties(AvailableEnvironment type, List<String> lines) throws LoadException{
        
        List<IBaseProperties> myProp = new ArrayList<>();
        IBaseProperties singleProp = getPropertyType(type);
        for(String l : lines){
            if(l.equals(SAY_NEXT)){
                myProp.add(singleProp);
                singleProp = getPropertyType(type);
                continue;
            }
            
            String[] keyValuePairs = l.split(KEY_VALUE_SEPARATOR);
            String key = keyValuePairs[0].trim();
            String value = keyValuePairs[1].trim();
            singleProp.putProperty(key, value);  
        }
        return myProp;
    }
    
    private static IBaseProperties getPropertyType(AvailableEnvironment type) throws LoadException{
        switch(type){
            case easy:
                return new EnvironmentProperties();
            case medium:
                return new EnvironmentProperties();
            case hard:
                return new EnvironmentProperties();
            default:
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, type.name());
        }
    }
}
