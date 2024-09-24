package org.example.assignment.publishers.hot;

import org.example.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ExternalOrderServiceClient extends AbstractHttpClient {

    public Flux<String> getOrderStream() {
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .delayElements(Duration.ofSeconds(2));
    }

}
