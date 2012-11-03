/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superlines.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import superlines.client.Context;
import superlines.client.SuperlinesController;
import superlines.client.SuperlinesListener;

import superlines.client.util.SuperlinesHelper;
import superlines.core.SuperlinesBall.State;
import superlines.core.SuperlinesContext;
import superlines.core.User;

/**
 *
 * @author Sashonk
 */
public class PlayPanel extends javax.swing.JPanel implements SuperlinesListener{

	private SuperlinesController m_controller;
	private final SuperlinesPanel m_superPanel; 
    /**
     * Creates new form PlayPanel
     */
    public PlayPanel() {
        initComponents();
        
        JPanel leftPanel = new JPanel();        
        middlePanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setPreferredSize(new Dimension(150, 100));
        leftPanel.setBackground(Color.GREEN);
        

        JPanel rightPanel = new JPanel();
        middlePanel.add(rightPanel, BorderLayout.EAST);
        rightPanel.setPreferredSize(new Dimension(150, 100));
        rightPanel.setBackground(Color.BLACK);
        
        
        m_superPanel = new SuperlinesPanel();
        middlePanel.add(m_superPanel,BorderLayout.CENTER);
        m_superPanel.setPreferredSize(new Dimension(200, 100));
        m_superPanel.setBackground(Color.CYAN);
        
 
   
    }
    
    public void setController(final SuperlinesController ctr){
        m_controller = ctr;
        m_superPanel.setController(ctr);
        ((TipPanel)tipPanel).setController(ctr);
    }
    
    @Override
    public void nextColorsChanged(final List<Integer> newColors){
    	TipPanel panel = (TipPanel)tipPanel;
    	panel.updatePanels(newColors);
    }
    
	@Override
	public void clickedBallUnset(int x, int y) {
		m_superPanel.getSpots()[x][y].setClicked(false);	
		
	}
    
	@Override
	public void clickeBallSet(int newx, int newy) {
		m_superPanel.getSpots()[newx][newy].setClicked(true);	
		
	}
    
	@Override
	public void clickedBallChanged(int newx, int newy, int oldx, int oldy) {
		m_superPanel.getSpots()[oldx][oldy].setClicked(false);
		m_superPanel.getSpots()[newx][newy].setClicked(true);				
	}
    
	@Override
	public void ballChangeColor(int x, int y, int newCol, int oldCol) {
		m_superPanel.getSpots()[x][y].setBackground(SuperlinesHelper.number2Color(newCol));
		
	}

	@Override
	public void ballChangeState(int x, int y, State newState, State oldState) {
		m_superPanel.getSpots()[x][y].setClicked(newState==State.CLICKED ? true : false);		
	}
    
	@Override
	public void scoreChanged(int newScore, int oldScore) {
		scoreField.setText(Integer.valueOf(newScore).toString());
	}
        
     @Override
    public void tableFilled() {
    	 scatterButton.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Game over!");
    }
    
    public void init(){
            Context ctx = Context.get();
            User profile = ctx.getUser();
            SuperlinesContext c = m_controller.getContext();
        
    	playerNameField.setText(profile.getUsername());
    	
    	if(ctx!=null){
    		scoreField.setText(Integer.valueOf(c.getScore()).toString());
    	}
    	if(ctx!=null){
    		maxScoreField.setText(Integer.valueOf(0).toString());
    	}
    	
    	m_superPanel.init();
        ((TipPanel)tipPanel).init();
        if(!c.getRules().isShowTip()){
        	tipPanel.setVisible(false);
        }
        
        //m_controller.scatter();
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
        tipPanel = new TipPanel();
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
        playerNameField.setPreferredSize(null);
        topPanel.add(playerNameField);

        scoreLabel.setText("jLabel1");
        topPanel.add(scoreLabel);

        scoreField.setEditable(false);
        scoreField.setText("1111");
        scoreField.setPreferredSize(new java.awt.Dimension(60, 20));
        topPanel.add(scoreField);

        maxScoreLabel.setText("jLabel2");
        topPanel.add(maxScoreLabel);

        maxScoreField.setEditable(false);
        maxScoreField.setText("12000");
        maxScoreField.setPreferredSize(new java.awt.Dimension(60, 20));
        topPanel.add(maxScoreField);

        add(topPanel);
        add(tipPanel);

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
        m_controller.scatter();
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
    private javax.swing.JPanel tipPanel;
    private javax.swing.JButton toScoreBtn;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables







}
