/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superlines.client.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import superlines.client.SuperlinesController;
import superlines.client.util.SuperlinesHelper;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;

/**
 *
 * @author Sashonk
 */
public class TipPanel extends JPanel{
    
    private SuperlinesController m_ctr;
    private int nextColorsCount = 0;

    private final static int SIZE = 40; 
    
    public void setController(final SuperlinesController ctr){
        m_ctr = ctr;
    }
    
    public void updatePanels(final List<Integer> data){
    	for(int i = 0; i<data.size();i++){
    		JPanel p = (JPanel) getComponent(i);
    		p.setPreferredSize(new Dimension(SIZE,SIZE));
    		p.setBackground(SuperlinesHelper.number2Color(data.get(i)));
    	}
    }
    
    public void init(){
        SuperlinesContext ctx = m_ctr.getContext();
        
        int count  = ctx.getNextColors().size();
        if(nextColorsCount != count){           
            removeAll();
            for(int i = 0;i<count; i++){
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(SIZE,SIZE));
                panel.setBackground(SuperlinesHelper.number2Color(ctx.getNextColors().get(i)));
                add(panel);
            }
            
            nextColorsCount = count;
        }
        
    }
}
