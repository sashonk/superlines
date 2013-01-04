package settings;

public class UserSettings {
	
	public void setSound(boolean s)
	{
		sound =  s;
	}
	
	public boolean getSound()
	{
		return sound;
	}
	
	public void setTip(boolean s)
	{
		tip =  s;
	}
	
	public boolean getTip()
	{
		return tip;
	}
	
	
	private boolean sound;
	private boolean tip;
}
