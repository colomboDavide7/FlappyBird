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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public enum ResourceType{
        ENV, BND, PLY;
    }
    
    private final String BETWEEN_PROP_SEPARATOR = ";";
    private final String KEY_VALUE_SEPARATOR = "=";
    
    private List<String> lines = new ArrayList<>();
    private boolean resourcesLoaded = false;
    
    private List<ObjectProperties> properties = new ArrayList<>();
    private GraphicsObject wallPrototype;
    private GraphicsObject birdPrototype;
    private Environment envPrototype;
    
// ===========================================================================================================
    public void loadResources(String propertyFile){
        try{
            checkMultipleLoading();
            loadProperties(propertyFile);
            createResources();
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
    
    private void loadProperties(String propertyFile) throws LoadException{
        openAndReadTextFile(propertyFile);
        createProperties();
    }
    
// ===========================================================================================================
    private void openAndReadTextFile(String filename) throws LoadException {
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
    }
        
// ===========================================================================================================
    // OBJECT'S PROPERTIES INSTANTIATION
// ===========================================================================================================
    private void createProperties() throws LoadException {
        for(String line : lines)
            parsePropertyLine(line.split(BETWEEN_PROP_SEPARATOR));
    }
    
// ===========================================================================================================
    private void parsePropertyLine(String[] lineArgs) throws LoadException{
        Map<String, String> myProperties = new HashMap<>();
        
        for(String prop : lineArgs)
            parsePropertyString(myProperties, prop);
            
        this.properties.add(new ObjectProperties(myProperties));
    }
    
// ===========================================================================================================
    private void parsePropertyString(Map<String, String> myProperties, String property) throws LoadException {
        String[] keyValuePair = property.split(KEY_VALUE_SEPARATOR);
        String key = keyValuePair[0].trim();
        
        if(!ObjectProperties.isValid(key))
            throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, key);
        
        String value = keyValuePair[1].trim();
        myProperties.put(key, value);
    }
            
// ===========================================================================================================
    // OBJECT'S RESOURCES INSTANTIATION
// ===========================================================================================================
    private void createResources() throws LoadException {
        for(ObjectProperties prop : properties)
            parseProperty(prop);
    }
      
// ===========================================================================================================
    private void parseProperty(ObjectProperties myProp) throws LoadException {
        String resType = myProp.getPropertyByKey("resType");
        if(resType.equalsIgnoreCase(ResourceType.PLY.name())){
            createBirdPrototype(myProp);
        }else if(resType.equalsIgnoreCase(ResourceType.BND.name())){
            createWallPrototype(myProp);
        }else if(resType.equalsIgnoreCase(ResourceType.ENV.name())){
            createEnvironment(myProp);
        }
    }
    
// ===========================================================================================================
    private void createWallPrototype(ObjectProperties myProp) throws LoadException {
        int envID = Integer.parseInt(myProp.getPropertyByKey("envID"));
        Image resizedImage = tryToResizeImage(myProp);
        this.wallPrototype = new Wall(resizedImage, envID);
    }
    
    private void createBirdPrototype(ObjectProperties myProp) throws LoadException {
        int envID = Integer.parseInt(myProp.getPropertyByKey("envID"));
        // ==========================================
        // TODO: estrarre le sprite e ridimensionarle
        // ==========================================
        
        Image resizedImage = tryToResizeImage(myProp);
        this.birdPrototype = new Bird(resizedImage, envID);
    }
    
    private void createEnvironment(ObjectProperties myProp) throws LoadException {
        int envID = Integer.parseInt(myProp.getPropertyByKey("envID"));
        Image resizedImage = tryToResizeImage(myProp);
//        ObjectProperties myProperties = searchProperties(envID, resType);
        this.envPrototype = new Environment(resizedImage);
    }
    
//    private ObjectProperties searchProperties(int envID, String resType) throws LoadException {
//        for(ObjectProperties p : properties)
//            if(envID == p.getAssociatedEnvironmentID() && resType.equals(p.getAssociatedResourceType()))
//                return p;
//        throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, Integer.toString(envID));
//    }    

    private Image tryToResizeImage(ObjectProperties myProp) throws LoadException {
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
