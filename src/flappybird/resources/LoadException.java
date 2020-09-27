/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

/**
 *
 * @author davidecolombo
 */
public class LoadException extends Exception {
    
    public enum ErrorCode {
        MULTIPLE_LOADING, IO_ERROR,
        RESOURCE_NOT_FOUND, PROPERTY_NOT_FOUND, 
        EXTERNAL_API_ERROR, BAD_DEFINITION,
        CREATURE_NOT_FOUND, POWER_UP_NOT_FOUND;
    }
    
    private String errorMessage = "";
    private ErrorCode errorCode;
    private String invalidResource = "";
    
    public LoadException(String errorMessage){
        this.errorCode = ErrorCode.EXTERNAL_API_ERROR;
        this.errorMessage = errorMessage;
    }
    
    public LoadException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
    
    public LoadException(ErrorCode errorCode, String invalidResource){
        this.errorCode = errorCode;
        this.invalidResource = invalidResource;
    }
    
    public String errorMessage() {
        switch(errorCode){
            case EXTERNAL_API_ERROR: 
                return this.errorMessage;
                
            case MULTIPLE_LOADING:
                return String.format("%s error code has occured.\n"
                                   + "You can't load the '%s' file multiple times", 
                                     errorCode, invalidResource);
            case RESOURCE_NOT_FOUND: 
                return String.format("%s error code has occured.\n"
                                   + "The resource correspond to '%s' was not found.", 
                                     errorCode, invalidResource);
            case IO_ERROR: 
                return String.format("%s error code has occured.\n"
                                   + "Error with '%s' file.", 
                                     errorCode, invalidResource);
            case PROPERTY_NOT_FOUND: 
                return String.format("%s error code has occured.\n"
                                   + "No key property associated to '%s'.", 
                                     errorCode, invalidResource);
            case CREATURE_NOT_FOUND: 
                return String.format("%s error code has occured.\n"
                                   + "No creature associated to '%s'.", 
                                     errorCode, invalidResource);
            case POWER_UP_NOT_FOUND:
                return String.format("%s error code has occured.\n"
                                   + "No power up associated to '%s'.", 
                                     errorCode, invalidResource);
            case BAD_DEFINITION: 
                return String.format("%s error code has occured.\n"
                                   + "The type associated to '%s' doesn't exist.", 
                                     errorCode, invalidResource);
        }
        return "";
    }
    
}
