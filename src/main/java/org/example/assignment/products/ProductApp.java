package org.example.assignment.products;

public class ProductApp {

    private static ProductService productService = new ProductServiceImpl();

    public static void main(String[] args) {
        productService.getProductName(3);
    }
}
