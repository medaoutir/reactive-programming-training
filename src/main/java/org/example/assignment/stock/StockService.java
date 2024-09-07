package org.example.assignment.stock;

import reactor.core.publisher.Flux;

public interface StockService {

    Flux<String> calculateProfit();
}
