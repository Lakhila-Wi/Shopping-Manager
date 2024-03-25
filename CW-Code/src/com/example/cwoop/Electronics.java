package com.example.cwoop;
public class Electronics extends Product{
    //Initializing the variables
    private String brand;
    private String warranty;

    //Creating Constructors
    public Electronics(String prdId, String prdName, int noOfItem, double price, String category,String brand, String warranty) {
        super(prdId, prdName, noOfItem, price,category); //importing super class variables
        this.brand = brand;
        this.warranty = warranty;
    }
    //Creating method for get brand name
    public String getBrand() {
        return brand;
    }
    //Creating method for get warranty details
    public String getWarranty() {
        return warranty;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

}
