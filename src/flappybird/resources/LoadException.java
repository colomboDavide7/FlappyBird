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
    
    public enum ErrorCode{
        OK, MULTIPLE_LOADING, RESOURCE_NOT_FOUND, TEXT_FILE_ERROR;
    }
    
    private ErrorCode errorCode = ErrorCode.OK;
    private String invalidResource = "";
    
    public LoadException(ErrorCode errorCode){
        this.errorCode = errorCode;
    }
    
    public LoadException(ErrorCode errorCode, String invalidResource){
        this.errorCode = errorCode;
        this.invalidResource = invalidResource;
    }
    
    public String errorMessage() throws Exception{
        switch(errorCode){
            case OK: 
                throw new Exception("ERROR: Should not get here!");
            case MULTIPLE_LOADING:
                return String.format("%s error code has occured.\n"
                                   + "You can't load the resources multiple times", 
                                     errorCode);
            case RESOURCE_NOT_FOUND: 
                return String.format("%s error code has occured.\n"
                                   + "The resource correspond to '%s' is invalid.", 
                                     errorCode, invalidResource);
            case TEXT_FILE_ERROR: 
                return String.format("%s error code has occured.\n"
                                   + "Error reading '%s' file.", 
                                     errorCode, invalidResource);
        }
        return "";
    }
    
}
