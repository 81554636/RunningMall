package ecommerce.rmall.notify.yuntongxun;

import java.util.HashMap;

import com.cloopen.rest.sdk.CCPRestSmsSDK;

import ecommerce.rmall.notify.INotify;
import ecommerce.rmall.notify.NotifyResult;

public class SmsNotifyImpl implements INotify {
	
	private String serverUrl = "sandboxapp.cloopen.com";
	private String serverPort = "8883";
	private String accountSID = "aaf98f894d328b13014d6fa72cbe2c07";
	private String accountToken = "c633a2b3edd642fcb60b9a24df220ca0";
	private String appID = "8a48b5514d32a2a8014d6fa9203c2b96";
	
	public String getServerUrl() { return serverUrl; }
	public void setServerUrl(String serverUrl) { this.serverUrl = serverUrl; }

	public String getServerPort() { return serverPort; }
	public void setServerPort(String serverPort) { this.serverPort = serverPort; }

	public String getAccountSID() { return accountSID; }
	public void setAccountSID(String accountSID) { this.accountSID = accountSID; }

	public String getAccountToken() { return accountToken; }
	public void setAccountToken(String accountToken) { this.accountToken = accountToken; }

	public String getAppID() { return appID; }
	public void setAppID(String appID) { this.appID = appID; }

	@Override
	public NotifyResult getBalance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NotifyResult sendMessage(String phone, String content) {
		
		CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
		restAPI.init(this.serverUrl, this.serverPort);
		restAPI.setAccount(this.accountSID, this.accountToken);
		restAPI.setAppId(this.appID);

		HashMap<String, Object> result = restAPI.sendTemplateSMS(phone,"1" ,new String[]{content,"30"});

		return new NotifyResult((String)result.get("statusCode"), (String)result.get("statusMsg"));
	}
}
