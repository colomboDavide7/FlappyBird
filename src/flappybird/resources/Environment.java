/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davidecolombo
 */
public class Environment {
    
    private List<PowerUp> powerUps;
    private List<Creature> enemies;
    private Creature player;
    private Image background;
    
    public Environment(Image background){
        powerUps = new ArrayList<>();
        this.background = background;
    }
    
    public Image getBackground(){
        return this.background;
    }
    
    void setPlayer(Creature player){
        this.player = player;
    }
    
    void addBoundary(PowerUp boundary){
        this.powerUps.add(boundary);
    }
    
//    @Override
//    public String toString(){
//        return properties.toString();
//    }
    
}
