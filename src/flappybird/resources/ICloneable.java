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
public interface ICloneable {
    
    public abstract ICloneable clone();
    
    public abstract boolean matchPersonality(String pers);
    
}