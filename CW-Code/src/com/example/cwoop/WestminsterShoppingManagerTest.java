package com.example.cwoop;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class WestminsterShoppingManagerTest {

    @Test
    public void testAddElectronicProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Mock user input for adding an electronic product
        String input = "123\nElectronic\nLaptop\n5\n800.0\nDell\n1 year warranty\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        shoppingManager.add_prd();

        // Assert that the product was added successfully
        assertEquals(1, shoppingManager.getPrd_list().size());
        assertEquals("Laptop", shoppingManager.getPrd_list().get(0).getPrdName());
        assertTrue(shoppingManager.getPrd_list().get(0) instanceof Electronics);
    }

    @Test
    public void testAddClothingProduct() {
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Mock user input for adding a clothing product
        String input = "456\nClothing\nShirt\n10\n25.0\nM\nBlue\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        shoppingManager.add_prd();

        // Assert that the product was added successfully
        assertEquals(1, shoppingManager.getPrd_list().size());
        assertEquals("Shirt", shoppingManager.getPrd_list().get(0).getPrdName());
        assertTrue(shoppingManager.getPrd_list().get(0) instanceof Clothing);
    }

    @Test
    public void testDeleteExistingProduct() {

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();

        // Mock user input for adding a product
        String addInput = "123\nY\n";
        InputStream in = new ByteArrayInputStream(addInput.getBytes());
        System.setIn(in);

        shoppingManager.delete_prd();

        // Assert that the product was deleted successfully
        assertEquals(0, shoppingManager.getPrd_list().size());
    }

    @Test
    public void testPrintProductList() {

        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        // Mock user input for adding a product
        String input = "123\nElectronic\nLaptop\n5\n800.0\nDell\n1 year warranty\n";

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        shoppingManager.add_prd();

        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        shoppingManager.printProductList();

        System.setOut(System.out);

        assertTrue(outputStream.toString().contains("Laptop"));
    }
}