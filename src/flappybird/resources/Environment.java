/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Environment implements IEnvironment {
    
    private AvailableEnvironment type;
    private Image background;
    private List<IPowerUp> powerUp;
          
    public Environment(List<IPowerUp> powerUp, Image background, AvailableEnvironment type){
        this.background = background;
        this.type = type;
        this.powerUp = powerUp;
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(this.background, 0, 0, null);
        
        for(IPowerUp p : powerUp)
            p.draw(g);
    }

    @Override
    public void update() {
        for(IPowerUp p : powerUp)
            p.update(this.background.getWidth(null));
    }

    @Override
    public boolean matchType(AvailableEnvironment type) {
        return this.type == type;
    }

    @Override
    public Image getBackgroundImage() {
        return this.background;
    }
    
}
