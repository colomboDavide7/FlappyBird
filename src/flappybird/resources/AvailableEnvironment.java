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
public class AvailableEnvironment implements IAvailable {
    
    public enum Available {
        easy, medium, hard;
    }
    
    public static boolean isAvailable(String value) {
        for(Available e : Available.values())
            if(value.equals(e.name()))
                return true;
        return false;
    }

    public AvailableEnvironment(String myPersonality){
        this.myPersonality = Available.valueOf(myPersonality);
    }
    
    private Available myPersonality;
    
    @Override
    public String getMyPersonality() {
        return myPersonality.name();
    }
    
}
