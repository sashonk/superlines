/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superlines.client.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import superlines.client.Messages;
import superlines.client.RateController;
import superlines.client.RateControllerImpl;
import superlines.client.RateListener;
import superlines.core.Util;
import superlines.ws.RateData;

/**
 *
 * @author Sashonk
 */
public class RatePanel extends javax.swing.JPanel implements RateListener{

	private static final Log log = LogFactory.getLog(RatePanel.class);
	private final StringBuilder m_template = new StringBuilder();
	private  RateController m_ctr;
	
	public void setController(final RateController ctr){
		m_ctr = ctr;
	}
	
    /**
     * Creates new form ScorePanel
     */
    public RatePanel() {
        initComponents();
        
        File folder = superlines.core.Configuration.get().getDataFolder();
        File templateFile = new File(folder, "score.html");
        
        BufferedReader br = null;
        try{
	         br = new BufferedReader(new InputStreamReader(new FileInputStream(templateFile)));
	        String line;
	        do{
	        	line = br.readLine();
	        	m_template.append(line);
	        }while(line!=null);
        }
        catch(Exception ex){
        	log.error(Util.toString(ex));
        }
        finally{
        	if(br!=null){
        		try{
        			br.close();
        		}
        		catch(Exception e){
        			log.error(Util.toString(e));
        		}
        	}
        }
        
    }
    
    @Override
    public void updateData(final List<RateData> score){
    	StringBuilder sb = new StringBuilder(m_template.toString());
    	String html = sb.replace(sb.lastIndexOf("["), sb.lastIndexOf("]")+1, buildTableBody(score)).toString();
    	tableEditorPane.setText(html);
    }
    
    
    private String buildTableBody(final List<RateData> score){
    	StringBuilder sb= new StringBuilder();
    	
    	int id = 1;
    	for(RateData data :score){
    		sb.append("<tr>");
    		
    		sb.append("<td>");
    		sb.append(id);
    		sb.append("</td>");
    		
    		sb.append("<td>");
    		sb.append(data.getName());
    		sb.append("</td>"); 
    		
    		sb.append("<td>");
    		sb.append(data.getRank().toString());
    		sb.append("</td>"); 
    		
    		sb.append("<td>");
    		sb.append(data.getScore());
    		sb.append("</td>"); 

    		
    		sb.append("</tr>");
    		
    		id++;
    	}
    	
    	return sb.toString();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        tableScrollPane = new javax.swing.JScrollPane();
        tableEditorPane = new javax.swing.JEditorPane();
        toGameBtn = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        tableEditorPane.setEditable(false);
        tableEditorPane.setBorder(null);
        tableEditorPane.setContentType("text/html"); // NOI18N
        tableScrollPane.setViewportView(tableEditorPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 331;
        gridBagConstraints.ipady = 274;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 0, 10);
        add(tableScrollPane, gridBagConstraints);

        toGameBtn.setText(Messages.TOGAME.toString());
        toGameBtn.setPreferredSize(new java.awt.Dimension(80, 23));
        toGameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toGameBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 11, 0);
        add(toGameBtn, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void toGameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toGameBtnActionPerformed
        MainFrame.get().showPlayPanel();
    }//GEN-LAST:event_toGameBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane tableEditorPane;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JButton toGameBtn;
    // End of variables declaration//GEN-END:variables
}
