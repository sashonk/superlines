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

import superlines.client.Messages;
import superlines.client.ProfileController;
import superlines.client.SuperlinesController;
import superlines.client.ColorHelper;

import superlines.client.ws.ServiceAdapter;
import superlines.core.Authentication;
import superlines.core.Localizer;
import superlines.core.ProfileListener;
import superlines.core.Rank;
import superlines.core.RulesHelper;
import superlines.core.SuperlinesListener;
import superlines.core.SuperlinesBall.State;
import superlines.core.SuperlinesContext;
import superlines.core.Profile;

/**
 *
 * @author Sashonk
 */
public class PlayPanel extends javax.swing.JPanel implements SuperlinesListener, ProfileListener{

	private SuperlinesController m_controller;
	private final SuperlinesPanel m_superPanel; 
	private  ProfileController m_profileCtr;

    /**
     * Creates new form PlayPanel
     */
    public PlayPanel() {
        initComponents();
        
        JPanel leftPanel = new JPanel();        
        middlePanel.add(leftPanel, BorderLayout.WEST);
        leftPanel.setPreferredSize(new Dimension(150, 100));
        leftPanel.setBackground(new Color(212,208, 200));
        

        JPanel rightPanel = new JPanel();
        middlePanel.add(rightPanel, BorderLayout.EAST);
        rightPanel.setPreferredSize(new Dimension(150, 100));
        rightPanel.setBackground(new Color(212,208, 200));
        
        
        m_superPanel = new SuperlinesPanel();
        middlePanel.add(m_superPanel,BorderLayout.CENTER);
        m_superPanel.setPreferredSize(new Dimension(200, 100));
        m_superPanel.setBackground(new Color(212,208, 200));
        
 
     
    }
    
    public void setController(final SuperlinesController ctr){
        m_controller = ctr;
        m_superPanel.setController(ctr);
        ((TipPanel)tipPanel).setController(ctr);
    }
    
    @Override
    public void progressiveOpened(final int level){
    	Messages msg = level ==1 ? Messages.PROGRESSIVE1_OPENED : Messages.PROGRESSIVE2_OPENED;
    	JOptionPane.showMessageDialog(this, msg);
    }
    
    @Override
    public void rateChanged(final int rate){
    	rateField.setText(Integer.toString(rate));
    }
    
    
	@Override
	public void nameChanged(String newName) {
		playerNameField.setText(newName);
		
	}

	@Override
	public void rankChanged(Rank newRank) {
		
		rankField.setText(newRank.toString());
		
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
		m_superPanel.getSpots()[x][y].setBackground(ColorHelper.number2Color(newCol));
		
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
    public void tableFilled(final int score) {
    	 scatterButton.setEnabled(false);
        //JOptionPane.showMessageDialog(this, Messages.GAME_OVER);
        
        //ServiceAdapter.get().getProfile(auth);
    }
     
     public void setController(final ProfileController ctr){
    	 m_profileCtr = ctr;
     }
     
     
    public void init(final Profile p){
    	playerNameField.setText(p.getUsername());
    	rankField.setText(p.getRank().toString());
    	rateField.setText(Integer.toString(p.getRate()));
    }
    
    @Override
    public void init(final SuperlinesContext ctx){

    	
    	if(ctx!=null){
    		scoreField.setText(Integer.valueOf(ctx.getScore()).toString());
    	}
    	
    	m_superPanel.init(ctx);
        ((TipPanel)tipPanel).init(ctx);
        if(!ctx.getRules().isShowTip()){
        	tipPanel.setVisible(false);
        }
        
        scatterButton.setEnabled(ctx.getTable().isFilled()? false : true);
        restartBtn.setEnabled(true);
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
        rankLabel = new javax.swing.JLabel();
        rankField = new javax.swing.JTextField();
        rateLabel = new javax.swing.JLabel();
        rateField = new javax.swing.JTextField();
        tipPanel = new TipPanel();
        scorePanel = new javax.swing.JPanel();
        scoreLabel = new javax.swing.JLabel();
        scoreField = new javax.swing.JTextField();
        middlePanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        toScoreBtn = new javax.swing.JButton();
        scatterButton = new javax.swing.JButton();
        restartBtn = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        topPanel.setMaximumSize(new java.awt.Dimension(32767, 80));
        topPanel.setName(""); // NOI18N
        topPanel.setPreferredSize(new java.awt.Dimension(495, 60));

        playerNameField.setEditable(false);
        playerNameField.setText("player");
        playerNameField.setToolTipText("player name");
        playerNameField.setPreferredSize(null);
        topPanel.add(playerNameField);

        rankLabel.setText(Messages.RANK_LABEL.toString());
        rankLabel.setPreferredSize(null);
        topPanel.add(rankLabel);

        rankField.setEditable(false);
        rankField.setText("p");
        rankField.setMinimumSize(null);
        rankField.setName(""); // NOI18N
        rankField.setPreferredSize(new java.awt.Dimension(100, 30));
        topPanel.add(rankField);

        rateLabel.setText(Messages.RATE_LABEL.toString());
        topPanel.add(rateLabel);

        rateField.setEditable(false);
        rateField.setPreferredSize(new java.awt.Dimension(80, 30));
        topPanel.add(rateField);

        add(topPanel);

        tipPanel.setBackground(new java.awt.Color(204, 204, 204));
        add(tipPanel);

        scoreLabel.setText(Messages.SCORE_LABEL.toString());
        scoreLabel.setPreferredSize(null);
        scorePanel.add(scoreLabel);

        scoreField.setEditable(false);
        scoreField.setText("p");
        scoreField.setPreferredSize(new java.awt.Dimension(60, 30));
        scorePanel.add(scoreField);

        add(scorePanel);

        middlePanel.setBackground(new java.awt.Color(204, 204, 204));
        middlePanel.setLayout(new java.awt.BorderLayout());
        add(middlePanel);

        bottomPanel.setMaximumSize(new java.awt.Dimension(32767, 100));
        bottomPanel.setPreferredSize(new java.awt.Dimension(495, 60));

        toScoreBtn.setText(Messages.TOSCORE.toString() );
        toScoreBtn.setPreferredSize(new java.awt.Dimension(80, 23));
        toScoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toScoreBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(toScoreBtn);

        scatterButton.setText(Messages.SCATTER.toString() );
        scatterButton.setPreferredSize(new java.awt.Dimension(80, 23));
        scatterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scatterButtonActionPerformed(evt);
            }
        });
        bottomPanel.add(scatterButton);

        restartBtn.setText(Messages.RESTART.toString() );
        restartBtn.setPreferredSize(new java.awt.Dimension(80, 23));
        restartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartBtnActionPerformed(evt);
            }
        });
        bottomPanel.add(restartBtn);

        add(bottomPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void scatterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scatterButtonActionPerformed
        m_controller.scatter();
    }//GEN-LAST:event_scatterButtonActionPerformed

    private void toScoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toScoreBtnActionPerformed
        MainFrame.get().showScorePanel();        // TODO add your handling code here:
    }//GEN-LAST:event_toScoreBtnActionPerformed

    private void restartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartBtnActionPerformed

    	
    	
    	m_controller.restart();

    }//GEN-LAST:event_restartBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JTextField playerNameField;
    private javax.swing.JTextField rankField;
    private javax.swing.JLabel rankLabel;
    private javax.swing.JTextField rateField;
    private javax.swing.JLabel rateLabel;
    private javax.swing.JButton restartBtn;
    private javax.swing.JButton scatterButton;
    private javax.swing.JTextField scoreField;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JPanel scorePanel;
    private javax.swing.JPanel tipPanel;
    private javax.swing.JButton toScoreBtn;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
	@Override
	public void postTableFilled() {
		// TODO Auto-generated method stub
		
	}









}
