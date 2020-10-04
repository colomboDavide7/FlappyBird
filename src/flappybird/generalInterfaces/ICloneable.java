/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.generalInterfaces;

import flappybird.resources.LoadException;

/**
 *
 * @author davidecolombo
 */
public interface ICloneable {
    
    public abstract Object cloneObject() throws LoadException;
    
}
