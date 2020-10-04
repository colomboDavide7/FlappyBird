/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.environment;

import flappybird.powerUp.AvailablePowerUp;
import flappybird.powerUp.IPowerUp;
import flappybird.powerUp.Wall;
import flappybird.resources.LoadException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import flappybird.properties.IBaseProperties;

/**
 *
 * @author davidecolombo
 */
public class EnvironmentBuilder {
    
    public static IEnvironment build(
            AvailableEnvironment pers, IBaseProperties myProp, List<IPowerUp> powerUp
    ) throws LoadException {
        IEnvironment prototype = null;
        
        String path = myProp.getPropertyByKey("path");
        BufferedImage bck = tryToReadImage(path);
        configurePrototypes(powerUp, bck);
        
        switch(pers){
            case easy:
                prototype = new EasyEnvironment(pers, bck, powerUp);
                break;
            case medium:
                prototype = new MediumEnvironment(pers, bck, powerUp);
                break;
            case hard:
                prototype = new HardEnvironment(pers, bck, powerUp);
                break;
            default:
                // TODO throw environment builder exception
        }
        
        return prototype;
    }
    
    private static BufferedImage tryToReadImage(String path) throws LoadException {
        try{
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        }catch(IOException ex){
            throw new LoadException(LoadException.ErrorCode.IO_ERROR, path);
        }
    }
    
    private static void configurePrototypes(
            List<IPowerUp> powerUp, BufferedImage background
    ) throws LoadException{
        for(IPowerUp pw : powerUp)
            if(pw.matchPersonality(AvailablePowerUp.upperWall.name()))
                setupUpperAndCorrespondingDownWalls(
                        (Wall) pw, searchCorrespondingDownWall(powerUp, pw), background
                );
    }
    
    private static void setupUpperAndCorrespondingDownWalls(
            Wall upperWall, Wall downWall, BufferedImage bck
    ) throws LoadException{
        int maxrandom  = upperWall.getMaxRandom();
        int slotHeight = upperWall.getSlotHeight();
        int yInit  = (int) Math.round(Math.random()*maxrandom);
        int height = (int) (bck.getHeight() / 2 - yInit);
        
        upperWall.setYPositionInPixel(0);
        upperWall.setUpperLeftX(0);
        upperWall.setUpperLeftY(yInit);
        upperWall.setTotalHeight(height);

        if(downWall != null){
            downWall.setUpperLeftY(0);
            downWall.setUpperLeftX(0);
            downWall.setYPositionInPixel(height + slotHeight);
            downWall.setTotalHeight(bck.getHeight() - (height + slotHeight));
        }
    }
    
    private static Wall searchCorrespondingDownWall(
            List<IPowerUp> powerUp, IPowerUp upperWall
    ) throws LoadException{
        int xPosition = upperWall.getXPositionInPixel();
        for(IPowerUp pw : powerUp)
            if(pw.getXPositionInPixel() == xPosition && pw.matchPersonality(AvailablePowerUp.downWall.name()))
                return (Wall) pw;
        return null;
    }
    
}
