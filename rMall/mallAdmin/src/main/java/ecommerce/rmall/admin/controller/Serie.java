package ecommerce.rmall.admin.controller;

import java.util.List;

public class Serie {
	
	public Serie(String name){
		this.name = name;
	}
	private String name;
	private List<Integer> data;
	
	public String getName(){return this.name;}
	public List<Integer> getData(){return this.data;}
	public void setData(List<Integer> data){this.data = data;}

}
