package superlines.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SuperlinesRules {
	
	public int getExtraAward(){
		return m_extraAward;
	}
	
	public void setExtraAward(int value){
		m_extraAward = value;
	}
	
	public int getNormalAward(){
		return m_normalAward;
	}
	
	public void setNormalAward(int value){
		m_normalAward= value;
	}
	
	public int getColorCount(){
		return m_colorCount;
	}
	
	public void setColorCount(int value){
		m_colorCount = value;
	}
	
	public int getTableWidth(){
		return m_width;
	}
	
	public void setTableWidth(int value){
		m_width = value;
	}
	
	public int getMinWinBalls(){
		return m_minWinBalls;
	}
	
	public void setMinWinBalls(int value){
		m_minWinBalls = value;
	}
	
	public int getScatterBallsCount(){
		return m_scatterBallsCount;
	}
	
	public void setScatterBallsCount(int value){
		m_scatterBallsCount = value;
	}
	
	public boolean isCountFlat(){
		return m_countFlat;
	}
	
	public void setCountFlat(boolean value){
		m_countFlat = value;
	}
	
	public boolean isCountSkew(){
		return m_countSkew;
	}
	
	public void setCountSkew(boolean value){
		m_countSkew = value;
	}
	
	public void setShowTip(boolean value){
		m_showTip = value;
	}
	
	public boolean isShowTip(){
		return m_showTip;
	}
	
	public void setAllowLeap(boolean value){
		m_allowLeap = value;
	}
	
	public boolean isAllowLeap(){
		return m_allowLeap;
	}
	
	@XmlAttribute(name="scatterBallsCount")
	private int m_scatterBallsCount;
	
	@XmlAttribute(name="minWinBalss")
	private int m_minWinBalls;
	
	@XmlAttribute(name="extraAward")
	private int m_extraAward;
	
	@XmlAttribute(name="normalAward")
	private int m_normalAward;
	
	@XmlAttribute(name="colorCount")
	private int m_colorCount;
	
	@XmlAttribute(name="width")
	private int m_width;
	
	@XmlAttribute(name="countFlat")
	private boolean m_countFlat;
	
	@XmlAttribute(name="countSkew")
	private boolean m_countSkew;
	
	@XmlAttribute(name="showTip")
	private boolean m_showTip;
	
	@XmlAttribute(name="allowJump")
	private boolean m_allowLeap;
	
}
