package superlines.client.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SuperlinesHelper {
	
	private final static Map<Integer, Color> m_colorMap = new HashMap<Integer, Color>();
	static{
		m_colorMap.put(Integer.valueOf(0), Color.black);
		m_colorMap.put(Integer.valueOf(1), Color.red);
		m_colorMap.put(Integer.valueOf(2), Color.green);
		m_colorMap.put(Integer.valueOf(3), Color.yellow);
		m_colorMap.put(Integer.valueOf(4), Color.orange);
		m_colorMap.put(Integer.valueOf(5), Color.magenta);
		m_colorMap.put(Integer.valueOf(6), Color.cyan);
		m_colorMap.put(Integer.valueOf(7), Color.pink);
		m_colorMap.put(Integer.valueOf(9), Color.blue);

	}
	
	
	public static Color number2Color(int num){
		return m_colorMap.get(Integer.valueOf(num));
	}
	
	public static int color2Number(final Color c){
		for(Entry<Integer, Color> entry : m_colorMap.entrySet()){
			if(entry.getValue()==c){
				return entry.getKey().intValue();
			}
		}
		
		return 0;
	}
}
