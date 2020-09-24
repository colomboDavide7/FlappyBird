/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.imageTool;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 *
 * @author davidecolombo
 */
public class ImageResizer implements ImageToolIF {
    
    private Image toEdit;
    private int widthInPixel;
    private int heightInPixel;
    
    private boolean widthProvided = false;
    private boolean heightProvided = false;
    private boolean imageProvided = false;
        
    @Override
    public void setImageWidthInPixel(int width) {
        this.widthInPixel  = width;
        this.widthProvided = true;
    }

    @Override
    public void setImageHeightInPixel(int height) {
        this.heightInPixel  = height;
        this.heightProvided = true;
    }

    @Override
    public void setImageToEdit(Image toEdit) {
        this.toEdit = toEdit;
        this.imageProvided = true;
    }
    
    @Override
    public Image resize() throws ImageToolException {
            checkBeforeResize();
            BufferedImage resized = new BufferedImage(widthInPixel, 
                                                      heightInPixel, 
                                                      BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resized.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(toEdit, 0, 0, widthInPixel, heightInPixel, null);
            g2.dispose();
            return resized;
    }
    
    private void checkBeforeResize() throws ImageToolException {
        
        if(!imageProvided)
            throw new ImageToolException(ImageToolException.ErrorCode.MISSING_IMAGE_TO_RESIZE);
        
        if(!widthProvided)
            throw new ImageToolException(ImageToolException.ErrorCode.MISSING_WIDTH);
                    
        if(!heightProvided)
            throw new ImageToolException(ImageToolException.ErrorCode.MISSING_HEIGHT);
    }
    
}
