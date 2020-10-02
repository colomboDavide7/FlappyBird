/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import flappybird.resources.AnimationType;
import flappybird.players.IPlayer;
import flappybird.generalInterfaces.IRenderable;
import flappybird.generalInterfaces.IUpdatable;
import flappybird.view.Display;
import java.awt.Graphics;

/**
 *
 * @author davidecolombo
 */
public class GameBoard {
    
    private Display disp;
    private IPlayer animatedPlayer;
    private IUpdatable player;
    private IRenderable renderablePlayer;
    private IUpdatable environment;
    private IRenderable renderableEnvironment;
    
    void setPlayer(IUpdatable player){
        this.player = player;
        this.renderablePlayer = (IRenderable) player;
        this.animatedPlayer = (IPlayer) player;
    }
    
    void setEnvironment(IUpdatable env){
        this.environment = env;
        this.renderableEnvironment = (IRenderable) env;
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
    
    void updatePlayer(AnimationType type){
        animatedPlayer.updateAnimation(type);
    }
    
    void jump(){
        this.animatedPlayer.jump();
    }
    
    void fall(){
        this.animatedPlayer.fall();
    }
    
    void updateEnvironment(){
        this.environment.update();
        this.player.update();
    }
    
    public void drawBoard(Graphics g){
        this.renderableEnvironment.draw(g);
        this.renderablePlayer.draw(g);
    }
    
}
