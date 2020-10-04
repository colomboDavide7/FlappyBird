/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.properties;

import flappybird.resources.LoadException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class BasePropertyFactory {
    
    private static final String KEY_VALUE_SEPARATOR = "=";
    private static final String SAY_NEXT = "NEXT";
    
    public static List<IBaseProperties> createProperties(
            AvailableProperties propType, List<String> lines
    ) throws LoadException {
        
        List<IBaseProperties> properties = new ArrayList<>();
        IBaseProperties myProp = createProperty(propType);
        for(String line : lines){
            if(line.equals(SAY_NEXT)){
                properties.add(myProp);
                continue;
            }
            
            String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
            String key = keyValuePair[0].trim();
            String value = keyValuePair[1].trim();
            myProp.putProperty(key, value);
        }
        
        return properties;
    }
    
    private static IBaseProperties createProperty(AvailableProperties propType) throws LoadException{
        switch(propType){
            case animation:
                return new AnimationProperties();
            default:
                throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, propType.name());
        }
    }
    
}
