package com.tiago.moedas.model;

public class Currency {

    private SingleCurrency USD;
    private SingleCurrency EUR;

    public SingleCurrency getUSD() {
        return USD;
    }

    public void setUSD(SingleCurrency USD) {
        this.USD = USD;
    }

    public SingleCurrency getEUR() {
        return EUR;
    }

    public void setEUR(SingleCurrency EUR) {
        this.EUR = EUR;
    }

    public ResponseCurrency getResponse(){

        ResponseCurrency response = new ResponseCurrency();
        response.setDollar(Float.parseFloat(getUSD().getBid()));
        response.setEuro(Float.parseFloat(getEUR().getBid()));
        return response;
    }
}
