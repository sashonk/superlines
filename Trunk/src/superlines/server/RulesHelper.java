package superlines.server;

import superlines.core.Rank;
import superlines.core.SuperlinesRules;

public class RulesHelper {
	
	public static int getAcceptableResult(final Rank rank){
		if(rank == Rank.NEWBIE){
			return 0;
		}
		if(rank == Rank.AVERAGE){
			return 1000 ;			
		}
		if(rank == Rank.EXPERIENCED){
			return 5000;		
		}
		if(rank == Rank.ADEPT){
			return  10000;		
		}
		if(rank == Rank.MASTER){
			return 50000 ;		
		}		
		if(rank == Rank.GODLIKE){
			return 10000 ;		
		}		
		
		return 0;
	}
	
	public static SuperlinesRules createRules(final Rank rank){
		SuperlinesRules rules = new SuperlinesRules();
		rules.setShowTip(true);
		rules.setAllowLeap(false);
		rules.setCountFlat(true);
		rules.setCountSkew(true);

		
		if(rank == Rank.NEWBIE){
			rules.setMinWinBalls(5);
			rules.setExtraAward(50);
			rules.setNormalAward(100);
			rules.setScatterBallsCount(3);
			rules.setColorCount(5);
			rules.setTableWidth(10);
			rules.setShowTip(true);
			rules.setProgressiveEnabled(false);
		

		}
		if(rank == Rank.AVERAGE){
			rules.setMinWinBalls(5);
			rules.setExtraAward(50);
			rules.setNormalAward(100);
			rules.setScatterBallsCount(3);
			rules.setColorCount(7);
			rules.setTableWidth(9);
			rules.setShowTip(true);
			rules.setProgressiveEnabled(false);
			
		}
/*		if(rank == Rank.SKILLED){
			rules.setMinWinBalls(5);
			rules.setExtraAward(50);
			rules.setNormalAward(100);
			rules.setScatterBallsCount(3);
			rules.setColorCount(8);
			rules.setTableWidth(9);
			rules.setShowTip(true);
			
		}*/
		if(rank == Rank.EXPERIENCED){
			rules.setMinWinBalls(5);
			rules.setExtraAward(50);
			rules.setNormalAward(100);
			rules.setScatterBallsCount(3);
			rules.setColorCount(9);
			rules.setTableWidth(9);
			rules.setShowTip(true);
			rules.setProgressiveEnabled(true);
			rules.setProgressiveThreshold1(10000);
			rules.setProgressiveThreshold2(50000);
			rules.setProgressive1Multiplier(2);
			rules.setProgressvive2Multiplier(4);
			
		}
		if(rank == Rank.ADEPT){
			rules.setMinWinBalls(6);
			rules.setExtraAward(50);
			rules.setNormalAward(100);
			rules.setScatterBallsCount(3);
			rules.setColorCount(9);
			rules.setTableWidth(9);
			rules.setShowTip(true);
			rules.setProgressiveEnabled(true);
			rules.setProgressiveThreshold1(20000);
			rules.setProgressiveThreshold2(100000);
			rules.setProgressive1Multiplier(2);
			rules.setProgressvive2Multiplier(4);			
			
		}
		if(rank == Rank.MASTER){
			rules.setMinWinBalls(6);
			rules.setExtraAward(50);
			rules.setNormalAward(100);
			rules.setScatterBallsCount(4);
			rules.setColorCount(10);
			rules.setTableWidth(9);
			rules.setShowTip(true);
			rules.setProgressiveEnabled(false);
			
		}
/*		if(rank == Rank.MASTER1){
			rules.setMinWinBalls(3);
			rules.setExtraAward(40);
			rules.setNormalAward(80);
			rules.setScatterBallsCount(3);
			rules.setColorCount(5);
			rules.setTableWidth(10);
			rules.setShowTip(true);
			
		}
		if(rank == Rank.MASTER2){
			rules.setMinWinBalls(7);
			rules.setExtraAward(25);
			rules.setNormalAward(50);
			rules.setScatterBallsCount(7);
			rules.setColorCount(5);
			rules.setTableWidth(10);
			rules.setShowTip(true);
			
		}*/
		if(rank == Rank.GODLIKE){
			rules.setMinWinBalls(6);
			rules.setExtraAward(250);
			rules.setNormalAward(500);
			rules.setScatterBallsCount(5);
			rules.setColorCount(10);
			rules.setTableWidth(8);
			rules.setShowTip(true);
			rules.setProgressiveEnabled(false);
			
		}
		
		return rules;
	}
}
