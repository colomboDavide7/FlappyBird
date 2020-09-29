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
    
    private static List<ICreature> creaturePrototypes = new ArrayList<>();
    private static List<IPowerUp> powerUpPrototypes = new ArrayList<>();
    private static List<IPowerUp> powerUpRequested = new ArrayList<>();
    private static int hSpaceBetween;
    private static int slotHeight;
    private static int upperLineOffset;
    private static int nWall;
    
    static IEnvironment build(IProperties myProp, List<IPowerUp> powerUp, AvailableEnvironment type) throws LoadException {
        String path = myProp.getPropertyByKey("path");
        BufferedImage bck = tryToReadImage(path);
        selectPowerUpPrototypes(myProp, powerUp);
        
        hSpaceBetween = Integer.parseInt(myProp.getPropertyByKey("hSpaceBetween"));
        slotHeight = Integer.parseInt(myProp.getPropertyByKey("slotHeight"));
        upperLineOffset = Integer.parseInt(myProp.getPropertyByKey("centerToUpperLineOffset"));
        nWall = Integer.parseInt(myProp.getPropertyByKey("nWall"));
        setupAllObjects(bck);
        
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
                powerUpPrototypes.add(p);
                return;
            }
       throw new Exception(prototype);
    }
    
    private static void setupAllObjects(BufferedImage bck) throws LoadException {
        
        setupPowerUps(bck);
    }
    
    private static void setupPowerUps(BufferedImage bck) throws LoadException{
        
        for(IPowerUp p : powerUpPrototypes)
            if(p.matchType(AvailablePowerUp.wallup))
                setupUpperWall(bck, p);
            else if(p.matchType(AvailablePowerUp.walldown))
                setupDownWall(bck, p);
    }
    
    private static void setupUpperWall(BufferedImage bck, IPowerUp wall) throws LoadException {
        
        int totalHeight = bck.getHeight() / 2 - upperLineOffset;
        int initialY;
        int upperLeftX = 0;
        
        for(int i = 0; i < nWall; i++){
            IPowerUp clone = wall.clone();
            clone.setInitialLocation(upperLeftX, 0);
            initialY = wall.getCurrentFrame().getHeight(null) - totalHeight;
            clone.setRectPositionToDrawSubImage(0, initialY, wall.getCurrentFrame().getWidth(null), totalHeight
            );
            powerUpRequested.add(clone);
            upperLeftX += wall.getCurrentFrame().getWidth(null) + hSpaceBetween;
        }
    }
    
    private static void setupDownWall(BufferedImage bck, IPowerUp wall) throws LoadException {
        int totalHeight = bck.getHeight() / 2 - upperLineOffset;
        int upperLeftX = 0;
        
        for(int i = 0; i < nWall; i++){
            IPowerUp clone = wall.clone();
            clone.setInitialLocation(upperLeftX, totalHeight + slotHeight);
            clone.setRectPositionToDrawSubImage(
                    0, 0, wall.getCurrentFrame().getWidth(null), totalHeight
            );
            
            powerUpRequested.add(clone);
            upperLeftX += wall.getCurrentFrame().getWidth(null) + hSpaceBetween;
        }
    }
    
    private static void selectCreaturePrototypes(IProperties myProp, List<ICreature> creatures){
        
    }
    
}
