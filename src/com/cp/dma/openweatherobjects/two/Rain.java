package com.cp.dma.openweatherobjects.two;

import com.google.gson.annotations.SerializedName;

public class Rain {

	@SerializedName("3h")
	public double segment3h;

	public double getSegment3h() {
		return segment3h;
	}

	public void setSegment3h(double segment3h) {
		this.segment3h = segment3h;
	}
	
	
	
	
}
