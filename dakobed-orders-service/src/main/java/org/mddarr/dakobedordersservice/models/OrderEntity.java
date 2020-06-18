package org.mddarr.dakobedordersservice.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Dakobed-Orders")
public class OrderEntity {
    private String OrderId;
    private String CustomerId;
    private String productID;
    private String OrderStatus;
    private long OrderCreationDate;

    @DynamoDBHashKey(attributeName="OrderId")
    public String getOrderId() {return OrderId; }
    public void setOrderId(String orderId) { OrderId = orderId;}

    @DynamoDBAttribute(attributeName = "CustomerId")
    public String getCustomerId() { return CustomerId;}
    public void setCustomerId(String customerID) { CustomerId = customerID; }

    @DynamoDBAttribute(attributeName = "productID")
    public String getProductID() { return productID; }
    public void setProductID(String productID) { this.productID = productID;}

    @DynamoDBAttribute(attributeName = "OrderStatus")
    public String getOrderStatus() { return OrderStatus;}
    public void setOrderStatus(String orderStatus) { OrderStatus = orderStatus;  }

    @DynamoDBAttribute(attributeName = "OrderCreationDate")
    public long getOrderCreationDate() {return OrderCreationDate; }
    public void setOrderCreationDate(long orderCreationDate) {OrderCreationDate = orderCreationDate;}
}
