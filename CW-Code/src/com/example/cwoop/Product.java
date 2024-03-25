package com.example.cwoop;
import java.io.Serializable;
import java.util.Objects;

public abstract class Product {
    private String prdId;
    private String prdName;
    private int noOfItem;
    private double price;
    private String category;
//    Creating Constructor
    public Product(String prdId, String prdName, int noOfItem, double price,String category) {
        this.prdId = prdId;
        this.prdName = prdName;
        this.noOfItem = noOfItem;
        this.price = price;
        this.category = category;
    }
    public Product(String category){
        this.category = category;
    }
//  getter for product id
    public String getPrdId() {
        return prdId;
    }
//  Getter for product Name
    public String getPrdName() {
        return prdName;
    }
//  Getter for number of items
    public int getNoofItem() {
        return noOfItem;
    }
//  Getter for price
    public double getPrice() {
        return price;
    }
//  Getter for Category
    public String getCategory() {
        return category;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public void setNoofItem(int noOfItem) {
        this.noOfItem = noOfItem;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public void setCategory(String category) {
        this.category = category;
    }
//  Overrides the default toString method to provide a custom string representation of the Product object.
    @Override
    public String toString() {
        return "Product: " +
                "prdId='" + prdId + '\'' +
                ", prdName='" + prdName + '\'' +
                ", noOfItem=" + noOfItem +
                ", price=" + price +
                ", category='" + category + '\''
                ;
    }


//     Overrides the default equals method to compare two Product objects based on their prdName field for equality.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(prdName, product.getPrdName());
    }


}
