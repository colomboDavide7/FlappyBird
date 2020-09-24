/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import flappybird.resources.ResourceManager;

/**
 *
 * @author davidecolombo
 */
public class Main {
    
    public static void main(String[] args) {
        String propertyFile = args[0];
        
        ResourceManager resManager = ResourceManager.getInstance();
        resManager.loadResources(propertyFile);
        System.out.println("exit the main");
        
    }
    
}
