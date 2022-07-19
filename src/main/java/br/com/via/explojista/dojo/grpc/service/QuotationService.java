package br.com.via.explojista.dojo.grpc.service;

import br.com.via.explojista.dojo.grpc.QuotationServiceGrpc;
import br.com.via.explojista.dojo.grpc.Request;
import br.com.via.explojista.dojo.grpc.Response;
import br.com.via.explojista.dojo.grpc.ResponseItem;
import br.com.via.explojista.dojo.grpc.client.FinnhubClient;
import br.com.via.explojista.dojo.grpc.dto.FinnhubQuoteResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@GrpcService
public class QuotationService extends QuotationServiceGrpc.QuotationServiceImplBase {
    @Autowired
    private FinnhubClient finnhubClient;

    @Override
    public void getQuotationByStockCode(Request request, StreamObserver<Response> responseObserver) {
        FinnhubQuoteResponse response = finnhubClient.getQuoteFrom("sandbox_cbaungaad3i91bfqctug", request.getCompany().name());

        Response.Builder resBuilder = Response.newBuilder();
        resBuilder.setItems(buidResponseItem(request, response));
        responseObserver.onNext(resBuilder.build());
        responseObserver.onCompleted();
    }

    private ResponseItem buidResponseItem(Request request, FinnhubQuoteResponse quoteResponse) {
        return ResponseItem.newBuilder()
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
