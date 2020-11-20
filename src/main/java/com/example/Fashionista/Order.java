package com.example.Fashionista;

import java.util.Date;
import java.util.List;

public class Order {

    private Long id;
    private Customer customer;
    private Date orderDate;
    private List<OrderRow> orderRows;
    private double totalAmount;

    public Order() {
    }

    public Order(Long id, Customer customer, Date orderDate, List<OrderRow> orderRows, double totalAmount) {
        this.id = id;
        this.customer = customer;
        this.orderDate = orderDate;
        this.orderRows = orderRows;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderRow> getOrderRows() {
        return orderRows;
    }

    public void setOrderRows(List<OrderRow> orderRows) {
        this.orderRows = orderRows;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
