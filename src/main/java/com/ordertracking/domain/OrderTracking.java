package com.ordertracking.domain;

import lombok.Data;


@Data
public class OrderTracking {
    private Long orderId;
    private int trackingStatusId;
    private String changeStatusDate;
}
