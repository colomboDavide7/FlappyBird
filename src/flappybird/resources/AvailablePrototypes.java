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
public class AvailablePrototypes implements IAvailable {

    public static enum Available {
        bird, wallup, walldown;
    }
    
    public static boolean isAvailable(String pers) {
        for(Available a : Available.values())
            if(pers.equals(a.name()))
                return true;
        return false;
    }
    
    public AvailablePrototypes(String value) {
        this.myPersonality = Available.valueOf(value);
    }
    
    private Available myPersonality;
    
    @Override
    public String getMyPersonality() {
        return myPersonality.name();
    }
    
}
