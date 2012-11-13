package superlines.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import superlines.core.Configuration;
import superlines.core.Rank;

public class PromotionHelper {
	private static final Log log = LogFactory.getLog(PromotionHelper.class);
	private static final Map<String, String> m_data = new HashMap<>();
	private static final Map<Rank, Integer> m_standardRanks = new HashMap<>();
	
	public static void fillData(){
		BufferedReader br = null;
		try{
		Configuration cfg = Configuration.get();
		File folder = cfg.getDataFolder();
		File promotionFolder = new File(folder, "promotion");
		
		for(File entry : promotionFolder.listFiles()){
			 br = new BufferedReader(new InputStreamReader(new FileInputStream(entry), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			do{
				line = br.readLine();
				if(line!=null){
					sb.append(line);
					sb.append('\n');
				}
			}while(line != null);
			
			m_data.put(entry.getName(), sb.toString());
			
			br.close();
		}
		}
		catch(Exception ex){
			log.error(ex);
		}
		finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					log.error(e);
				}
			}
		}
		
		m_standardRanks.put(Rank.NEWBIE, Integer.valueOf(0));
		m_standardRanks.put(Rank.AVERAGE, Integer.valueOf(20000));
		m_standardRanks.put(Rank.EXPERIENCED, Integer.valueOf(50000));
		m_standardRanks.put(Rank.ADEPT, Integer.valueOf(100000));
		m_standardRanks.put(Rank.MASTER, Integer.valueOf(500000));
		m_standardRanks.put(Rank.GODLIKE, Integer.valueOf(1000000));
		
		
	}
	

	
	public static String getPromotionMessage(final  Rank newRank, final String locale){
			return m_data.get(String.format("%s-%s.properties", newRank.name(), locale));
	}

	public static Rank getQualifiedRankTest(final int score, final Rank currentRank){
		if(currentRank == Rank.NEWBIE){
			return score > 100 ? Rank.AVERAGE : currentRank;
		}
		if(currentRank == Rank.AVERAGE){
			return score > 300 ? Rank.EXPERIENCED : currentRank;			
		}
		if(currentRank == Rank.EXPERIENCED){
			return score > 500 ? Rank.ADEPT : currentRank;			
		}
		if(currentRank == Rank.ADEPT){
			return score > 1000 ? Rank.MASTER : currentRank;			
		}
		if(currentRank == Rank.MASTER){
			return score > 5000 ? Rank.GODLIKE : currentRank;			
		}
		
		return Rank.GODLIKE;
	}

	public static Rank getQualifiedRank(final int score, final Rank currentRank){
		if(currentRank == Rank.NEWBIE){
			return score > 20000 ? Rank.AVERAGE : currentRank;
		}
		if(currentRank == Rank.AVERAGE){
			return score > 50000 ? Rank.EXPERIENCED : currentRank;			
		}
		if(currentRank == Rank.EXPERIENCED){
			return score > 100000 ? Rank.ADEPT : currentRank;			
		}
		if(currentRank == Rank.ADEPT){
			return score > 500000 ? Rank.MASTER : currentRank;			
		}
		if(currentRank == Rank.MASTER){
			return score > 1000000 ? Rank.GODLIKE : currentRank;			
		}
		
		return Rank.GODLIKE;
	}
	
	public static int getQualifiedRate(final Rank rank){
		return m_standardRanks.get(rank);
	}
	


}
