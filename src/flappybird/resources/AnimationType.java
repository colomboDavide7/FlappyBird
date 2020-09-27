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
public enum AnimationType {
    right, left, up, down;
    
    public static boolean isValid(String type){
        for(AnimationType t : AnimationType.values())
            if(type.equals(t.name()))
                return true;
        return false;
    }   
    
}
