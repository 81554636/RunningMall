package ecommerce.rmall.notify;

public class NotifyResult {
	private String retCode;
	private String retValue;
	
	public NotifyResult(String retCode, String retValue){
		this.retCode = retCode;
		this.retValue = retValue;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetValue() {
		return retValue;
	}

	public void setRetValue(String retValue) {
		this.retValue = retValue;
	}
}
