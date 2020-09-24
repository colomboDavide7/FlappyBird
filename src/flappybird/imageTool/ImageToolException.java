/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.imageTool;

/**
 *
 * @author davidecolombo
 */
public class ImageToolException extends Exception {

    public enum ErrorCode{
        OK, MISSING_WIDTH, MISSING_HEIGHT, MISSING_IMAGE_TO_RESIZE;
    }
    
    private ErrorCode errorCode;
    
    public ImageToolException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return this.errorCode;
    }
    
    public String errorMessage() {
        switch(this.errorCode){
            case MISSING_WIDTH:
                return String.format("%s error code has occured." 
                                   + "\nYou must specify the desired width.", errorCode);
            case MISSING_HEIGHT:
                return String.format("%s error code has occured." 
                                   + "\nYou must specify the desired height.", errorCode);
            case MISSING_IMAGE_TO_RESIZE:
                return String.format("%s error code has occured." 
                                   + "\nYou must provide an image to be edited.", errorCode);
        }
        return "";
    }
    
    
}
