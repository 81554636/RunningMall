package ecommerce.rmall.notify;

public class NotifyNothing implements INotify {

	@Override
	public NotifyResult getBalance() {
		return null;
	}

	@Override
	public NotifyResult sendMessage(String phone, String content) {
		return null;
	}

}
