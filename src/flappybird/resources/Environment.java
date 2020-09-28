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
    private List<IPowerUp> powerUps;
    
    public Environment(List<IPowerUp> powerUps, Image background, AvailableEnvironment type){
        this.powerUps = powerUps;
        this.background = background;
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(this.background, 0, 0, null);
        
        for(IPowerUp p : powerUps)
            p.draw(g);
    }

    @Override
    public void update() {
        for(IPowerUp p : powerUps)
            p.update();
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
