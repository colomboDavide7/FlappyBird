/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

/**
 *
 * @author davidecolombo
 */
public class AnimationToolException extends Exception {

    public enum ErrorCode{
        EXTERNAL_API_ERROR, IO_ERROR, INVALID_TYPE;
    }
    
    private ErrorCode errorCode;
    private String invalidResource = "";
    private String msg = "";
    
    public AnimationToolException(String msg){
        this.errorCode = ErrorCode.EXTERNAL_API_ERROR;
        this.msg = msg;
    }
    
    public AnimationToolException(ErrorCode code) {
        this.errorCode = code;
    }
    
    public AnimationToolException(ErrorCode code, String invalidReasource){
        this.errorCode = code;
        this.invalidResource = invalidReasource;
    }

    public String errorMessage(){
        switch(errorCode){
            case EXTERNAL_API_ERROR:
                return this.msg;
                
            case IO_ERROR:
                return String.format("%s error code has occured."
                                   + "\nAn error is occured while reading the file '%s'.", 
                                     this.errorCode, this.invalidResource);
            case INVALID_TYPE:
                return String.format("%s error code has occured."
                                   + "\nNo such animation type associated to '%s'.", 
                                     this.errorCode, this.invalidResource);
        }
        return "";
    }
    
}
