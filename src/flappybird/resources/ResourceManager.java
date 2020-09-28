/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.animationTool.IAnimation;
import flappybird.animationTool.AnimationBuilder;
import flappybird.animationTool.AnimationToolException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private final String PROPERTY_IDENT_SEPARATOR = "_";
    private final String SAY_NEXT = "NEXT";
    
    private final String DEFINITION_KEY = "definition";
    
    private List<AvailableCreature> availableCreatures;
    private List<AvailablePowerUp> availablePowerUp;
    private List<AvailableEnvironment> availableLevels;
    
    private List<ICreature> creaturePrototypes;
    private List<IPowerUp> powerUpPrototypes;
    private List<IEnvironment> levelPrototypes;
    
    private Map<String, List<IAnimation>> animations;
    private Map<String, IProperties> levelProperties;
    
    private boolean resourcesLoaded = false;
    
// ===========================================================================================================
    public void loadResources(String propertyFile){
        try{
            // Checking
            checkMultipleLoading(propertyFile);
            
            // Loading
            loadProperties(propertyFile);
            
            // Creating
            createPrototypes();
            
            // Environment
            createEnvironmentPrototypes();
            
            // Done
            resourcesLoaded = true;
        }catch(LoadException ex){
            System.err.println(ex.errorMessage());
            System.exit(1);
        }
    }
    
    private void loadProperties(String file) throws LoadException {
        initObjects();
        List<String> lines = openAndReadTextFile(file);
        parseFile(lines);
    }
    
    private void initObjects(){
        this.availableCreatures  = new ArrayList<>();
        this.availablePowerUp    = new ArrayList<>();
        this.availableLevels     = new ArrayList<>();
        this.animations          = new HashMap<>();
        this.creaturePrototypes  = new ArrayList<>();
        this.powerUpPrototypes   = new ArrayList<>();
        this.levelPrototypes     = new ArrayList<>();
        this.levelProperties     = new HashMap<>();
    }
    
    private void parseFile(List<String> lines) throws LoadException{
        for(String line : lines)
            parseLine(line);
    }
    
    private void parseLine(String line) throws LoadException{
        String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
        String key   = keyValuePair[0].trim();
        String value = keyValuePair[1].trim();
        
        if(key.equals(DEFINITION_KEY))
            parseDefinition(value);
        else
            parseProperty(key, value);
    }
    
    private void parseDefinition(String value) throws LoadException {
        if(AvailableCreature.isAvailable(value))
            this.availableCreatures.add(AvailableCreature.valueOf(value));
        else if(AvailablePowerUp.isAvailable(value))
            this.availablePowerUp.add(AvailablePowerUp.valueOf(value));
        else if(AvailableEnvironment.isAvailable(value))
            this.availableLevels.add(AvailableEnvironment.valueOf(value));
        else
            throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, value);
    }
    
    private void parseProperty(String key, String value) throws LoadException {
        String[] persPropertyPair = key.split(PROPERTY_IDENT_SEPARATOR);
        String pers     = persPropertyPair[0].trim();
        String property = persPropertyPair[1].trim();
        List<String> lines;
        
        if(property.equals(AvailableProperties.animation.name())){
            lines = openAndReadTextFile(value);
            createCharactersAnimations(lines, pers);
        }else if(property.equals(AvailableProperties.config.name())){
            lines = openAndReadTextFile(value);
            createEnvironmentProperties(lines, pers);
        }else{
            throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, property);
        }
            
    }
    
    private void createCharactersAnimations(List<String> lines, String personality) throws LoadException {
        List<IAnimation> myAnimations = new ArrayList<>();
        IProperties myProperties = new AnimationProperties();
        
        try{
            for(String line : lines){
                if(line.equals(SAY_NEXT)){
                    myAnimations.add(AnimationBuilder.build(myProperties));
                    continue;
                }
                
                String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
                String key = keyValuePair[0].trim();
                String value = keyValuePair[1].trim();
                myProperties.putProperty(key, value);
            }
            
            this.animations.put(personality, myAnimations);
        
        } catch (AnimationToolException ex) {
            throw new LoadException(ex.errorMessage());
        }
    }
    
    private void createPrototypes() throws LoadException {
        Set<String> keyset = animations.keySet();
        for(String pers : keyset)
            if(AvailableCreature.isAvailable(pers))
                this.creaturePrototypes.add(SimpleCreatureFactory.createPrototype(
                                                AvailableCreature.valueOf(pers), animations.get(pers)
                                            ));
            else if(AvailablePowerUp.isAvailable(pers))
                this.powerUpPrototypes.add(SimplePowerUpFactory.createPrototype(
                                                AvailablePowerUp.valueOf(pers), animations.get(pers)
                                            ));
            else
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers);
    }
    
    private void createEnvironmentProperties(List<String> lines, String pers) throws LoadException {
        IProperties myProp = new EnvironmentProperties();
        
        for(String line : lines){
            if(line.equals(SAY_NEXT)){
                levelProperties.put(pers, myProp);
                continue;
            }
            
            String[] keyValuePair = line.split(KEY_VALUE_SEPARATOR);
            String key = keyValuePair[0].trim();
            String value = keyValuePair[1].trim();
            myProp.putProperty(key, value);
        }
    }
    
    private void createEnvironmentPrototypes() throws LoadException {
        Set<String> keyset = levelProperties.keySet();
        
        for(String pers : keyset)
            levelPrototypes.add(EnvironmentBuilder.build(
                    levelProperties.get(pers), powerUpPrototypes)
            );
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
                
                if(!line.isEmpty())
                    if(Character.isLetter(line.charAt(0)))
                        lines.add(line);
            }
        }catch(IOException ex){
            throw new LoadException(LoadException.ErrorCode.IO_ERROR, filename);
        }
        return lines;
    }
    
    private void checkMultipleLoading(String filename) throws LoadException {
        if(resourcesLoaded)
            throw new LoadException(LoadException.ErrorCode.MULTIPLE_LOADING, filename);
    }
    
// ===========================================================================================================
    public ICreature getPlayerByType(AvailableCreature type) throws LoadException {
        for(ICreature c : this.creaturePrototypes)
            if(c.matchPersonality(type))
                return c;
        throw new LoadException(LoadException.ErrorCode.CREATURE_NOT_FOUND, type.name());
    }
    
}

// ===========================================================================================================

    