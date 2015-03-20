package ecommerce.rmall.domain;

public class CountByDate {
	private int count;
	private String date;
	public CountByDate(int count, String date){
		this.count = count;
		this.date = date;
	}
	
	public int getCount(){return this.count;}
	public String getDate(){return this.date;}
}
