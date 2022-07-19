package br.com.via.explojista.dojo.grpc.client;


import br.com.via.explojista.dojo.grpc.dto.FinnhubQuoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "financial-api", url = "https://finnhub.io", path = "/api/v1")
public interface FinnhubClient {

    @GetMapping("/quote")
    FinnhubQuoteResponse getQuoteFrom(@RequestHeader("X-Finnhub-Token") String apiKey, @RequestParam("symbol") String symbol);

}
