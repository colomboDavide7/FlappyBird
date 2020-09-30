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
import java.util.Iterator;
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

    private List<IAvailable> personality;
    private List<ICloneable> prototypes;
    private List<IUpdatable> levelPrototypes;
    
    private Map<String, List<IAnimation>> animations;
    private Map<String, List<IProperties>> properties;
    
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
        this.personality = new ArrayList<>();
        this.prototypes  = new ArrayList<>();
        this.levelPrototypes = new ArrayList<>();
        this.properties = new HashMap<>();
        this.animations = new HashMap<>();
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
        if(AvailablePrototypes.isAvailable(value))
            personality.add(new AvailablePrototypes(value));
        else if (AvailableEnvironment.isAvailable(value))
            personality.add(new AvailableEnvironment(value));
        else
            throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, value);
    }
    
    private void parseProperty(String key, String value) throws LoadException {
        String[] persPropertyPair = key.split(PROPERTY_IDENT_SEPARATOR);
        String pers     = persPropertyPair[0].trim();
        String property = persPropertyPair[1].trim();
        
        List<String> lines = openAndReadTextFile(value);
        
        if(property.equals(AvailableProperties.animation.name()))
            createCharactersAnimations(lines, pers);
        else if(property.equals(AvailableProperties.config.name()))
            properties.put(
                    pers, SimplePropertyFactory.createProperties(pers, lines)
            );
        else
            throw new LoadException(LoadException.ErrorCode.PROPERTY_NOT_FOUND, property);
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
// ===========================================================================================================
    // PROTOTYPES
// ===========================================================================================================
    private void createPrototypes() throws LoadException {
        Set<String> keyset = animations.keySet();
        for(String pers : keyset)
            if(!AvailablePrototypes.isAvailable(pers))
                throw new LoadException(LoadException.ErrorCode.BAD_DEFINITION, pers);
            else
                prototypes.add(
                        SimplePrototypeFactory.createPrototype(pers, animations.get(pers))
                );
    }
    
    private IAvailable searchPersonality(String pers) throws LoadException {
        for(IAvailable a : personality)
            if(pers.equals(a.getMyPersonality()))
                return a;
        throw new LoadException(LoadException.ErrorCode.PERSONALITY_NOT_FOUND, pers);
    }
    
// ===========================================================================================================
    // ENVIRONMENT
// ===========================================================================================================
    private void createEnvironmentPrototypes() throws LoadException {
        Set<String> keyset = properties.keySet();
        
        for(String pers : keyset)
            if(AvailableEnvironment.isAvailable(pers))
                parseEnvironmentProperties(pers);
    }
    
    private void parseEnvironmentProperties(String pers) throws LoadException {
        
        for(IProperties p : properties.get(pers))
            levelPrototypes.add(EnvironmentBuilder.build(
                                    p, 
                                    searchPrototypeAssociatedToMyLevelID(p.getPropertyByKey("levelID")), 
                                    searchPersonality(pers)
                                ));
    }
    
    private List<IConfigurable> searchPrototypeAssociatedToMyLevelID(String levelID) throws LoadException {
        List<IConfigurable> configuredPrototypes = new ArrayList<>();
        
        Set<String> keyset = properties.keySet();
        for(String pers : keyset)
            if(AvailablePrototypes.isAvailable(pers) && isAssociatedToLevelID(pers, levelID))
               cloneAndConfigurePrototype(configuredPrototypes, pers, levelID);
        return configuredPrototypes;
    }
    
    private boolean isAssociatedToLevelID(String pers, String levelID) throws LoadException{
        for(IProperties p : properties.get(pers))
            if(levelID.equals(p.getPropertyByKey("levelID")))
                return true;
        return false;
    }
    
    private void cloneAndConfigurePrototype(List<IConfigurable> prot, String pers, String levelID) throws LoadException {
        ICloneable prototype = searchPrototypeByPersonality(pers);
        IProperties prototypeProp = searchPropertyByPersonalityAndLevelID(pers, levelID);
        int howmany = Integer.parseInt(prototypeProp.getPropertyByKey("howmany"));
        for(int i = 0; i < howmany; i++)
            prot.add(configurePrototype(prototype, prototypeProp, i));
    }
    
    private IConfigurable configurePrototype(ICloneable prototype, IProperties cloneProp, int index) throws LoadException{
        IConfigurable config = (IConfigurable) prototype.clone();
        config.configure(cloneProp);
        int xPosition = (Integer.parseInt(cloneProp.getPropertyByKey("hspace"))*index) + 500;
        System.out.println("xPos = " + xPosition);
        
        config.putProperty("xPosition", Integer.toString(xPosition));
        return config;
    }
            
    private ICloneable searchPrototypeByPersonality(String pers) throws LoadException {
        for(ICloneable c : prototypes)
            if(c.matchPersonality(pers))
                return c;
        throw new LoadException(LoadException.ErrorCode.PERSONALITY_NOT_FOUND, pers);
    }
    
    private IProperties searchPropertyByPersonalityAndLevelID(String pers, String levelID) throws LoadException{
        for(IProperties p : properties.get(pers))
            if(levelID.equals(p.getPropertyByKey("levelID")))
                return p;
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
    public IUpdatable getPlayerByType(String pers) throws LoadException {
        for(ICloneable c : this.prototypes)
            if(c.matchPersonality(pers))
                return (IUpdatable) c.clone();
        throw new LoadException(LoadException.ErrorCode.CREATURE_NOT_FOUND, pers);
    }
    
    public IUpdatable getLevel(String pers) throws LoadException {
        for(Iterator<IUpdatable> it = this.levelPrototypes.iterator(); it.hasNext();){
            ICloneable env = (ICloneable) it.next();
            if(env.matchPersonality(pers))
                return (IUpdatable) env;
        }
        throw new LoadException(LoadException.ErrorCode.ENVIRONMENT_NOT_FOUND, pers);
    }
    
}

// ===========================================================================================================
    