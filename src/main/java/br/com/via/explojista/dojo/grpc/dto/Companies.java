package br.com.via.explojista.dojo.grpc.dto;

public enum Companies {
    APPLE("static/apple_prices.csv"),
    MICROSOFT("static/microsoft_prices.csv");

    private String quotationPath;

    public String getQuotationPath() {
        return quotationPath;
    }

    Companies(String data) {
        this.quotationPath = data;
    }
}