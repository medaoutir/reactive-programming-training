package org.example.assignment.publishers.hot;

import reactor.core.publisher.Flux;

public interface OrderService {

    Flux<String> getOrder();
}
