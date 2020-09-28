/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import flappybird.resources.AnimationType;
import flappybird.resources.ICreature;
import flappybird.view.Display;
import java.awt.Graphics;

/**
 *
 * @author davidecolombo
 */
public class GameBoard {
    
    private Display disp;
    private ICreature player;
    
    void setPlayer(ICreature player){
        this.player = player;
    }
    
    void setDisplay(Display disp){
        this.disp = disp;
    }
    
    void timerTick(){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                disp.repaint();
            }
        });
    }
    
    void update(AnimationType type){
        player.updateAnimation(type);
    }
    
    public void drawBoard(Graphics g){
        // TODO: draw environment
        
        player.draw(g);
    }
    
    
    
}
