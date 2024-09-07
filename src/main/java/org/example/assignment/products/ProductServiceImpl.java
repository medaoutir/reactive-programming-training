package org.example.assignment.products;

import org.example.common.Util;

import javax.swing.text.Utilities;
import java.time.Duration;

public class ProductServiceImpl implements ProductService{

    private final ExternalProductServiceClient externalProductServiceClient = new ExternalProductServiceClient();
    @Override
    public void getProductName(int productId) {
        String productIdStr = String.valueOf(productId);
        externalProductServiceClient.getProduct(productIdStr)
                .timeout(Duration.ofSeconds(2),externalProductServiceClient.getFallback(productIdStr))
                .switchIfEmpty(externalProductServiceClient.getEmptyFallback(productIdStr))
                .doOnNext((productName) -> System.out.println("received product "+ productName))
                .doOnError(Throwable::printStackTrace)
                .subscribe(Util.subscriber());
        Util.sleepSeconds(5);

    }
}
