package org.example.assignment.publishers.hot;

import org.example.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class OrderApp {

    private static final Logger log = LoggerFactory.getLogger(OrderApp.class);

    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        Flux<String> orders = orderService.getOrder();
        InventoryService inventoryService = new InventoryService();
        RevenueService revenueService = new RevenueService();
        inventoryService.calculateInventory(orders)
                .doOnNext(inventory -> inventory.forEach((key, value) -> log.info("the remaining quantity for category {} is {}", key, value)))
                .subscribe(Util.subscriber("inventory"));
        Util.sleepSeconds(3);
        revenueService.calculateRevenue(orders)
                .doOnNext(revenue -> revenue.forEach((key, value) -> log.info("the revenue for category {} is {}", key, value)))
                .subscribe(Util.subscriber("revenue"));
        Util.sleepSeconds(15);


    }
}
