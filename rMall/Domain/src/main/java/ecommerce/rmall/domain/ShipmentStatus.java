package ecommerce.rmall.domain;

public enum ShipmentStatus {
	INIT("INIT"),
	PROCESSING("PROCESSING"),
	FINISH("FINISH");
	
	private String status;
	ShipmentStatus(String status){this.status = status;}
}
