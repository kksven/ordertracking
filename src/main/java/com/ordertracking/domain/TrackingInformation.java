package com.ordertracking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class TrackingInformation {
    private OrderTracking orderTracking;
    private Map<Long, List<OrderTracking>> history;
}
