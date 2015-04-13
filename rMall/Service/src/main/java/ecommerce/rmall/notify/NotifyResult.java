package ecommerce.rmall.notify;

public class NotifyResult {
	private int retCode;
	private String retValue;
	
	public NotifyResult(int retCode, String retValue){
		this.retCode = retCode;
		this.retValue = retValue;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetValue() {
		return retValue;
	}

	public void setRetValue(String retValue) {
		this.retValue = retValue;
	}
}
