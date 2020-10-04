/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.environment;

import flappybird.powerUp.IPowerUp;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public abstract class Environment implements IEnvironment {
    
    protected List<IPowerUp> powerUp;
//    protected List<IEnemies> enemies;
    protected BufferedImage background;
    protected AvailableEnvironment personality;
    
    public Environment(AvailableEnvironment pers, BufferedImage bck, List<IPowerUp> powerUp){
        this.powerUp = powerUp;
        this.background = bck;
        this.personality = pers;
    }
    
    public int getHeightInPixel(){
        return this.background.getHeight();
    }
    
    public int getWidthInPixel(){
        return this.background.getWidth();
    }
    
}
