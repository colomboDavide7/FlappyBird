/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import flappybird.generalInterfaces.IUpdatable;
import flappybird.generalInterfaces.IRenderable;
import flappybird.generalInterfaces.ICloneable;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Environment implements IUpdatable, IRenderable, ICloneable {
    
    private IAvailable personality;
    private Image background;
    private List<IUpdatable> updatable;
          
    public Environment(List<IUpdatable> updatable, Image background, IAvailable pers){
        this.background = background;
        this.personality = pers;
        this.updatable = updatable;
    }
    
    @Override
    public void draw(Graphics g) {
        g.drawImage(this.background, 0, 0, null);
        
        for (Iterator<IUpdatable> it = updatable.iterator(); it.hasNext();) {
            IRenderable p = (IRenderable) it.next();
            p.draw(g);
        }
    }

    @Override
    public void update() {
        for(IUpdatable p : updatable)
            p.update();
    }
    
    public int getWidthInPixel(){
        return this.background.getWidth(null);
    }
    
    public int getHeightInPixel(){
        return this.background.getHeight(null);
    }

    @Override
    public ICloneable clone() {
        // for now do nothing
        return null;
    }

    @Override
    public boolean matchPersonality(String pers) {
        return pers.equals(this.personality.getMyPersonality());
    }
    
}
