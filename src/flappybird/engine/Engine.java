/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.engine;

import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidecolombo
 */
class Engine extends Thread {
    
    private Clock clock;
    private int fps;
    
    Engine(Clock clock, int fps){
        this.clock = clock;
        this.fps = fps;
    }
    
    void startEngine(){
        this.start();
    }
    
    @Override
    public void run(){
        while(true){
            cycle();
        }
    }
    
    private void cycle(){
        try {
            timerTick();
            Thread.sleep(1000 / fps);
        } catch (InterruptedException ex) {
             // Interrupted   
        }
    }
    
    private void timerTick(){
        this.clock.tick();
    }
    
}
