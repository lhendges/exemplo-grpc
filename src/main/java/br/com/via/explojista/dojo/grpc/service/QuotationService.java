package br.com.via.explojista.dojo.grpc.service;

import br.com.via.explojista.dojo.grpc.QuotationServiceGrpc;
import br.com.via.explojista.dojo.grpc.Request;
import br.com.via.explojista.dojo.grpc.Response;
import br.com.via.explojista.dojo.grpc.ResponseItem;
import br.com.via.explojista.dojo.grpc.dto.StockPrice;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

@GrpcService
public class QuotationService extends QuotationServiceGrpc.QuotationServiceImplBase {

    @Autowired
    private DataService dataService;

    @Override
    public void getQuotation(Request request, StreamObserver<Response> responseObserver) {
        Response.Builder resBuilder = Response.newBuilder();
        try {
            List<StockPrice> data = dataService.getData(request.getCompany());

            data.stream().map(stockPrice -> buidResponseItem(request, stockPrice)).forEach(resBuilder::addItems);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        responseObserver.onNext(resBuilder.build());
        responseObserver.onCompleted();
    }

    private ResponseItem buidResponseItem(Request request, StockPrice stockPrice) {
        return ResponseItem.newBuilder().setCompany(request.getCompany().name()).setDate(stockPrice.getDate().toString()).setClosePrice(stockPrice.getClosePrice()).setOpenPrice(stockPrice.getOpenPrice()).setHighPrice(stockPrice.getHighPrice()).setLowPrice(stockPrice.getLowPrice()).setVolume(stockPrice.getVolume()).build();
    }
}
