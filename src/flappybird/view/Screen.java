/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.view;

import java.awt.Color;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author davidecolombo
 */
public class Screen extends JFrame {
    
    private Display display;
    
    public Screen(){
        initializeScreen();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setTitle("The FlappyBird Game");
                pack();
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
            }
        });
    }
    
    private void initializeScreen(){
        this.display = new Display();
        
        this.setBackground(Color.BLACK);
        this.getContentPane().add(this.display);
    }
    
    public Display getDisplay(){
        return this.display;
    }
    
}
