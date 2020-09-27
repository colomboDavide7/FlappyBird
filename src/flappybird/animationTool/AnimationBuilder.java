/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

import flappybird.resources.Animation;
import flappybird.resources.AnimationType;
import flappybird.resources.IAnimation;
import flappybird.resources.IProperties;
import flappybird.resources.LoadException;
import java.awt.Image;
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
public class AnimationBuilder {

    public static IAnimation build(IProperties prop) throws AnimationToolException {        
        try {
            String path = prop.getPropertyByKey("path");
            BufferedImage sheet = tryToReadImage(path);
            List<Image> sprites = getSpriteListFromProperty(prop, sheet);
            AnimationType type = getTypeFromProperty(prop);
            
            return new Animation(sprites, type);
        } catch (LoadException ex) {
            throw new AnimationToolException(ex.errorMessage());
        }
    }
    
    private static BufferedImage tryToReadImage(String path) throws AnimationToolException {
        try{
            BufferedImage img = ImageIO.read(new File(path));
            return img;
        }catch(IOException ex){
            throw new AnimationToolException(AnimationToolException.ErrorCode.IO_ERROR, path);
        }
    }
    
    private static AnimationType getTypeFromProperty(IProperties prop) throws AnimationToolException{
        try {
            String type = prop.getPropertyByKey("type");
            
            if(!AnimationType.isValid(type))
                throw new AnimationToolException(AnimationToolException.ErrorCode.INVALID_TYPE, type);
            return AnimationType.valueOf(type);
        } catch (LoadException ex) {
            throw new AnimationToolException(ex.errorMessage());
        }
    }
    
    private static List<Image> getSpriteListFromProperty(IProperties prop, BufferedImage sheet) throws AnimationToolException {
        try{
            int upperLeftX = 0;
            int upperLeftY = 0;
            int frame = Integer.parseInt(prop.getPropertyByKey("frame"));
            List<Image> sprites = new ArrayList<>(frame);
            int col   = Integer.parseInt(prop.getPropertyByKey("col"));
            int row   = Integer.parseInt(prop.getPropertyByKey("row"));
            int spriteWidth = sheet.getWidth() / col;
            int spriteHeight = sheet.getHeight() / row;
            
            for(int i = 0; i < frame; i++){
                sprites.add(
                        sheet.getSubimage(upperLeftX, upperLeftY, spriteWidth, spriteHeight)
                );
                
                upperLeftX += spriteWidth;
                
                if(upperLeftX >= spriteWidth * col){
                    upperLeftX = 0;
                    upperLeftY += spriteHeight;
                }
                
            }
            
            return sprites;
        }catch(LoadException ex){
            throw new AnimationToolException(ex.errorMessage());
        }
    }
}
