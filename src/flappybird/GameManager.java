/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import flappybird.resources.AnimationType;
import flappybird.engine.Clock;
import flappybird.players.Bird;
import flappybird.resources.Environment;
import flappybird.players.IPlayer;
import flappybird.generalInterfaces.IUpdatable;
import flappybird.resources.LoadException;
import flappybird.resources.ResourceManager;
import flappybird.view.Display;
import flappybird.view.Screen;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author davidecolombo
 */
public class GameManager implements Observer, KeyListener {
    
    public static void main(String[] args) {
        String propertyFile = args[0];
        
        try {
            new GameManager().initialize(propertyFile);
        } catch (LoadException ex) {
            System.out.println(ex.errorMessage());
            System.exit(1);
        }
     
        System.out.println("exit the main");
    }
    
    private Clock clock;
    private ResourceManager resManager;
    private Screen frame;
    private Display disp;
    private GameBoard board;
    
    void initialize(String propertyFile) throws LoadException{
        // Resources
        this.resManager = ResourceManager.getInstance();
        this.resManager.loadResources(propertyFile);
        
        IUpdatable currentEnvironment = resManager.getLevel("easy");
        IUpdatable currentPlayer      = resManager.getPlayerByType("bird");
        
        // Board
        this.board = new GameBoard();
        this.board.setPlayer(currentPlayer);
        this.board.setEnvironment(currentEnvironment);
        
        // Configuration
        Environment env = (Environment) currentEnvironment;
        int widthInPixel = env.getWidthInPixel();
        int heightInPixel = env.getHeightInPixel();
        
        IPlayer ply = (IPlayer) currentPlayer;
        ply.setLocation(widthInPixel / 2, heightInPixel / 2);
        
        // Screen
        this.frame = new Screen(widthInPixel, heightInPixel);
        this.disp  = frame.getDisplay();
        
        // Display
        this.disp.setFocusable(true);
        this.disp.addKeyListener(this);
        
        // Setting the dependencies
        this.disp.setBoard(board);
        this.board.setDisplay(disp);
        
        // Clock
        this.clock = Clock.getInstance();
        this.clock.setFPS(30);
        this.clock.addObserver(this);
        this.clock.start();
    }
    
    @Override
    public void update(Observable o, Object arg) {
        EventObject evt = (EventObject) arg; 
        
        if(evt instanceof Clock.TimerTickEvent){
            this.board.updateEnvironment();
            this.board.timerTick();
        }
    }

    // Updating player's animation
    @Override
    public void keyTyped(KeyEvent e) {
        // not relevant
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
                
            case KeyEvent.VK_SPACE:
                board.updatePlayer(AnimationType.fly_right);
                board.jump();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
                
            case KeyEvent.VK_SPACE:
                board.updatePlayer(AnimationType.stay);
                board.fall();
                break;
        }
    }
    
}
