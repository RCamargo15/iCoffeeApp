package com.rcamargo15.icoffeeapp.repositories.memDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RenameTable;

@Entity
@RenameTable(fromTableName = "OrderEntity", toTableName = "orders")
public class OrderEntity  {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "orderNumber")
    private Integer orderNumber;

    @ColumnInfo(name = "clientName")
    private String clientName;

    @ColumnInfo(name = "orderDate")
    private String orderDate;

    @ColumnInfo(name = "totalOrderPrice")
    private Double totalOrderPrice;

    @ColumnInfo(name = "paymentType")
    private String paymentType;

    public OrderEntity(Integer orderNumber, String clientName, String orderDate, Double totalOrderPrice, String paymentType) {
        this.orderNumber = orderNumber;
        this.clientName = clientName;
        this.orderDate = orderDate;
        this.totalOrderPrice = totalOrderPrice;
        this.paymentType = paymentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(Double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
