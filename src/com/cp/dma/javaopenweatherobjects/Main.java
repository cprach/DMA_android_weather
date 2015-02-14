package com.cp.dma.javaopenweatherobjects;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class Main {

private Double temp;
@SerializedName("temp_min")
private Double tempMin;
@SerializedName("temp_max")
private Double tempMax;
private Double pressure;
@SerializedName("sea_level")
private Double seaLevel;
@SerializedName("grnd_level")
private Double grndLevel;
private Integer humidity;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
* 
* @return
* The temp
*/
public Double getTemp() {
return temp;
}

/**
* 
* @param temp
* The temp
*/
public void setTemp(Double temp) {
this.temp = temp;
}

/**
* 
* @return
* The tempMin
*/
public Double getTempMin() {
return tempMin;
}

/**
* 
* @param tempMin
* The temp_min
*/
public void setTempMin(Double tempMin) {
this.tempMin = tempMin;
}

/**
* 
* @return
* The tempMax
*/
public Double getTempMax() {
return tempMax;
}

/**
* 
* @param tempMax
* The temp_max
*/
public void setTempMax(Double tempMax) {
this.tempMax = tempMax;
}

/**
* 
* @return
* The pressure
*/
public Double getPressure() {
return pressure;
}

/**
* 
* @param pressure
* The pressure
*/
public void setPressure(Double pressure) {
this.pressure = pressure;
}

/**
* 
* @return
* The seaLevel
*/
public Double getSeaLevel() {
return seaLevel;
}

/**
* 
* @param seaLevel
* The sea_level
*/
public void setSeaLevel(Double seaLevel) {
this.seaLevel = seaLevel;
}

/**
* 
* @return
* The grndLevel
*/
public Double getGrndLevel() {
return grndLevel;
}

/**
* 
* @param grndLevel
* The grnd_level
*/
public void setGrndLevel(Double grndLevel) {
this.grndLevel = grndLevel;
}

/**
* 
* @return
* The humidity
*/
public Integer getHumidity() {
return humidity;
}

/**
* 
* @param humidity
* The humidity
*/
public void setHumidity(Integer humidity) {
this.humidity = humidity;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}