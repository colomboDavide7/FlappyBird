/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.resources;

/**
 *
 * @author davidecolombo
 */
public class Wall extends PowerUp {

    public Wall(Animation initA) {
        super(initA);
    }

    @Override
    public void powerUp(Creature creature) {
        // Azzero la vita della creatura
    }
    
}
