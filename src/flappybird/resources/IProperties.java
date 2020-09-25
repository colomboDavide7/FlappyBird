/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

/**
 *
 * @author davidecolombo
 */
public interface IProperties {
        
    public abstract void putProperty(String key, String value) throws LoadException;
    
    public abstract String getPropertyByKey(String key) throws LoadException;
    
    public abstract void clearProperties();
    
}
