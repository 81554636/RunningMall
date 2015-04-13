package ecommerce.rmall.notify;

public interface INotify {
	
	NotifyResult getBalance();
	NotifyResult sendMessage(String phone, String content);
}
