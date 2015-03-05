
package com.cp.dma.javaweatherobjects2;

import com.google.gson.annotations.Expose;

public class Sys {

    @Expose
    private Integer population;

    /**
     * 
     * @return
     *     The population
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * 
     * @param population
     *     The population
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

}
