/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

/**
 *
 * @author davidecolombo
 */
public enum AnimationType {
    stay, fly_right, fly_left, wall_up, wall_down;
    
    public static boolean isValid(String type){
        for(AnimationType t : AnimationType.values())
            if(type.equals(t.name()))
                return true;
        return false;
    }
    
}
