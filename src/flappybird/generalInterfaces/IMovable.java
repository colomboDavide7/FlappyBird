/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.generalInterfaces;

/**
 *
 * @author davidecolombo
 */
public interface IMovable {
    
    public abstract void setXPositionInPixel(int x);
    
    public abstract void setYPositionInPixel(int y);
    
    public abstract int getXPositionInPixel();
    
    public abstract int getYPositionInPixel();
    
}
