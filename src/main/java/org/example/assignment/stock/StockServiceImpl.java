package org.example.assignment.stock;

import org.example.common.Util;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.List;

public class StockServiceImpl implements StockService {

    private final ExternalStockServiceClient externalStockServiceClient = new ExternalStockServiceClient();


    @Override
    public Flux<String> calculateProfit() {
        return externalStockServiceClient.getStockPrice();
    }

    public static void main(String[] args) {
        StockService stockService = new StockServiceImpl();
        var subscriber = new StockPriceSubscriber();
        Flux<Integer> priceFlux = stockService.calculateProfit()
                .map(Integer::parseInt);
        priceFlux.subscribe(subscriber);
        Util.sleepSeconds(20);

    }
}
