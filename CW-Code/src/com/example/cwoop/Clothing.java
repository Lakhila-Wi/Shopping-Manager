package com.example.cwoop;
public class Clothing extends Product{
    //Initializing the variables
    private String size;
    private String color;
    // creating Constructors
    public Clothing(String prdId, String prdName, int noOfItem, double price, String category,String size, String color) {
        super(prdId, prdName, noOfItem, price,category);
        this.size = size;
        this.color = color;
    }
    //method for get size
    public String getSize() {
        return size;
    }
    //method for get color
    public String getColor() {
        return color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
