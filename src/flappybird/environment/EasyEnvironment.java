/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.environment;

import flappybird.players.IPlayer;
import flappybird.powerUp.IPowerUp;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class EasyEnvironment extends Environment {

    public EasyEnvironment(AvailableEnvironment pers, BufferedImage bck, List<IPowerUp> powerUp) {
        super(pers, bck, powerUp);
    }
    
    @Override
    public void update() {
        for(IPowerUp pw : super.powerUp)
            pw.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        
        for(IPowerUp pw : powerUp)
            pw.draw(g);
    }
    
    @Override
    public boolean matchPersonality(String pers) {
        return pers.equals(super.personality.name());
    }

    @Override
    public void checkCollision(IPlayer player) {
        for(IPowerUp pw : super.powerUp)
            pw.powerUp(player);
    }
    
}
