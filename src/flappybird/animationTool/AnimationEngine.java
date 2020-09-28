/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidecolombo
 */
class AnimationEngine extends Thread {
    
    private long delayBetweenFrames;
    private int currentFrame = 0;
    private int numberOfFrames;
    private EngineMonitor monitor;
    
    AnimationEngine(long delay, int numberOfFrames){
        this.monitor = new EngineMonitor();
        this.delayBetweenFrames = delay;
        this.numberOfFrames = numberOfFrames;
    }
    
    void pauseEngine(){
        monitor.setState(EngineMonitor.EngineState.PAUSED);
    }
    
    void stopEngine(){
        monitor.setState(EngineMonitor.EngineState.STOPPED);
    }
    
    void startEngine(){
        start();
    }
    
    void resumeEngine(){
        monitor.setState(EngineMonitor.EngineState.RUNNING);
    }
    
    @Override
    public void run(){
        
        while(monitor.isAlive()){
            try{
                cycleThroughAnimation();
                Thread.sleep(delayBetweenFrames);
            }catch(InterruptedException ex){
                // do nothing
            }
        }
        
    }
    
    private void cycleThroughAnimation(){
        currentFrame = (currentFrame + 1) % numberOfFrames;
    }
    
    synchronized int getCurrentFrameIndex(){
        while(monitor.isPaused()){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                // do nothing
            }
        }
        
        return this.currentFrame;
    }
    
}
