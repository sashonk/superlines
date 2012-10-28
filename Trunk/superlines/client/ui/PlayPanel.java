/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superlines.client.ui;

/**
 *
 * @author Sashonk
 */
public class PlayPanel extends javax.swing.JPanel {

    /**
     * Creates new form PlayPanel
     */
    public PlayPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        playerNameField = new javax.swing.JTextField();
        scoreLabel = new javax.swing.JLabel();
        scoreField = new javax.swing.JTextField();
        maxScoreLabel = new javax.swing.JLabel();
        maxScoreField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        toScoreBtn = new javax.swing.JButton();
        scatterButton = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 80));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(495, 60));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        playerNameField.setEditable(false);
        playerNameField.setText("player");
        playerNameField.setToolTipText("player name");
        jPanel1.add(playerNameField);

        scoreLabel.setText("jLabel1");
        jPanel1.add(scoreLabel);

        scoreField.setEditable(false);
        scoreField.setText("1111");
        jPanel1.add(scoreField);

        maxScoreLabel.setText("jLabel2");
        jPanel1.add(maxScoreLabel);

        maxScoreField.setEditable(false);
        maxScoreField.setText("12000");
        jPanel1.add(maxScoreField);

        add(jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanel3.setPreferredSize(new java.awt.Dimension(495, 80));

        toScoreBtn.setText("toScore");
        toScoreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toScoreBtnActionPerformed(evt);
            }
        });
        jPanel3.add(toScoreBtn);

        scatterButton.setText("scatter");
        scatterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scatterButtonActionPerformed(evt);
            }
        });
        jPanel3.add(scatterButton);

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents

    private void scatterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scatterButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_scatterButtonActionPerformed

    private void toScoreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toScoreBtnActionPerformed
        MainFrame.get().showScorePanel();        // TODO add your handling code here:
    }//GEN-LAST:event_toScoreBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField maxScoreField;
    private javax.swing.JLabel maxScoreLabel;
    private javax.swing.JTextField playerNameField;
    private javax.swing.JButton scatterButton;
    private javax.swing.JTextField scoreField;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton toScoreBtn;
    // End of variables declaration//GEN-END:variables
}
