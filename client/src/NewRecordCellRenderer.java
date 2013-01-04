import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;


public class NewRecordCellRenderer extends JTextArea implements TableCellRenderer
{
	public NewRecordCellRenderer(){
		this.setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
	}

	public Component getTableCellRendererComponent(JTable table, Object obj,
			boolean isSelected, boolean hasFocus, int row, int col) {
		// TODO Auto-generated method stub
		if(isSelected)
		{
			setForeground(table.getSelectionForeground());
			this.setBackground(table.getSelectionBackground());
		}
		else{
			setForeground(table.getForeground());
			this.setBackground(table.getBackground());
		}
		
		if(hasFocus)
		{
			setBorder(UIManager.getBorder("Table.focusCellHighlishtBorder"));
			if(table.isCellEditable(row, col)){
				this.setForeground(UIManager.getColor("Table.focusCellForeground"));
				this.setBackground(UIManager.getColor("Table.focusCellBackground"));
			}
			else setBorder(new EmptyBorder(1, 2, 1, 2));
		}
		MyTableModel tm = (MyTableModel)table.getModel();
		if(tm.getValueAt(row, 3)==(Integer)1)		
		{
			this.setBackground(Color.YELLOW);
		}

		this.setFont(table.getFont());
		if(col!=0)
			this.setText(obj==null ? "" : obj.toString());
		else{
			this.setText(obj==null ? "" : Integer.toString(row+1));
			this.setBackground(new Color(233, 244, 255));
		}
		
		if(row==0)
		{
			this.setForeground(new Color(155, 175, 255));
			if(col==1)
				this.setText(obj==null ? "" : "*** "+obj.toString() +" ***");
		}
		
					
		return this;
	}
	
}
