/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class AnimationBuilder implements IAnimationTool {

    // Default values
    private final int DEFAULT_ROWS    = 1;
    private final int DEFAULT_COLS    = 1;
    private final int DEFAULT_N_FRAME = 1;
    private final int DEFAULT_HSPACE_BETWEEN = 15;
    private final int DEFAULT_HSPACE_BEGIN   = 0;
    private final int DEFAULT_HSPACE_END     = 0;
    private final int DEFAULT_VSPACE_BETWEEN = 15;
    private final int DEFAULT_VSPACE_BEGIN   = 0;
    private final int DEFAULT_VSPACE_END     = 0;
    private final int DEFAULT_SPRITE_WIDTH   = 32;
    private final int DEFAULT_SPRITE_HEIGHT  = 32;
    
// ================================================================
    private int nCols = DEFAULT_COLS;
    private int nRows = DEFAULT_ROWS;
    private int numberOfFrame   = DEFAULT_N_FRAME;
    private int hSpaceBetween   = DEFAULT_HSPACE_BETWEEN;
    private int hSpaceBegin     = DEFAULT_HSPACE_BEGIN;
    private int hSpaceEnd       = DEFAULT_HSPACE_END;
    private int vSpaceBetween   = DEFAULT_VSPACE_BETWEEN;
    private int vSpaceBegin     = DEFAULT_VSPACE_BEGIN;
    private int vSpaceEnd       = DEFAULT_VSPACE_END;
    private int spriteWidth     = DEFAULT_SPRITE_WIDTH;
    private int spriteHeight    = DEFAULT_SPRITE_HEIGHT;
    private BufferedImage spriteSheet = null;
    private int x = 0, y = 0;
    
    @Override
    public void setSpriteSheet(BufferedImage img) {
        this.spriteSheet = img;
    }
    
    @Override
    public void setSpriteSheetProperties(int nRow, int nCol, int nFrame) {
        this.nRows = nRow;
        this.nCols = nCol;
        this.numberOfFrame = nFrame;
    }

    @Override
    public void setHorizontalSpaces(int beginSpace, int betweenSpace, int endspace) {
        this.hSpaceBegin   = beginSpace;
        this.hSpaceBetween = betweenSpace;
        this.hSpaceEnd     = endspace;
    }

    @Override
    public void setVerticalSpaces(int beginSpace, int betweenSpace, int endspace) {
        this.vSpaceBegin   = beginSpace;
        this.vSpaceBetween = betweenSpace;
        this.vSpaceEnd     = endspace;
    }

    @Override
    public void setSpriteDimensions(int w, int h) {
        this.spriteWidth  = w;
        this.spriteHeight = h;
    }
    
// ================================================================
    @Override
    public Animation build() throws AnimationToolException {
        
        checkBuilderParameters();
        
        List<Image> sprites = new ArrayList<>(numberOfFrame);
        for(int frame = 0; frame < this.numberOfFrame; frame++){
            sprites.add(extractSprite());
        }
        
        return new Animation(sprites);
    }

    private void checkBuilderParameters() throws AnimationToolException {
        if(numberOfFrame > nCols*nRows || numberOfFrame == 0)
            throw new AnimationToolException(AnimationToolException.ErrorCode.INCOMPATIBLE_SHEET_PROPERTIES);
        else if(spriteSheet == null)
            throw new AnimationToolException(AnimationToolException.ErrorCode.MISSING_SPRITE_SHEET);
    }
    
    private Image extractSprite()  {
        Image sprite = spriteSheet.getSubimage(x + hSpaceBegin, 
                                               y + vSpaceBegin, 
                                               spriteWidth, 
                                               spriteHeight);
        x += (spriteWidth + hSpaceBetween);
        if(x >= spriteSheet.getWidth() - hSpaceEnd)
            y += (spriteHeight + vSpaceBetween);
        return sprite;
    }
    
}
