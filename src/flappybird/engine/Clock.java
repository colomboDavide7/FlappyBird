/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.engine;

import java.util.EventObject;
import java.util.Observable;

/**
 *
 * @author davidecolombo
 */
public class Clock extends Observable {
    
    // SINGLETON
    private static Clock theInstance = null;
    
    private Clock(){
    }
    
    public static synchronized Clock getInstance(){
        if(theInstance == null)
            theInstance = new Clock();
        return theInstance;
    }
    
    private Engine engine = null;
    private int fps;
    
    public void setFPS(int fps){
        this.fps = fps;
    }
    
    public void start(){
        if(engine == null)
            this.engine = new Engine(this, fps);
        engine.startEngine();
    }
    
    void tick(){
        this.setChanged();
        this.notifyObservers(new TimerTickEvent(this));
    }
    
    public class TimerTickEvent extends EventObject {
        
        public TimerTickEvent(Object source) {
            super(source);
        }
        
    }
    
}
