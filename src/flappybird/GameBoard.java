/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import flappybird.animationTool.AnimationType;
import flappybird.environment.IEnvironment;
import flappybird.generalInterfaces.IRenderable;
import flappybird.generalInterfaces.IUpdatable;
import flappybird.players.IPlayer;
import flappybird.view.Display;
import java.awt.Graphics;
import java.util.EventObject;
import java.util.Observable;

/**
 *
 * @author davidecolombo
 */
public class GameBoard extends Observable implements IUpdatable, IRenderable {
    
    public class GameOverEvent extends EventObject{

        public GameOverEvent(Object source) {
            super(source);
        }

    }
    
    private Display disp;
    private IPlayer player;
    private IEnvironment env;
    
    @Override
    public void update(){
        this.env.update();
        this.player.update();
        this.env.checkCollision(player);
        
        if(!player.isAlive())
            gameOver();
    }

    @Override
    public void draw(Graphics g) {
        this.env.draw(g);
        this.player.draw(g);
    }
    
    void setPlayer(IPlayer player){
        this.player = player;
    }
    
    void setEnvironment(IEnvironment env){
        this.env = env;
    }
    
    IEnvironment getCurrentEnvironment(){
        return this.env;
    }
    
    IPlayer getCurrentPlayer(){
        return this.player;
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
        player.updateAnimation(type);
    }
    
    void jump(){
        this.player.jump();
    }
    
    void fall(){
        this.player.fall();
    }
    
    private void gameOver(){
        this.setChanged();
        this.notifyObservers(new GameOverEvent(this));
    }
    
}
