package ecommerce.rmall.domain;

import javax.xml.bind.annotation.XmlRootElement;

/***
 * 百度推送ID
 * @author martin
 *
 */
@XmlRootElement (name = "Channel")
public class Channel {
	
	private long channelID;
	private String userID;
	private String osType;
	
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public long getChannelID() {
		return channelID;
	}
	public void setChannelID(long channelID) {
		this.channelID = channelID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
}
