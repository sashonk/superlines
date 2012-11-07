package superlines.client.ui;

import superlines.core.Localizer;

public enum Buttons {
	TOSCORE,
	SCATTER,
	RESTART,
	TOGAME,
	
	OK,
	OFFLINE,
	
	LOGIN_LABEL,
	PASSWORD_LABEL;

	
	@Override
	public String toString(){
		String key = String.format("%s.%s", Buttons.class.getName(), this.name());
		String value = Localizer.getLocalizedString(key);
		if(value == null){
			value = key;
		}
		
		return value;
	}
}
