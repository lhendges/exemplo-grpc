package br.com.via.explojista.dojo.grpc.service;

import br.com.via.explojista.dojo.grpc.QuotationServiceGrpc;
import br.com.via.explojista.dojo.grpc.QuoteResponse;
import br.com.via.explojista.dojo.grpc.RecomendationResponse;
import br.com.via.explojista.dojo.grpc.Request;
import br.com.via.explojista.dojo.grpc.client.FinnhubClient;
import br.com.via.explojista.dojo.grpc.dto.FinnhubQuoteResponse;
import br.com.via.explojista.dojo.grpc.dto.FinnhubRecomendationResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.List;

@GrpcService
public class QuotationService extends QuotationServiceGrpc.QuotationServiceImplBase {
    @Autowired
    private FinnhubClient finnhubClient;

    @Value("${apiKey}")
    private String apikey;

    @Override
    public void getQuotationByStockSymbol(Request request, StreamObserver<QuoteResponse> responseObserver) {
        FinnhubQuoteResponse response = finnhubClient.getQuote(apikey, request.getCompany().name());

        responseObserver.onNext(buildQuoteResponse(request, response));
        responseObserver.onCompleted();
    }

    @Override
    public void getRecomendationByStockSymbol(Request request, StreamObserver<RecomendationResponse> responseObserver) {
        List<FinnhubRecomendationResponse> response = finnhubClient.getRecomendations(apikey, request.getCompany().name());
        for (FinnhubRecomendationResponse finnhubRecomendationResponse : response) {
            responseObserver.onNext(
                    RecomendationResponse.newBuilder()
                            .setCompany(request.getCompany().name())
                            .setBuy(finnhubRecomendationResponse.getBuy())
                            .setHold(finnhubRecomendationResponse.getHold())
                            .setSell(finnhubRecomendationResponse.getSell())
                            .setPeriod(finnhubRecomendationResponse.getPeriod())
                            .setStrongBuy(finnhubRecomendationResponse.getStrongBuy())
                            .setStrongSell(finnhubRecomendationResponse.getStrongSell())
                            .build()
            );
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        responseObserver.onCompleted();
    }

    private QuoteResponse buildQuoteResponse(Request request, FinnhubQuoteResponse quoteResponse) {
        return QuoteResponse.newBuilder()
                .setCompany(request.getCompany().name())
                .setCurrentPrice(quoteResponse.getCurrentPrice())
                .setChange(quoteResponse.getChange())
                .setHighPrice(quoteResponse.getHighPriceDay())
                .setLowPrice(quoteResponse.getLowPriceDay())
                .setOpenPrice(quoteResponse.getOpenPriceDay())
                .setClosePrice(quoteResponse.getPreviousClosePrice())
                .setDate(Instant.ofEpochSecond(quoteResponse.getTimestamp()).toString())
                .build();
    }
}
