package com.example.cwoop.interfaces;

import com.example.cwoop.Product;
import com.example.cwoop.WestminsterShoppingManager;

import java.io.IOException;
import java.util.ArrayList;
public interface ShoppingManager {
    public void add_prd() throws IOException;
    public void delete_prd() throws IOException;
    public void printProductList() throws IOException;
    public void save_in_a_file(ArrayList<Product> data, String Products);



}
