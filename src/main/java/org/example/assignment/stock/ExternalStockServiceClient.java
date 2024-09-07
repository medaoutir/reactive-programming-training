package org.example.assignment.stock;

import org.example.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalStockServiceClient extends AbstractHttpClient {

    public Flux<String> getStockPrice() {
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString();
    }

}
