/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.imageTool.ImageResizer;
import flappybird.imageTool.ImageToolException;
import flappybird.imageTool.ImageToolIF;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

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
    
    private GraphicsObject wallPrototype;
    private GraphicsObject birdPrototype;
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
        ObjectPropertiesIF myProperties;
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
                myProperties = new BirdProperties();
                parsePropertyFile(myProperties, lines);
                createBirdPrototype(myProperties);
                System.out.println(myProperties);
            }
        }
    }
    
    private void parsePropertyFile(ObjectPropertiesIF myProperties, List<String> lines) throws LoadException {
        for(String line : lines)
            parsePropertyLine(myProperties, line);
    }
    
// ===========================================================================================================
    private void parsePropertyLine(ObjectPropertiesIF myProperties, String line) throws LoadException{
        String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
        String key = keyValuePair[0].trim();
        String value = keyValuePair[1].trim();
        myProperties.putProperty(key, value);
    }
    
// ===========================================================================================================
    private void createWallPrototype(ObjectPropertiesIF myProp) throws LoadException {
        int envID = Integer.parseInt(myProp.getPropertyByKey("envID"));
        Image resizedImage = tryToResizeImage(myProp);
        this.wallPrototype = new Wall(envID, resizedImage);
    }
    
    private void createBirdPrototype(ObjectPropertiesIF myProp) throws LoadException {
        // ==========================================
        // TODO: estrarre le sprite e ridimensionarle
        // ==========================================
        
        Image resizedImage = tryToResizeImage(myProp);
        this.birdPrototype = new Bird(resizedImage);
    }
    
    private void createEnvironment(ObjectPropertiesIF myProp) throws LoadException {
        Image resizedImage = tryToResizeImage(myProp);
//        ObjectProperties myProperties = searchProperties(myProp);
        this.envPrototype = new Environment(resizedImage);
    }
    
//    private ObjectProperties searchProperties(ObjectPropertiesIF myProp) throws LoadException {
//        int envID = Integer.parseInt(myProp.getPropertyByKey("envID"));
//        
//    }    

    private Image tryToResizeImage(ObjectPropertiesIF myProp) throws LoadException {
        try{
            String path = myProp.getPropertyByKey("path");
            Image image = readImageFromFile(path);
            int width = Integer.parseInt(myProp.getPropertyByKey("width"));
            int height = Integer.parseInt(myProp.getPropertyByKey("height"));
            ImageToolIF resizer = new ImageResizer();
            resizer.setImageWidthInPixel(width);
            resizer.setImageHeightInPixel(height);
            resizer.setImageToEdit(image);
            return resizer.resize();
        }catch(ImageToolException ex){
            throw new LoadException(ex.errorMessage());
        }
    }
    
    private Image readImageFromFile(String path) throws LoadException {
        try{
            File f = new File(path);
            if(!f.exists())
                throw new FileNotFoundException();
            Image image = ImageIO.read(f);
            return image;
        }catch(FileNotFoundException ex){
            throw new LoadException(LoadException.ErrorCode.RESOURCE_NOT_FOUND, path);
        }catch(IOException ex){
            throw new LoadException(LoadException.ErrorCode.IO_ERROR, path);
        }
    }
    
}
