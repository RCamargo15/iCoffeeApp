package com.rcamargo15.icoffeeapp.models;

import com.rcamargo15.icoffeeapp.models.enums.PaymentEnum;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {

    private Integer orderNumber;
    private String clientName;
    private LocalDate orderDate;
    private Double totalOrderPrice;
    private PaymentEnum paymentType;

    private List<Product> productList;

    public Order(Integer orderNumber, String clientName, LocalDate orderDate, Double totalOrderPrice, PaymentEnum paymentType) {
        this.orderNumber = orderNumber;
        this.clientName = clientName;
        this.orderDate = orderDate;
        this.totalOrderPrice = totalOrderPrice;
        this.paymentType = paymentType;
    }

    public Order(){
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public PaymentEnum getPaymentType() {
        return paymentType;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setPaymentType(PaymentEnum paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderNumber, order.orderNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", clientName='" + clientName + '\'' +
                ", orderDateAndTime=" + orderDate +
                ", totalOrderPrice=" + totalOrderPrice +
                ", paymentType=" + paymentType +
                ", productList=" + productList +
                '}';
    }
}
