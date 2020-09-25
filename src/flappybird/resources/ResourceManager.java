/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.animationTool.AnimationBuilder;
import flappybird.animationTool.AnimationToolException;
import flappybird.animationTool.IAnimationTool;
import flappybird.imageTool.ImageResizer;
import flappybird.imageTool.ImageToolException;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import flappybird.imageTool.IImageTool;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidecolombo
 */
public class ResourceManager {
    
    // SINGLETON PATTERN
    private static ResourceManager theInstance = null;
    
    private ResourceManager(){
    }
    
    public static synchronized ResourceManager getInstance(){
        if(theInstance == null)
            theInstance = new ResourceManager();
        return theInstance;
    }
    
// ===========================================================================================================    
    private final String KEY_VALUE_SEPARATOR = "=";
    private boolean resourcesLoaded = false;
    
    private Sprite wallPrototype;
    private Sprite birdPrototype;
    private Environment envPrototype;
    
    public enum PropertiesType {
        bird, wall, env;
    }
    
// ===========================================================================================================
    public void loadResources(String propertyFile){
        try{
            checkMultipleLoading();
            loadPropertiesAndCreatePrototypes(propertyFile);
            resourcesLoaded = true;
        }catch(LoadException ex){
            System.err.println(ex.errorMessage());
            System.exit(1);
        }
    }
    
    private void checkMultipleLoading() throws LoadException {
        if(resourcesLoaded)
            throw new LoadException(LoadException.ErrorCode.MULTIPLE_LOADING);
    }
    
    private void loadPropertiesAndCreatePrototypes(String propertyFile) throws LoadException{
        List<String> files = openAndReadTextFile(propertyFile);
        createProperties(files);
    }
    
// ===========================================================================================================
    private List<String> openAndReadTextFile(String filename) throws LoadException {
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while(true){
                line = reader.readLine();
                if(line == null){
                    reader.close();
                    break;
                }
                
                if(!line.startsWith("#") && !line.isEmpty())
                    lines.add(line);
            }
        }catch(IOException ex){
            throw new LoadException(LoadException.ErrorCode.IO_ERROR, filename);
        }
        return lines;
    }
      
    private void createProperties(List<String> files) throws LoadException {
        IProperties myProperties;
        String filename;
        String propType;
        String[] lineArgs;
        List<String> lines;
        
        for(String file : files){
            lineArgs = file.split(";");
            filename = lineArgs[0].trim();
            propType = lineArgs[1].trim();
            
            if(propType.equalsIgnoreCase(PropertiesType.bird.name())){
                lines = openAndReadTextFile(filename);
                myProperties = new SpriteProperties();
                parsePropertyFile(myProperties, lines);
                createBirdPrototype(myProperties);
                System.out.println(myProperties);
            }
        }
    }
    
    private void parsePropertyFile(IProperties myProperties, List<String> lines) throws LoadException {
        for(String line : lines)
            parsePropertyLine(myProperties, line);
    }
    
// ===========================================================================================================
    private void parsePropertyLine(IProperties myProperties, String line) throws LoadException{
        String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
        String key = keyValuePair[0].trim();
        String value = keyValuePair[1].trim();
        System.out.println("key = " + key);
        System.out.println("value = " + value);
        myProperties.putProperty(key, value);
    }
    
// ===========================================================================================================
    private void createWallPrototype(IProperties myProp) throws LoadException {
        Animation initA = tryToBuildAnimation(myProp);
        this.wallPrototype = new Wall(initA);
    }
    
    private void createBirdPrototype(IProperties myProp) throws LoadException {
        Animation initA = tryToBuildAnimation(myProp);
        this.birdPrototype = new Bird(initA);
    }
    
    private void createEnvironment(IProperties myProp) throws LoadException {
        Image resizedImage = tryToResizeImage(myProp);
//        ObjectProperties myProperties = searchProperties(myProp);
        this.envPrototype = new Environment(resizedImage);
    }
    
//    private ObjectProperties searchProperties(ObjectPropertiesIF myProp) throws LoadException {
//        int envID = Integer.parseInt(myProp.getPropertyByKey("envID"));
//        
//    }    

    private Animation tryToBuildAnimation(IProperties myProp) throws LoadException{
        try{
            String path = myProp.getPropertyByKey("path");
            BufferedImage spriteSheet = readImageFromFile(path);
            IAnimationTool builder = new AnimationBuilder();
            // Properties
            int spriteWidth = Integer.parseInt(myProp.getPropertyByKey("spriteWidth"));
            int spriteHeight = Integer.parseInt(myProp.getPropertyByKey("spriteHeight"));
            int nFrame = Integer.parseInt(myProp.getPropertyByKey("nFrame"));
            int nRow = Integer.parseInt(myProp.getPropertyByKey("nRow"));
            int nCol = Integer.parseInt(myProp.getPropertyByKey("nCol"));
            int hSpaceBegin = Integer.parseInt(myProp.getPropertyByKey("hSpaceBegin"));
            int hSpaceBetween = Integer.parseInt(myProp.getPropertyByKey("hSpaceBetween"));
            int hSpaceEnd = Integer.parseInt(myProp.getPropertyByKey("hSpaceEnd"));
            int vSpaceBetween = Integer.parseInt(myProp.getPropertyByKey("vSpaceBetween"));
            int vSpaceBegin = Integer.parseInt(myProp.getPropertyByKey("vSpaceBegin"));
            int vSpaceEnd = Integer.parseInt(myProp.getPropertyByKey("vSpaceEnd"));
            // Builder configuration
            builder.setHorizontalSpaces(hSpaceBegin, hSpaceBetween, hSpaceEnd);
            builder.setSpriteSheet(spriteSheet);
            builder.setVerticalSpaces(vSpaceBegin, vSpaceBetween, vSpaceEnd);
            builder.setSpriteDimensions(spriteWidth, spriteHeight);
            builder.setSpriteSheetProperties(nRow, nCol, nFrame);
            return builder.build();
        } catch (AnimationToolException ex) {
            throw new LoadException(ex.errorMessage());
        }
    }
    
    private Image tryToResizeImage(IProperties myProp) throws LoadException {
        try{
            String path = myProp.getPropertyByKey("path");
            Image image = readImageFromFile(path);
            int width = Integer.parseInt(myProp.getPropertyByKey("width"));
            int height = Integer.parseInt(myProp.getPropertyByKey("height"));
            IImageTool resizer = new ImageResizer();
            resizer.setImageWidthInPixel(width);
            resizer.setImageHeightInPixel(height);
            resizer.setImageToEdit(image);
            return resizer.resize();
        }catch(ImageToolException ex){
            throw new LoadException(ex.errorMessage());
        }
    }
    
    private BufferedImage readImageFromFile(String path) throws LoadException {
        try{
            File f = new File(path);
            if(!f.exists())
                throw new FileNotFoundException();
            BufferedImage image = ImageIO.read(f);
            return image;
        }catch(FileNotFoundException ex){
            throw new LoadException(LoadException.ErrorCode.RESOURCE_NOT_FOUND, path);
        }catch(IOException ex){
            throw new LoadException(LoadException.ErrorCode.IO_ERROR, path);
        }
    }
    
}
