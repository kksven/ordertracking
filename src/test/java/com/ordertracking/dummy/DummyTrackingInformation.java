package com.ordertracking.dummy;

import com.ordertracking.domain.OrderTracking;
import com.ordertracking.domain.TrackingInformation;

import java.util.List;
import java.util.Map;

public class DummyTrackingInformation {

    public static TrackingInformation build(OrderTracking orderTracking, Map<Long, List<OrderTracking>> history) {
            return TrackingInformation.builder()
                    .orderTracking(orderTracking)
                    .history(history)
                    .build();
    }
}
