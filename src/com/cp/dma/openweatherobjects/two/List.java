package com.cp.dma.openweatherobjects.two;

public class List {
	
	public int dt;
    public Main main;
    public java.util.List<Weather> weather;
    public Clouds clouds;
    public Wind wind;
    public Sys2 sys;
    public String dt_txt;
    public Rain rain;
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public java.util.List<Weather> getWeather() {
		return weather;
	}
	public void setWeather(java.util.List<Weather> weather) {
		this.weather = weather;
	}
	public Clouds getClouds() {
		return clouds;
	}
	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}
	public Wind getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = wind;
	}
	public Sys2 getSys() {
		return sys;
	}
	public void setSys(Sys2 sys) {
		this.sys = sys;
	}
	public String getDt_txt() {
		return dt_txt;
	}
	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}
	public Rain getRain() {
		return rain;
	}
	public void setRain(Rain rain) {
		this.rain = rain;
	}
    
    

}
