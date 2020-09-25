/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.imageTool;

import java.awt.Image;

/**
 *
 * @author davidecolombo
 */
public interface IImageTool {
    
    public abstract Image resize() throws ImageToolException;
    
    public abstract void setImageWidthInPixel(int width);
    
    public abstract void setImageHeightInPixel(int height);
    
    public abstract void setImageToEdit(Image toEdit);
    
}
