/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author davidecolombo
 */
public class GameSettingPanel extends JPanel {
    
    private List<JButton> buttons;
    
    public GameSettingPanel(){
        buttons = new ArrayList<>();
        initPanel();
    }
    
    private void initPanel(){
        setLayout(new BorderLayout());
        setName("");
        setOpaque(false);
        setPreferredSize(new Dimension(500, 500));
        setMinimumSize(new Dimension(500, 500));
        setMaximumSize(new Dimension(500, 500));
        
        
    }
    
    
}
