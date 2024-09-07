package org.example.assignment.products;

import org.example.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalProductServiceClient extends AbstractHttpClient {

    public Flux<String> getProduct(String productId) {
        return this.httpClient.get()
                .uri(String.format("/demo03/product/%s", productId))
                .responseContent()
                .asString();
    }

    public Flux<String> getFallback(String productId) {
        return this.httpClient.get()
                .uri(String.format("/demo03/timeout-fallback/product/%s", productId))
                .responseContent()
                .asString();
    }

    public Flux<String> getEmptyFallback(String productId) {
        return this.httpClient.get()
                .uri(String.format("/demo03/empty-fallback/product/%s", productId))
                .responseContent()
                .asString();
    }

}
