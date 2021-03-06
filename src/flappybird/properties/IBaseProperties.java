/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.properties;

import flappybird.resources.LoadException;

/**
 *
 * @author davidecolombo
 */
public interface IBaseProperties {
    
    public abstract String getPropertyByKey(String key) throws LoadException;
    
    public abstract void putProperty(String key, String value) throws LoadException;
        
}
