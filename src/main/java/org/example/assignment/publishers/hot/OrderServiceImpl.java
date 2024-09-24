package org.example.assignment.publishers.hot;

import reactor.core.publisher.Flux;

public class OrderServiceImpl implements OrderService{

    private final ExternalOrderServiceClient externalOrderServiceClient = new ExternalOrderServiceClient();
    @Override
    public Flux<String> getOrder() {
        return externalOrderServiceClient.getOrderStream().publish().refCount(2);
    }
}
