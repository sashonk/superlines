package superlines.core;

public enum Rank {
	NEWBIE{
		public int getRank(){
			return 1;
		}
		
	},
	AVERAGE{
		public int getRank(){
			return 2;
		}
		
	},
	EXPERIENCED{
		public int getRank(){
			return 3;
		}
		
	},
	SKILLED{
		public int getRank(){
			return 4;
		}
		
	},
	ADEPT{
		public int getRank(){
			return 5;
		}
		
	},
	MASTER{
		public int getRank(){
			return 6;
		}
		
	},
	MASTER1{
		public int getRank(){
			return 7;
		}
		
	},
	MASTER2{
		public int getRank(){
			return 8;
		}
		
	},
	GODLIKE{
		public int getRank(){
			return 9;
		}
		
	};
	
	public abstract int getRank();
	public String toString(){
		String str = Localizer.getLocalizedString(String.format("%s.%s",Rank.class.getName(), this.name()));
		if(str==null){
			str = String.format("%s.%s", Rank.class.getName(), this.name());
		}
		
		return  str;
	}
}
