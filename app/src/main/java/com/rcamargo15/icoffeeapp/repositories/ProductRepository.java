package com.rcamargo15.icoffeeapp.repositories;

import com.rcamargo15.icoffeeapp.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ProductRepository {

    private static final Product product1 = new Product(1, "Pingado", 1.0);
    private static final Product product2 = new Product(2, "Café com Leite", 2.0);
    private static final Product product3 = new Product(3, "Café com Leite e Creme", 3.5);
    private static final Product product4 = new Product(4, "Cappuccino", 4.50);
    private static final Product product5 = new Product(5, "Espresso", 3.00);
    private static final Product product6 = new Product(6, "Cookies", 0.5);
    private static final Product product7 = new Product(7, "Croissant", 6.50);
    private static final Product product8 = new Product(8, "Bolo de Fubá", 6.00);
    private static final Product product9 = new Product(9, "Bolo Floresta Negra", 7.50);
    private static final Product product10 = new Product(10, "Bolo de Morango", 7.00);

    private static final List<Product> I_COFFEE_PRODUCTS = Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10);

    public List<Product> findAll(){
        List<Product> commonList = new ArrayList<>();
        for (Product prod : I_COFFEE_PRODUCTS){
            commonList.add(prod);
        }
        return commonList;
    }

    public Product findById(Integer id){
        for (Product prod : I_COFFEE_PRODUCTS){
            if(prod.getProductCode() == id){
                return prod;
            }
        }
        return null;
    }

    public List<Product> findByPrice(Double price){
        List<Product> commonPrice = new ArrayList<>();
          for (Product prod : I_COFFEE_PRODUCTS){
            if(prod.getProductPrice() == price){
                commonPrice.add(prod);
            }
        }
        return commonPrice;
    }
}
