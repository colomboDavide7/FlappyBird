/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

import java.awt.image.BufferedImage;

/**
 *
 * @author davidecolombo
 */
public interface IAnimationTool {
    
    public abstract void setSpriteSheet(BufferedImage img);
    
    public abstract void setSpriteSheetProperties(int nRow, int nCol, int nFrame);
    
    public abstract void setHorizontalSpaces(int beginSpace, int betweenSpace, int endspace);
    
    public abstract void setVerticalSpaces(int beginSpace, int betweenSpace, int endspace);
    
    public abstract void setSpriteDimensions(int w, int h);
        
    public abstract Animation build() throws AnimationToolException;
    
}
