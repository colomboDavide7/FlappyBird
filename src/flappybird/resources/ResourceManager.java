/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.environment.EnvironmentBuilder;
import flappybird.players.AvailablePlayer;
import flappybird.players.IPlayer;
import flappybird.players.SimplePlayerFactory;
import flappybird.animationTool.IAnimation;
import flappybird.animationTool.AnimationBuilder;
import flappybird.animationTool.AnimationToolException;
import flappybird.environment.AvailableEnvironment;
import flappybird.environment.IEnvironment;
import flappybird.powerUp.AvailablePowerUp;
import flappybird.powerUp.IPowerUp;
import flappybird.powerUp.SimplePowerUpFactory;
import flappybird.properties.AvailableProperties;
import flappybird.properties.BasePropertyFactory;
import flappybird.properties.EnvironmentPropertiesFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import flappybird.properties.IBaseProperties;
import flappybird.properties.IPowerUpProperties;
import flappybird.properties.PowerUpPropertyFactory;

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
    
    private final String DEFINITION_KEY = "definition";
    
    private List<IPlayer> players;
    private List<IPowerUp> powerUps;
    private List<IEnvironment> env;
    private List<String> personalities;

    private Map<String, List<IPowerUpProperties>> powerUpProp;
    private Map<String, List<IBaseProperties>> envProp;
    private Map<String, List<IBaseProperties>> animationProp;
    
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
        this.personalities = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        this.players  = new ArrayList<>();
        this.env      = new ArrayList<>();
        
        this.envProp = new HashMap<>();
        this.powerUpProp = new HashMap<>();
        this.animationProp = new HashMap<>();
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
    
    private void parseDefinition(String def) throws LoadException {
        if(AvailablePlayer.isAvailable(def) ||
           AvailablePowerUp.isAvailable(def) ||
           AvailableEnvironment.isAvailable(def))
         this.personalities.add(def);
        else
            throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, def);
    }
    
    private void parseProperty(String key, String file) throws LoadException {
        String[] persPropertyPair = key.split(PROPERTY_IDENT_SEPARATOR);
        String pers     = persPropertyPair[0].trim();
        String property = persPropertyPair[1].trim();
        List<String> lines = openAndReadTextFile(file);
        
        if(property.equals(AvailableProperties.animation.name())){
            animationProp.put(
                    pers, 
                    BasePropertyFactory.createProperties(AvailableProperties.valueOf(property), lines
            ));
        }else{
            if(AvailablePlayer.isAvailable(pers))
                System.out.println("no player properties yet");
            else if(AvailablePowerUp.isAvailable(pers))
                powerUpProp.put(
                        pers, 
                        PowerUpPropertyFactory.createProperties(AvailablePowerUp.valueOf(pers), lines
                ));
            else if(AvailableEnvironment.isAvailable(pers))
                envProp.put(
                        pers, 
                        EnvironmentPropertiesFactory.createProperties(AvailableEnvironment.valueOf(pers), lines
                ));
            else
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers);
        }
    }
    
// ===========================================================================================================
    // PROTOTYPES
// ===========================================================================================================
    private void createPrototypes() throws LoadException {
        Set<String> keyset = animationProp.keySet();
        for(String pers : keyset)
            if(AvailablePlayer.isAvailable(pers))
                players.add(SimplePlayerFactory.createPlayerByType(
                                AvailablePlayer.valueOf(pers), createCharactersAnimations(pers)
                ));
            else if(AvailablePowerUp.isAvailable(pers))
                powerUps.add(SimplePowerUpFactory.createPowerUpByType(
                                AvailablePowerUp.valueOf(pers), createCharactersAnimations(pers)
                ));
            else
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers);
    }
        
    private List<IAnimation> createCharactersAnimations(String pers) throws LoadException {
        List<IAnimation> myAnimations = new ArrayList<>();
        try{
            for(IBaseProperties prop : animationProp.get(pers)){
                myAnimations.add(AnimationBuilder.build(prop));
            }
            return myAnimations;
        } catch (AnimationToolException ex) {
            throw new LoadException(ex.errorMessage());
        }
    }
    
// ===========================================================================================================
    // ENVIRONMENT
// ===========================================================================================================
    private void createEnvironmentPrototypes() throws LoadException {
        Set<String> keyset = envProp.keySet();
        for(String pers : keyset)
            parseEnvironmentProperties(pers);
    }
    
    private void parseEnvironmentProperties(String pers) throws LoadException {
        for(IBaseProperties myProp : envProp.get(pers))
            env.add(EnvironmentBuilder.build(
                        AvailableEnvironment.valueOf(pers), 
                        myProp, 
                        searchPowerUpAssociatedToMyLevelID(myProp.getPropertyByKey("levelID"))
            ));
    }
    
    private List<IPowerUp> searchPowerUpAssociatedToMyLevelID(String levelID) throws LoadException {
        List<IPowerUp> searchedPrototypes = new ArrayList<>();
        Set<String> keyset = powerUpProp.keySet();
        for(String pers : keyset)
            if(isAssociatedToLevelID(pers, levelID))
               cloneAndConfigurePowerUp(searchedPrototypes, pers, levelID);
        return searchedPrototypes;
    }
    
    private boolean isAssociatedToLevelID(String pers, String levelID) throws LoadException{
        for(IPowerUpProperties p : powerUpProp.get(pers))
            if(levelID.equals(p.getPropertyByKey("levelID")))
                return true;
        return false;
    }
    
    private void cloneAndConfigurePowerUp(List<IPowerUp> searchedPrototypes, String pers, String levelID) throws LoadException {
        IPowerUp prototype = searchPrototypeByPersonality(pers);
        IPowerUpProperties prop = searchPropertyByPersonalityAndLevelID(pers, levelID);
        
        int numberOfClone = Integer.parseInt(prop.getPropertyByKey("howmany"));
        for(int i = 0; i < numberOfClone; i++)
            searchedPrototypes.add(configurePrototype(prototype, prop, i));
    }
    
    private IPowerUp configurePrototype(
            IPowerUp prototype, IPowerUpProperties cloneProp, int index
    ) throws LoadException{
        IPowerUp clone = (IPowerUp) prototype.cloneObject();
        IPowerUpProperties clonedProperties = (IPowerUpProperties) cloneProp.cloneObject();
        clone.configure(clonedProperties);
        
        int xPosition = (Integer.parseInt(clonedProperties.getPropertyByKey("hspace")))*index;
        clone.setXPositionInPixel(xPosition + 500);
        return clone;
    }
            
    private IPowerUp searchPrototypeByPersonality(String pers) throws LoadException {
        for(IPowerUp c : powerUps)
            if(c.matchPersonality(pers))
                return c;
        throw new LoadException(LoadException.ErrorCode.PERSONALITY_NOT_FOUND, pers);
    }
    
    private IPowerUpProperties searchPropertyByPersonalityAndLevelID(String pers, String levelID) throws LoadException{
        for(IPowerUpProperties prop : powerUpProp.get(pers))
            if(levelID.equals(prop.getPropertyByKey("levelID")))
                return prop;
        throw new LoadException(LoadException.ErrorCode.NO_LEVELID_MATCH, levelID);
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
    public IPlayer getPlayerByType(AvailablePlayer playerType) throws LoadException {
        for(IPlayer c : this.players)
            if(c.matchPersonality(playerType.name()))
                return c;
        throw new LoadException(LoadException.ErrorCode.CREATURE_NOT_FOUND, playerType.name());
    }
    
    public IEnvironment getLevelByType(AvailableEnvironment envType) throws LoadException {
        for(IEnvironment e : this.env)
            if(e.matchPersonality(envType.name()))
                return e;
        throw new LoadException(LoadException.ErrorCode.ENVIRONMENT_NOT_FOUND, envType.name());
    }
    
}

// ===========================================================================================================
    