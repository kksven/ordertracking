package com.ordertracking.rule.imp;

import com.ordertracking.constant.StatusTracking;
import com.ordertracking.domain.OrderTracking;
import com.ordertracking.domain.TrackingInformation;
import com.ordertracking.dummy.DummyTrackingInformation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ValidStatusToUpdateTest {
    @InjectMocks
    ValidStatusToUpdate validStatusToUpdate;

    @Test
    @DisplayName("return Empty When status not is in transit or delivery exception")
    public void shouldReturnEmptyWhenExistStatusDelivered(){
        OrderTracking orderTracking = new OrderTracking();
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.DELIVERED);

        List<OrderTracking> orderTrackingList = Collections.singletonList(orderTracking);
        history.put(orderTracking.getOrderId(), orderTrackingList);
        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);

        assertTrue(validStatusToUpdate.isEligibleToHistorySave(trackingInformation).isEmpty());
    }

    @Test
    @DisplayName("return update status When status is in transit")
    public void shouldReturnStatusWhenStatusIsInTransit(){
        OrderTracking orderTracking = new OrderTracking();
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.IN_TRANSIT);
        history.put(orderTracking.getOrderId(), new ArrayList<>());
        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);

        String result = validStatusToUpdate.isEligibleToHistorySave(trackingInformation);

        assertEquals("Update status", result);
    }

    @Test
    @DisplayName("return update status When status is delivery exception")
    public void shouldReturnStatusWhenStatusIsDeliveryException(){
        OrderTracking orderTracking = new OrderTracking();
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.DELIVERY_EXCEPTION);
        history.put(orderTracking.getOrderId(), new ArrayList<>());
        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);

        String result = validStatusToUpdate.isEligibleToHistorySave(trackingInformation);

        assertEquals("Update status", result);
    }
}
