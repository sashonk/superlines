/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superlines.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesController;
import superlines.core.SuperlinesListener;

/**
 *
 * @author Sashonk
 */
public class PlayPanel extends javax.swing.JPanel implements SuperlinesListener{

	private final SuperlinesController m_controller;
    /**
     * Creates new form PlayPanel
     */
    public PlayPanel(final SuperlinesController ctr) {
        initComponents();
        
        JPanel leftPanel = new JPanel();        
        middlePanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setPreferredSize(new Dimension(150, 100));
        leftPanel.setBackground(Color.GREEN);
        
        
        
        JPanel rightPanel = new JPanel();
        middlePanel.add(rightPanel, BorderLayout.EAST);
        rightPanel.setPreferredSize(new Dimension(150, 100));
        rightPanel.setBackground(Color.BLACK);
        
        
        SuperlinesPanel slPanel = new SuperlinesPanel();
        middlePanel.add(slPanel,BorderLayout.CENTER);
        slPanel.setPreferredSize(new Dimension(200, 100));
        slPanel.setBackground(Color.CYAN);
        
        m_controller= ctr;
        
   
    }
    
    public void init(final SuperlinesContext ctx){
    	
    }
    
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        playerNameField = new javax.swing.JTextField();
        scoreLabel = new javax.swing.JLabel();
        scoreField = new javax.swing.JTextField();
        maxScoreLabel = new javax.swing.JLabel();
        maxScoreField = new javax.swing.JTextField();
        middlePanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        toScoreBtn = new javax.swing.JButton();
        scatterButton = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        topPanel.setBackground(new java.awt.Color(255, 204, 204));
        topPanel.setMaximumSize(new java.awt.Dimension(32767, 80));
        topPanel.setName(""); // NOI18N
        topPanel.setPreferredSize(new java.awt.Dimension(495, 60));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        topPanel.setLayout(flowLayout1);

        playerNameField.setEditable(false);
        playerNameField.setText("player");
        playerNameField.setToolTipText("player name");
        topPanel.add(playerNameField);

        scoreLabel.setText("jLabel1");
        topPanel.add(scoreLabel);

        scoreField.setEditable(false);
        scoreField.setText("1111");
        topPanel.add(scoreField);

        maxScoreLabel.setText("jLabel2");
        topPanel.add(maxScoreLabel);

        maxScoreField.setEditable(false);
        maxScoreField.setText("12000");
        topPanel.add(maxScoreField);

        add(topPanel);

        middlePanel.setBackground(new java.awt.Color(204, 255, 204));
        middlePanel.setLayout(new java.awt.BorderLayout());
        add(middlePanel);

        bottomPanel.setBackground(new java.awt.Color(204, 204, 255));
        bottomPanel.setMaximumSize(new java.awt.Dimension(32767, 100));
        bottomPanel.setPreferredSize(new java.awt.Dimension(495, 80));

        toScoreBtn.setText("toScore");
        toScoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toScoreBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(toScoreBtn);

        scatterButton.setText("scatter");
        scatterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scatterButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(scatterButton);

        add(bottomPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void scatterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scatterButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scatterButtonActionPerformed

    private void toScoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toScoreBtnActionPerformed
        MainFrame.get().showScorePanel();        // TODO add your handling code here:
    }//GEN-LAST:event_toScoreBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JTextField maxScoreField;
    private javax.swing.JLabel maxScoreLabel;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JTextField playerNameField;
    private javax.swing.JButton scatterButton;
    private javax.swing.JTextField scoreField;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton toScoreBtn;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
