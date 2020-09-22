/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

import flappybird.resources.LoadException;
import flappybird.resources.ResourceManager;

/**
 *
 * @author davidecolombo
 */
public class Main {
    
    public static void main(String[] args){
        String resourceFile = args[0];
        ResourceManager resManager = ResourceManager.getInstance();
        
        try{
            resManager.loadResources(resourceFile);
        }catch(LoadException ex){
            try {
                System.err.println(ex.errorMessage());
                System.exit(1);
            } catch (Exception ex1) {
                System.err.println(ex1.getMessage());
                System.exit(1);
            }
        }
        
        System.out.println("exit the main");
        
    }
    
}
