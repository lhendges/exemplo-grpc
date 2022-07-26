package br.com.via.explojista.dojo.grpc.dto;

public class FinnhubRecomendationResponse {

    private Integer buy;
    private Integer sell;
    private Integer hold;
    private String period;
    private Integer strongBuy;
    private Integer strongSell;
    private String symbol;

    public FinnhubRecomendationResponse() {
    }

    public FinnhubRecomendationResponse(Integer buy, Integer sell, Integer hold, String period, Integer strongBuy,
                                        Integer strongSell, String symbol) {
        this.buy = buy;
        this.sell = sell;
        this.hold = hold;
        this.period = period;
        this.strongBuy = strongBuy;
        this.strongSell = strongSell;
        this.symbol = symbol;
    }

    public Integer getBuy() {
        return buy;
    }

    public void setBuy(Integer buy) {
        this.buy = buy;
    }

    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }

    public Integer getHold() {
        return hold;
    }

    public void setHold(Integer hold) {
        this.hold = hold;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Integer getStrongBuy() {
        return strongBuy;
    }

    public void setStrongBuy(Integer strongBuy) {
        this.strongBuy = strongBuy;
    }

    public Integer getStrongSell() {
        return strongSell;
    }

    public void setStrongSell(Integer strongSell) {
        this.strongSell = strongSell;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
