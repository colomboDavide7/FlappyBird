/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.view;

import flappybird.GameBoard;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author davidecolombo
 */
public class Display extends JPanel {
    
    private GameBoard board;
            
    public Display(int w, int h){
        initDisplay(w, h);
    }
    
    private void initDisplay(int w, int h){
        setLayout(null);
        setName("");
        setOpaque(false);
        setMinimumSize(new Dimension(w, h));
        setPreferredSize(new Dimension(w, h));
        setMaximumSize(new Dimension(w, h));
    }
    
    public void setBoard(GameBoard board){
        this.board = board;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(board != null)
            board.draw(g);
    }
    
}
