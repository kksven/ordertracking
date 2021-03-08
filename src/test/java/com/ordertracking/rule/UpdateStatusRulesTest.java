package com.ordertracking.rule;

import com.ordertracking.domain.TrackingInformation;
import com.ordertracking.rule.imp.CollectedOfStorage;
import com.ordertracking.rule.imp.Delivered;
import com.ordertracking.rule.imp.ValidStatusToUpdate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateStatusRulesTest {

    @InjectMocks
    UpdateStatusRules updateStatusRules;

    @Mock
    CollectedOfStorage collectedOfStorage;

    @Mock
    Delivered delivered;

    @Mock
    ValidStatusToUpdate validStatusToUpdate;

    @Test
    @DisplayName("Return empty when status not is eligible")
    public void shouldReturnEmptyWhenStatusIsInvalid(){
        TrackingInformation trackingInformation = TrackingInformation.builder().build();
        when(collectedOfStorage.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");
        when(validStatusToUpdate.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");
        when(delivered.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");

        String result = updateStatusRules.isEligibleToHistorySave(trackingInformation);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Return Update status when update status is valid")
    public void shouldReturnStatusWhenStatusCollectedOfStorageIsValid(){
        String expected = "Update status";
        TrackingInformation trackingInformation = TrackingInformation.builder().build();
        when(collectedOfStorage.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("Update status");
        when(validStatusToUpdate.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");
        when(delivered.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");

        String result = updateStatusRules.isEligibleToHistorySave(trackingInformation);

        assertEquals(expected, result );
    }

    @Test
    @DisplayName("Return Update status  when not is status collected of storage and Delivered is valid")
    public void shouldReturnStatusWhenStatusDeliveredIsValid(){
        String expected = "Update status";
        TrackingInformation trackingInformation = TrackingInformation.builder().build();
        when(collectedOfStorage.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");
        when(validStatusToUpdate.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");
        when(delivered.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("Update status");

        String result = updateStatusRules.isEligibleToHistorySave(trackingInformation);

        assertEquals(expected, result );
    }

    @Test
    @DisplayName("Return Update status  when not is status collected of storage and Delivered is valid")
    public void shouldReturnStatusWhenIsValidToUpdate(){
        String expected = "Update status";
        TrackingInformation trackingInformation = TrackingInformation.builder().build();
        when(collectedOfStorage.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");
        when(validStatusToUpdate.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("Update status");
        when(delivered.isEligibleToHistorySave(any(TrackingInformation.class))).thenReturn("");

        String result = updateStatusRules.isEligibleToHistorySave(trackingInformation);

        assertEquals(expected, result );
    }
}
