package com.example.cwoop;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private ArrayList<Product> productList;
    boolean isFirstPurchase = false;
    private int addingProduct;
    private double totalPrice;
    private double totalPrice1;
    private double discount;
    Map<String, Integer> productQuantity = new HashMap<>();
    public ShoppingCart() {
        productList = new ArrayList<>();
    }


    public void addToCart(Product product){
        if(!this.productList.contains(product)){
            productList.add(product);
        }

        Integer productQuantityAfter = productQuantity.get(product.getPrdName());
        if (productQuantityAfter != null) {
            int intValue = productQuantityAfter.intValue();
            productQuantityAfter = intValue+1;
        } else {
            productQuantityAfter = 1;
            // Handle the null case as needed
        }
        productQuantity.put(product.getPrdName(),productQuantityAfter);


//        TODO : retrieve values from a hashmap from a given key in java  |  product quantity
//        input to hasmap (add1)


    }

    public int getAddingProduct(Product product) {
        return productQuantity.get(product.getPrdName());
    }

    public void removerFromCart(Product product){
        productList.remove(product);
    }
    public ArrayList<Product> getProductList() {
        return productList;
    }
    public double calculateTotalPrice() {
        totalPrice = 0.0;
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (Product product : productList) {
            totalPrice += product.getPrice() * getAddingProduct(product);
            // Count products per category for the discount
            String className = product.getClass().getSimpleName();
            categoryCounts.put(className, categoryCounts.getOrDefault(className, 0) + 1);
        }
        return totalPrice;
    }
    public double threeItemsDiscont(){
        totalPrice1 = 0.0;
        Map<String, Integer> categoryCounts = new HashMap<>();
        Map<String,Integer> productTotprice = new HashMap<>();

        for (Product product : productList) {
            totalPrice1 += product.getPrice() * getAddingProduct(product);
            // Count products per category for the discount
            String className = product.getClass().getSimpleName();
            categoryCounts.put(className, categoryCounts.getOrDefault(className, 0) + 1);
            System.out.println(product);
        }
        System.out.println(categoryCounts);
        System.out.println(totalPrice1);
        for (int count : categoryCounts.values()) {
            if (count >= 3) {
                System.out.println(count);
                discount = totalPrice1 * 0.2;
                break;
            }
        }
        System.out.println(discount);
        return discount;
    }
    public double finalTotal(){
        double totalPrice2 = 0.0;
        totalPrice2 = totalPrice - discount;
        return totalPrice2;
    }

    }

