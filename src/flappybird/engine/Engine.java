/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.engine;

/**
 *
 * @author davidecolombo
 */
class Engine extends Thread {
    
    private Clock clock;
    private int fps;
    private boolean gameOver = false;
    
    Engine(Clock clock, int fps){
        this.clock = clock;
        this.fps = fps;
    }
    
    void startEngine(){
        this.start();
    }
    
    void stopEngine(){
        gameOver = true;
    }
    
    @Override
    public void run(){
        while(!gameOver){
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
