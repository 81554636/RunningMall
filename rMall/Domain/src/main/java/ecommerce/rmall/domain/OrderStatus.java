package ecommerce.rmall.domain;

public enum OrderStatus {
	PENDING("PENDING"),
	PROCESSING("PROCESSING"), 
	CANCEL("CANCEL"),
	FINISH("FINISH");
	
	OrderStatus(String status){
	}
}
