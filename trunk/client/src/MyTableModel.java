import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import java.util.Collections;

import settings.AppData;


public class MyTableModel extends AbstractTableModel{

	public MyTableModel()
	{
		Object[][] buf = settings.AppData.getInstance().GetStats();

		if(buf!=null){
			for(int i = 0; i<buf.length; i++)
			{
				Vector<Object> v = new Vector<Object>();
				v.add(0);
				v.add(buf[i][0]);
				v.add(buf[i][1]);
				v.add(0);
				data.add(v);
			}	
		}
		else{
			for(int i = 0; i<maxNRecords; i++)
			{
				Vector<Object> v = new Vector<Object>();
				v.add(0);
				v.add("");
				v.add(0);
				v.add(0);
				data.add(v);
			}	
		}
		SortData();
		
	}
	public int getRowCount(){return data.size();}
	public int getColumnCount(){return data.get(0).size()-1;}
	public Object getValueAt(int row, int col){return data.get(row).get(col);}
	public String getColumnName(int col){
		return colNames[col];
		}
	public void addRecord(Vector<Object> value)
	{
		DehighLightAll();
		data.add(value);
		if(data.size()>maxNRecords)
			removeMin();
		SortData();
		
		this.fireTableDataChanged();
	}
	public void Save()
	{
		Object[][] data = new Object[this.data.size()][3];
		for(int i = 0;i<data.length;i++)
		{
			for(int j = 1;j<3;j++)
			{
				data[i][j] = this.data.get(i).get(j);
			}
		}
		AppData.getInstance().SaveStats(data);
	}
	
	private void DehighLightAll()
	{
		for(int i=0;i<data.size(); i++)
		{
			data.get(i).set(3, 0);
		}
	}
	
	private void removeMin()
	{
		Integer min = (Integer)data.get(0).get(2);
		int index = 0;
		for(int i=0;i<data.size(); i++)
		{
			if((Integer)data.get(i).get(2)<=min)
			{
				min = (Integer)data.get(i).get(2);
				index = i;
			}
		}
		data.remove(index);
	}
	
	private void SortData()
	{
		Collections.sort(data, new VecCmp());	
	}
	
	
	private List<Vector<Object>> data = new ArrayList<Vector<Object>>();
	private String[] colNames = {"№","Игрок", "Очки"};
	private int maxNRecords = AppData.getInstance().GetMaxRows();
}

final class VecCmp implements Comparator
{
	public int compare(Object lval, Object rval) {
		// TODO Auto-generated method stub
		if((Integer)((Vector<Object>)lval).get(2)<=(Integer)((Vector<Object>)rval).get(2))
			return 1;
		else
			return -1;
	}
	
}
