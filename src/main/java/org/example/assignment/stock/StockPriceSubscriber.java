package org.example.assignment.stock;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockPriceSubscriber implements Subscriber<Integer> {

    private static final Logger log = LoggerFactory.getLogger(org.example.common.DefaultSubscriber.class);
    private int balance = 1000;
    private int quantity = 0;
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
        this.subscription = subscription;
    }

    @Override
    public void onNext(Integer price) {
        if (price < 90 && balance > price) {
            quantity++;
            balance -= price;
            log.info("bought a stock at {}. total quantity: {}, remaining balance: {}", price, quantity, balance);
        } else if (price > 110 && quantity > 0) {
            log.info("selling {} quantities at {}", quantity, price);
            balance += quantity * price;
            quantity = 0;
            subscription.cancel();
            log.info("profit: {}", (balance - 1000));
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error", throwable);
    }

    @Override
    public void onComplete() {
        log.info("received complete!");
    }

}
