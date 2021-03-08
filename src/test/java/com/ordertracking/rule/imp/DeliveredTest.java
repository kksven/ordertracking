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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeliveredTest {

    @InjectMocks
    Delivered delivered;

    @Test
    @DisplayName("return Empty When exist status Delivered to OrderId ")
    public void shouldReturnEmptyWhenExistStatusDelivered(){
        OrderTracking orderTracking = new OrderTracking();
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.DELIVERED);

        List<OrderTracking> orderTrackingList = Collections.singletonList(orderTracking);
        history.put(orderTracking.getOrderId(), orderTrackingList);
        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);

        assertTrue(delivered.isEligibleToHistorySave(trackingInformation).isEmpty());
    }

    @Test
    @DisplayName("return Update status When not exists Delivered status in history")
    public void shouldReturnUpdateStatusWhenNotExistStatusDelivered(){

        String expected = "Update status";

        OrderTracking orderTracking = new OrderTracking();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.DELIVERED);
        List<OrderTracking> orderTrackingList = new ArrayList<>();

        Map<Long, List<OrderTracking>> history = new HashMap<>();
        history.put(orderTracking.getOrderId(), orderTrackingList);
        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);

        String result = delivered.isEligibleToHistorySave(trackingInformation);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("return Empty When new status and order is Delivered")
    public void shouldReturnFalseWhenInsertNewStatusAndOrderIsDelivered(){

        //After status
        OrderTracking orderTracking = new OrderTracking();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.DELIVERED);

        // New Delivery exception
        OrderTracking newOrderTracking = new OrderTracking();
        newOrderTracking.setOrderId(1L);
        newOrderTracking.setTrackingStatusId(StatusTracking.DELIVERY_EXCEPTION);

        List<OrderTracking> orderTrackingList = Arrays.asList(orderTracking, newOrderTracking);
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        history.put(orderTracking.getOrderId(), orderTrackingList);
        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);

        assertTrue(delivered.isEligibleToHistorySave(trackingInformation).isEmpty());
    }
}
