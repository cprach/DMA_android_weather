package com.cp.dma.openweatherobjects.two;

public class RootObject {

    public int cod;
    public double message;
    public City city;
    public int cnt;
    public java.util.List<com.cp.dma.openweatherobjects.two.List> list;
    
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public double getMessage() {
		return message;
	}
	public void setMessage(double message) {
		this.message = message;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public java.util.List<com.cp.dma.openweatherobjects.two.List> getList() {
		return list;
	}
	public void setList(java.util.List<com.cp.dma.openweatherobjects.two.List> list) {
		this.list = list;
	}
    
	
    
}
