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
public interface IConfigurable {
    
    public abstract void configure(IProperties myProperties);
    
    public abstract void putProperty(String key, String value) throws LoadException;
    
    public abstract String getProperty(String key) throws LoadException;
    
    public abstract boolean matchPersonality(String pers);
    
}
