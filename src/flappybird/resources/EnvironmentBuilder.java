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
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author davidecolombo
 */
public class EnvironmentBuilder {
    
    static IUpdatable build(IProperties myProp, List<IConfigurable> prototypes, IAvailable pers) throws LoadException {
        String path = myProp.getPropertyByKey("path");
        BufferedImage bck = tryToReadImage(path);
        configurePrototypes(prototypes, bck);
                
        List<IUpdatable> updatable = new ArrayList<>();
        for(Iterator<IConfigurable> it = prototypes.iterator(); it.hasNext();)
            updatable.add((IUpdatable) it.next());
        
        return new Environment(updatable, bck, pers);
    }
    
    private static BufferedImage tryToReadImage(String path) throws LoadException {
        try{
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        }catch(IOException ex){
            throw new LoadException(LoadException.ErrorCode.IO_ERROR, path);
        }
    }
    
    private static void configurePrototypes(List<IConfigurable> prototypes, BufferedImage backgroundImage) throws LoadException{
        for(IConfigurable p : prototypes)
            if(p.matchPersonality(AvailablePrototypes.Available.wallup.name()))
                setupUpperAndCorrespondingDownWalls(prototypes, p, backgroundImage);
    }
    
    private static void setupUpperAndCorrespondingDownWalls(
            List<IConfigurable> prototypes, IConfigurable upperWall, BufferedImage bck
    ) throws LoadException{
        
        IConfigurable downWall = searchDownWallCorrespondingToXPosition(
                                    prototypes, upperWall.getProperty("xPosition")
                                 );
        
        upperWall.putProperty("yPosition", Integer.toString(0));
        upperWall.putProperty("xInit", Integer.toString(0));
        
        int maxrandom = Integer.parseInt(upperWall.getProperty("maxrandom"));
        int yInit = (int) Math.round(Math.random()*maxrandom);
        int height = (int) (bck.getHeight() / 2 - yInit);
        
        upperWall.putProperty("yInit", Integer.toString(yInit));
        upperWall.putProperty("height", Integer.toString(height));
        
        int slotHeight = Integer.parseInt(upperWall.getProperty("slotheight"));
        if(downWall != null){
            downWall.putProperty("yInit", Integer.toString(0));
            downWall.putProperty("xInit", Integer.toString(0));
            downWall.putProperty("yPosition", Integer.toString(height + slotHeight));
            downWall.putProperty("height", Integer.toString(bck.getHeight() - (height + slotHeight)));
        }
    }
    
    private static IConfigurable searchDownWallCorrespondingToXPosition(
            List<IConfigurable> prototypes, String xPosition
    ) throws LoadException{
        for(IConfigurable p : prototypes)
            if(xPosition.equals(p.getProperty("xPosition")))
                return p;
        return null;
    }
    
}
