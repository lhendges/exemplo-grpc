package br.com.via.explojista.dojo.grpc.client;


import br.com.via.explojista.dojo.grpc.dto.FinnhubQuoteResponse;
import br.com.via.explojista.dojo.grpc.dto.FinnhubRecomendationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "financial-api", url = "https://finnhub.io", path = "/api/v1")
public interface FinnhubClient {

    @GetMapping("/quote")
    FinnhubQuoteResponse getQuote(@RequestHeader("X-Finnhub-Token") String apiKey, @RequestParam("symbol") String symbol);

    @GetMapping("/stock/recommendation")
    List<FinnhubRecomendationResponse> getRecomendations(@RequestHeader("X-Finnhub-Token") String apiKey, @RequestParam("symbol") String symbol);
}
