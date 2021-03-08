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
public class CollectedOfStorageTest {
    @InjectMocks
    CollectedOfStorage collectedOfStorage;

    @Test
    @DisplayName("return Empty When exist status Collected Of Storage to OrderId ")
    public void shouldReturnEmptyWhenExistStatusCollectedOfStorage(){
        OrderTracking orderTracking = new OrderTracking();
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.COLLECTED_OF_STORAGE);

        List<OrderTracking> orderTrackingList = Collections.singletonList(orderTracking);
        history.put(orderTracking.getOrderId(), orderTrackingList);

        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);

        String result = collectedOfStorage.isEligibleToHistorySave(trackingInformation);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("return Update status When first order status is Collected Of Storage")
    public void shouldReturnStatusWhenIsInitialStatus(){

        String expected = "Update status";

        OrderTracking orderTracking = new OrderTracking();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.COLLECTED_OF_STORAGE);
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);

        String result = collectedOfStorage.isEligibleToHistorySave(trackingInformation);

        assertEquals(expected, result);
    }


    @Test
    @DisplayName("return Empty When exist status Collected Of Storage")
    public void shouldReturnEmptyWhenNewStatusIsInTransit(){

        //Initial status
        OrderTracking orderTracking = new OrderTracking();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(StatusTracking.COLLECTED_OF_STORAGE);

        // New Status in Transit
        OrderTracking newOrderTracking = new OrderTracking();
        newOrderTracking.setOrderId(1L);
        newOrderTracking.setTrackingStatusId(StatusTracking.IN_TRANSIT);

        List<OrderTracking> orderTrackingList = Collections.singletonList(orderTracking);
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        history.put(orderTracking.getOrderId(), orderTrackingList);
        TrackingInformation trackingInformation = DummyTrackingInformation.build(newOrderTracking, history);

        String result = collectedOfStorage.isEligibleToHistorySave(trackingInformation);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("return Empty When first status not is Collected Of Storage")
    public void shouldReturnEmptyWhenNewStatusNotIsCollectedOfStorage(){

        OrderTracking orderTracking = new OrderTracking();
        orderTracking.setOrderId(1L);
        orderTracking.setTrackingStatusId(5);
        Map<Long, List<OrderTracking>> history = new HashMap<>();
        TrackingInformation trackingInformation = DummyTrackingInformation.build(orderTracking, history);
        String result = collectedOfStorage.isEligibleToHistorySave(trackingInformation);

        assertTrue(result.isEmpty());
    }
}
