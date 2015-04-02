package ecommerce.rmall.domain;

public class CountByStatus {
	
	private int count;
	private String status;
	public CountByStatus(int count, String status){
		this.count = count;
		this.status = status;
	}
	
	public int getCount(){return this.count;}
	public String getStatus(){return this.status;}

}
