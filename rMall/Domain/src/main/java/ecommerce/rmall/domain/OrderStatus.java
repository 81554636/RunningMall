package ecommerce.rmall.domain;

public enum OrderStatus {
	PENDING("PENDING"),
	PROCESSING("PROCESSING"), 
	CANCEL("CANCEL"),
	FINISH("FINISH");
	
	private String status;
	OrderStatus(String status){
		this.status = status;
	}
}
