package br.com.via.explojista.dojo.grpc.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class FinnhubQuoteResponse {

    @JsonAlias("c")
    private Double currentPrice;
    @JsonAlias("d")
    private Double change;
    @JsonAlias("dp")
    private Double percentChange;
    @JsonAlias("h")
    private Double highPriceDay;
    @JsonAlias("l")
    private Double lowPriceDay;
    @JsonAlias("o")
    private Double openPriceDay;
    @JsonAlias("pc")
    private Double previousClosePrice;

    @JsonAlias("t")
    private Long timestamp;

    public FinnhubQuoteResponse() {
    }

    public FinnhubQuoteResponse(Double currentPrice, Double change, Double percentChange, Double highPriceDay,
                                Double lowPriceDay, Double openPriceDay, Double previousClosePrice, Long timestamp) {
        this.currentPrice = currentPrice;
        this.change = change;
        this.percentChange = percentChange;
        this.highPriceDay = highPriceDay;
        this.lowPriceDay = lowPriceDay;
        this.openPriceDay = openPriceDay;
        this.previousClosePrice = previousClosePrice;
        this.timestamp = timestamp;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(Double percentChange) {
        this.percentChange = percentChange;
    }

    public Double getHighPriceDay() {
        return highPriceDay;
    }

    public void setHighPriceDay(Double highPriceDay) {
        this.highPriceDay = highPriceDay;
    }

    public Double getLowPriceDay() {
        return lowPriceDay;
    }

    public void setLowPriceDay(Double lowPriceDay) {
        this.lowPriceDay = lowPriceDay;
    }

    public Double getOpenPriceDay() {
        return openPriceDay;
    }

    public void setOpenPriceDay(Double openPriceDay) {
        this.openPriceDay = openPriceDay;
    }

    public Double getPreviousClosePrice() {
        return previousClosePrice;
    }

    public void setPreviousClosePrice(Double previousClosePrice) {
        this.previousClosePrice = previousClosePrice;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
