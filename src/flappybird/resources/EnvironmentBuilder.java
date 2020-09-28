/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author davidecolombo
 */
public class EnvironmentBuilder {
    
    private static final int MAX_NUMBER_OF_OBJECTS = 10;
    
    private static List<ICreature> creatureRequested = new ArrayList<>();
    private static List<IPowerUp> powerUpRequested = new ArrayList<>();
    
    static IEnvironment build(IProperties myProp, List<IPowerUp> powerUp, String pers) throws LoadException {
        String path = myProp.getPropertyByKey("path");
        BufferedImage bck = tryToReadImage(path);
        AvailableEnvironment type = AvailableEnvironment.valueOf(pers);
        selectPowerUpPrototypes(myProp, powerUp);
        
        return new Environment(powerUpRequested, bck, type);
    }
    
    private static BufferedImage tryToReadImage(String path) throws LoadException {
        try{
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        }catch(IOException ex){
            throw new LoadException(LoadException.ErrorCode.IO_ERROR, path);
        }
    }
    
    private static void selectPowerUpPrototypes(
            IProperties myProp, List<IPowerUp> powerUp
    ) throws LoadException {
        String prototype;
        AvailablePowerUp type;
        
        for(int i = 1; i < MAX_NUMBER_OF_OBJECTS; i++){
            try{
                prototype = myProp.getPropertyByKey("powerup_" + i);
                type = AvailablePowerUp.valueOf(prototype);
                matchPowerUp(prototype, type, powerUp);
            }catch(LoadException ex){
                System.err.println("WARNING");
                System.out.println(ex.errorMessage());
                System.out.println("Prototype not added");
                return;
            }catch(Exception ex){
                throw new LoadException(LoadException.ErrorCode.POWER_UP_NOT_FOUND, ex.getMessage());
            }
        }
    }
    
    private static void matchPowerUp(
            String prototype, AvailablePowerUp type, List<IPowerUp> powerUp
    ) throws Exception{
        
        for(IPowerUp p : powerUp)
            if(p.matchType(type)){
                powerUpRequested.add(p);
                return;
            }
       throw new Exception(prototype);
    }
    
    private static void selectCreaturePrototypes(IProperties myProp, List<ICreature> creatures){
        
    }
    
}
