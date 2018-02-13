package com.mub.graphql.bo;

/**
 * Created by manodyas on 2/6/2018.
 */
public class PriceEvent {
    private int eventId;
    private String  symbol;
    private String timeStamp;
    private String lastTradePrice;
    private String volume;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLastTradePrice() {
        return lastTradePrice;
    }

    public void setLastTradePrice(String lastTradePrice) {
        this.lastTradePrice = lastTradePrice;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
