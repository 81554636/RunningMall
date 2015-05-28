package ecommerce.rmall.notify.moming;

import ecommerce.rmall.notify.INotify;
import ecommerce.rmall.notify.NotifyResult;

public class SmsNotifyImpl implements INotify {
	
	private ISmsEndpoint sms;
	public void setSmsEndpoint(ISmsEndpoint sms){this.sms = sms;}
	
	private String account;
	public void setAccount(String account){ this.account = account; }
	
	private String password;
	public void setPassword(String password){ this.password = password;}

	@Override
	public NotifyResult getBalance() {

		String result = this.sms.getBalance("getBalance", this.account, this.password);
		String[] arr = result.split("\\|\\|");
		return new NotifyResult(arr[0], arr[1]);
	}

	@Override
	public NotifyResult sendMessage(String phone, String content) {

		String sent = String.format("尊敬的%s, 您的注册码为 %s, 请于30分钟内正确输入[跑跑巴士]", phone, content);
		String result = this.sms.sendMessage("send", 
				this.account, this.password, 
				"utf8", phone, sent);
		return new NotifyResult(result, "");
	}	
}
