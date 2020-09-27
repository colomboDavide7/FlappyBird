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
            
    public Display(){
        initDisplay();
    }
    
    private void initDisplay(){
        setLayout(null);
        setName("");
        setOpaque(false);
        setMinimumSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
    }
    
    public void setBoard(GameBoard board){
        this.board = board;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(board != null)
            board.drawBoard(g);
    }
    
}
