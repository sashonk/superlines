/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superlines.client.ui;

import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author Sashonk
 */
public class MainFrame extends javax.swing.JFrame {

    private static MainFrame instance ;
    /**
     * Creates new form MainFrame
     */
     MainFrame() {
        initComponents();
    }
    
    
    public static MainFrame get(){
        if(instance==null){
            instance = new MainFrame();
        }
        
        return instance;
    }
    
    
    public void showScorePanel(){
        showPanel(ScorePanel.class);
    }
     public void showPlayPanel(){
        showPanel(PlayPanel.class);
    }   

    
    void showPanel(Class cls){
        for(Component c : getContentPane().getComponents()){
            if(c.getClass()==cls){
                c.setVisible(true);                
            }
            else{
                c.setVisible(false);
            }
        }
        
    }
    
    void shutdown(){
        System.exit(0);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
