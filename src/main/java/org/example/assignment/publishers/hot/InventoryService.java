package org.example.assignment.publishers.hot;

import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private Map<String, Integer> revenueMap = new HashMap<>();


    public Flux<Map<String, Integer>> calculateInventory(Flux<String> orders) {
        return orders
                .map(order -> {
                    String category = order.split(":")[1];
                    int quantity = Integer.parseInt(order.split(":")[3]);
                    revenueMap.computeIfAbsent(category, cat -> 500);
                    revenueMap.computeIfPresent(category, (cat, qte) -> qte - quantity);
                    return revenueMap;
                });
    }
}
