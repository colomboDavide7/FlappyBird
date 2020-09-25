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
        MISSING_SPRITE_SHEET,
        INCOMPATIBLE_SHEET_PROPERTIES;
    }
    
    private ErrorCode errorCode;
    private String errorMessage;
    
    public AnimationToolException(String msg){
        this.errorMessage = msg;
    }
    
    public AnimationToolException(ErrorCode code) {
        this.errorCode = code;
    }

    public String errorMessage(){
        switch(errorCode){
            case MISSING_SPRITE_SHEET:
                return String.format("%s error code has occured."
                                   + "You must provide the sprite sheet.", this.errorCode);
            case INCOMPATIBLE_SHEET_PROPERTIES:
                
        }
        return "";
    }
    
}
