package org.example.assignment.publishers.hot;

import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

public class RevenueService {
    private Map<String, Integer> revenueMap = new HashMap<>();


    public Flux<Map<String, Integer>> calculateRevenue(Flux<String> orders) {
        return orders
                .map(order -> {
                    String category = order.split(":")[1];
                    int price = Integer.parseInt(order.split(":")[2]);
                    int quantity = Integer.parseInt(order.split(":")[3]);
                    revenueMap.put(category, price * quantity);
                    return revenueMap;
                });
    }

}
