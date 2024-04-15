package com.sid.msorder.Enums;

public enum ShippingStatus {
    NOT_SHIPPED,
    IN_TRANSIT, //from for example the manufacture to the distribution center
    OUT_FOR_DELIVERY, //to the client
    DELIVERED,
    RETURNED,
    LOST
}
