/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
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
    private enum ResourceType{
        ENV, BND, PLY;
    }
    
    private List<String> lines = new ArrayList<>();
    private boolean resourcesLoaded = false;
    
    private GraphicsObject wallPrototype;
    private GraphicsObject birdPrototype;
    private Environment map;
    
    public void loadResources(String resourceFile) throws LoadException {
        if(resourcesLoaded)
            throw new LoadException(LoadException.ErrorCode.MULTIPLE_LOADING);
        
        openAndReadTextFile(resourceFile);
        createResources();
    }
    
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
            throw new LoadException(LoadException.ErrorCode.TEXT_FILE_ERROR, filename);
        }
    }
    
    private void createResources() throws LoadException {
        for(String line : lines)
            parseLine(line.split("\\s"));
        resourcesLoaded = true;
    }
            
    private void parseLine(String[] args) throws LoadException{
        String resourceType = args[0];
        String directory    = args[1];
        String path = directory;
        
        for(int el = 0; el < args.length - 2; el++){
            path += args[el+2];
            
            if(resourceType.equalsIgnoreCase(ResourceType.ENV.name())){
                
            }else if(resourceType.equalsIgnoreCase(ResourceType.BND.name())){
                createWallPrototype(path);
            }else if(resourceType.equalsIgnoreCase(ResourceType.PLY.name())){
                
            }
        }
    }
    
    private void createWallPrototype(String path) throws LoadException {
        Image image = readImageFromFile(path);
        this.wallPrototype = new Wall(image);
    }
    
    private Image readImageFromFile(String path) throws LoadException {
        try{
            Image image = ImageIO.read(new File(path));
            return image;
        }catch(IOException ex){
            throw new LoadException(LoadException.ErrorCode.RESOURCE_NOT_FOUND, path);
        }
    }
    
}
