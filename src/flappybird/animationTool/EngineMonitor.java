/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.animationTool;

/**
 *
 * @author davidecolombo
 */
class EngineMonitor {
    
    static enum EngineState{
        STARTED, RUNNING, PAUSED, STOPPED;
    }
    
    private EngineState currentState = EngineState.STARTED;
    
    synchronized boolean isAlive(){
        return currentState != EngineState.STOPPED;
    }
    
    synchronized boolean isPaused(){
        return this.currentState == EngineState.PAUSED;
    }
    
    synchronized void setState(EngineState newState) throws IllegalStateException {
        switch(newState){
            case PAUSED:
                if(currentState == EngineState.RUNNING || currentState == EngineState.STARTED){
                    currentState = newState;
                }else{
                    throw new IllegalStateException(
                            "Transition from " + currentState.name() 
                             + " to " + newState.name() + " is not allowed!\n"
                    );
                }
            case RUNNING:
                if(currentState == EngineState.PAUSED || currentState == EngineState.STARTED){
                    currentState = newState;
                }else{
                    throw new IllegalStateException(
                            "Transition from " + currentState.name() 
                             + " to " + newState.name() + " is not allowed!\n"
                    );
                }
        }
    }
    
}
