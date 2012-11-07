/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package superlines.core;

/**
 *
 * @author Sashonk
 */
public enum Messages {
	AUTH_FAILED,
	GENERIC_ERROR,
	SERVICE_UNAVAILABLE;
	
	@Override
	public String toString(){
		String key = String.format("%s.%s", Messages.class.getName(), this.name());
		String value = Localizer.getLocalizedString(key);
		if(value == null){
			value = key;
		}
		
		return value;
	}
}
