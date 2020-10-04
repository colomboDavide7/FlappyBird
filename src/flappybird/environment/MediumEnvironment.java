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
public class MediumEnvironment extends Environment {

    public MediumEnvironment(AvailableEnvironment pers, BufferedImage bck, List<IPowerUp> powerUp) {
        super(pers, bck, powerUp);
    }
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean matchPersonality(String pers) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void checkCollision(IPlayer player) {
        for(IPowerUp pw : powerUp)
            pw.powerUp(player);
    }
    
}
