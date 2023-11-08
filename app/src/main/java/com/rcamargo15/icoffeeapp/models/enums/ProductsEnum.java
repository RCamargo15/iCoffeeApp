package com.rcamargo15.icoffeeapp.models.enums;

public enum ProductsEnum {

    PINGADO(1, "Pingado", 1.00D),
    CAFE_LEITE(2, "Café com Leite", 2.00D),
    CAFE_LEITE_CREME(3, "Café com Leite e Creme", 3.50D),
    CAPPUCCINO(4, "Cappuccino", 4.50D),
    ESPRESSO(5, "Espresso", 3.00D),
    COOKIES(6, "Cookies", 0.5D),
    CROISSANT(7, "Croissant", 6.50D),
    BOLO_FUBA(8, "Bolo de Fubá", 6.00),
    BOLO_FLORESTA_NEGRA(9, "Bolo Floresta Negra", 7.50D),
    BOLO_MORANGO(10, "Bolo de Morango", 7.00D);

    ProductsEnum(Integer productId, String productName, Double productPrice) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    private Integer productId;
    private String productName;
    private Double productPrice;

    public String getProductName() {
        return productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public Integer getProductId(){
        return productId;
    }

    @Override
    public String toString() {
        return "ProductPriceEnum{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }

    public Double getProductPrice(ProductsEnum desiredProduct){
        for(ProductsEnum product : ProductsEnum.values()){
            if(product.equals(desiredProduct)){
                return product.getProductPrice();
            }
        }
        return null;
    }

    public String getProductName(ProductsEnum desiredProduct){
        for(ProductsEnum product : ProductsEnum.values()){
            if(product.equals(desiredProduct)){
                return product.getProductName();
            }
        }
        return null;
    }
}
